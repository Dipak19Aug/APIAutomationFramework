const express = require("express");
const router = express.Router();
const db = require("../db");
const verifyToken = require("../middleware/authMiddleware"); // path adjust kar
const depositValidator = require("../middleware/depositValidator");
const { validationResult } = require("express-validator");

// Create FD (protected + validated)
router.post("/create", verifyToken, depositValidator, async (req, res) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({ success: false, errors: errors.array() });
  }

  const { userId, depositType, amount, durationMonths, nominee } = req.body;

  try {
    const sql = `
      INSERT INTO deposits (user_id, deposit_type, amount, duration_months, nominee_firstname)
      VALUES (?, ?, ?, ?, ?)
    `;
    const [result] = await db.query(sql, [userId, depositType, amount, durationMonths, nominee]);

    res.status(201).json({
      success: true,
      message: "FD created successfully",
      fdId: result.insertId
    });
  } catch (err) {
    console.error("FD create error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

// Get FD Details (protected)
router.post("/details", verifyToken, async (req, res) => {
  const { fdId } = req.body;
  try {
    const [rows] = await db.query("SELECT * FROM deposits WHERE fd_id = ?", [fdId]);
    if (rows.length === 0) {
      return res.status(404).json({ success: false, message: "FD not found" });
    }
    res.json({ success: true, fd: rows[0] });
  } catch (err) {
    console.error("FD details error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

// Get All FDs by User (protected)
router.post("/user", verifyToken, async (req, res) => {
  const { userId } = req.body;
  try {
    const [rows] = await db.query("SELECT * FROM deposits WHERE user_id = ?", [userId]);
    res.json({ success: true, fds: rows });
  } catch (err) {
    console.error("FD list error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

// Delete FD (protected)
router.delete("/delete", verifyToken, async (req, res) => {
  const { fdId, userId } = req.body;
  try {
    const [result] = await db.query("DELETE FROM deposits WHERE fd_id = ? AND user_id = ?", [fdId, userId]);
    if (result.affectedRows === 0) {
      return res.status(404).json({ success: false, message: "FD not found or not owned by user" });
    }
    res.json({ success: true, message: "FD deleted successfully" });
  } catch (err) {
    console.error("FD delete error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

module.exports = router;
