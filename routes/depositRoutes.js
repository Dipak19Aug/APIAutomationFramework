const express = require("express");
const router = express.Router();
const db = require("../db"); // MySQL connection pool
const verifyToken = require("../middleware/authMiddleware"); // import middleware

// 👇 Custom FD number generator
function generateFdNumber() {
  // 52 + 10 random digits = 12 digits total
  return "52" + Math.floor(1000000000 + Math.random() * 9000000000).toString();
}

// Create FD (protected)
router.post("/create", verifyToken, async (req, res) => {
  const {
    depositType,
    amount,
    durationMonths,
    nominee_firstname,
    nominee_lastname,
    nominee_address,
    nominee_relationship
  } = req.body;

  try {
    const fdId = generateFdNumber(); // 👈 custom FD number
    const userId = req.user.userId;  // 👈 taken from JWT

    const sql = `
      INSERT INTO deposits (fd_id, user_id, deposit_type, amount, duration_months, nominee_firstname, nominee_lastname, nominee_address, nominee_relationship)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    `;

    await db.query(sql, [
      fdId,
      userId,
      depositType,
      amount,
      durationMonths,
      nominee_firstname,
      nominee_lastname,
      nominee_address,
      nominee_relationship
    ]);

    res.status(201).json({
      success: true,
      message: "FD created successfully",
      fdId
    });
  } catch (err) {
    console.error("FD create error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});


// Get FD Details by FD Number (protected)
// 1. Get Single FD Details (POST + body)
router.get("/details/:fdId", verifyToken, async (req, res) => {
  const fdId = req.params.fdId;
  const userId = req.user.userId;

  try {
    const sql = `SELECT * FROM deposits WHERE fd_id = ? AND user_id = ?`;
    const [rows] = await db.query(sql, [fdId, userId]);

    if (rows.length === 0) {
      return res.status(404).json({ success: false, message: "FD not found or not owned by user" });
    }

    res.json({ success: true, fdDetails: rows[0] });
  } catch (err) {
    console.error("FD details error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

// 2. Get FD List by User (GET, no body userId needed)
router.get("/list", verifyToken, async (req, res) => {
  const userId = req.user.userId;

  try {
    const sql = `SELECT * FROM deposits WHERE user_id = ?`;
    const [rows] = await db.query(sql, [userId]);

    if (rows.length === 0) {
      return res.status(404).json({
        success: false,
        message: "No FD found for this user"
      });
    }

    res.json({
      success: true,
      fdList: rows
    });
  } catch (err) {
    console.error("FD list error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});



// 3. Delete FD (DELETE + body)
router.delete("/delete", verifyToken, async (req, res) => {
  const { fdId } = req.body;
  const userId = req.user.userId;

  try {
    const sql = `DELETE FROM deposits WHERE fd_id = ? AND user_id = ?`;
    const [result] = await db.query(sql, [fdId, userId]);

    if (result.affectedRows === 0) {
      return res.status(404).json({
        success: false,
        message: "FD not found or not owned by user"
      });
    }

    res.json({
      success: true,
      message: "FD deleted successfully"
    });
  } catch (err) {
    console.error("FD delete error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

module.exports = router;
