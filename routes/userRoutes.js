const express = require("express");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const router = express.Router();
const db = require("../db"); // MySQL connection pool

// 👇 Custom User ID generator
function generateUserId() {
  return "92" + Math.floor(1000000 + Math.random() * 9000000).toString();
}

// 👇 Custom Application ID generator
function generateApplicationId() {
  return "APP" + Math.floor(100000 + Math.random() * 900000).toString();
}

// Register User (without password)
router.post("/register", async (req, res) => {
  const { firstname, username, middleName, sirname, pan, aadhaar, mobile, dob, gender } = req.body;

  try {
    // Pre-check duplicates
    const [rows] = await db.query(
      "SELECT username, pan, aadhaar, mobile FROM users WHERE username = ? OR pan = ? OR aadhaar = ? OR mobile = ?",
      [username, pan, aadhaar, mobile]
    );

    if (rows.length > 0) {
      let errors = {};
      rows.forEach(row => {
        if (row.username === username) errors.usernameError = "Duplicate value error: username already exists";
        if (row.pan === pan) errors.panError = "Duplicate value error: PAN already exists";
        if (row.aadhaar === aadhaar) errors.aadhaarError = "Duplicate value error: Aadhaar already exists";
        if (row.mobile === mobile) errors.mobileError = "Duplicate value error: mobile already exists";
      });
      return res.status(400).json({ success: false, ...errors });
    }

    // If no duplicates, proceed
    const userId = generateUserId();
    const applicationId = generateApplicationId();

    const sql = `
      INSERT INTO users (user_id, application_id, firstname, username, middle_name, sirname, pan, aadhaar, mobile, dob, gender)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    `;

    await db.query(sql, [
      userId,
      applicationId,
      firstname,
      username,
      middleName,
      sirname,
      pan,
      aadhaar,
      mobile,
      dob,
      gender
    ]);

    res.status(201).json({
      success: true,
      message: "User registered successfully",
      userId,
      applicationId
    });
  } catch (err) {
    console.error("Register error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

// Create Password (second step)
router.post("/create-password", async (req, res) => {
  const { applicationId, userId, username, password } = req.body;

  try {
    const hashedPassword = await bcrypt.hash(password, 10);

    const sql = `
      UPDATE users SET password = ? WHERE user_id = ? AND application_id = ? AND username = ?
    `;

    const [result] = await db.query(sql, [hashedPassword, userId, applicationId, username]);

    if (result.affectedRows === 0) {
      return res.status(400).json({ success: false, message: "User not found or invalid details" });
    }

    res.json({
      success: true,
      message: "Password set successfully"
    });
  } catch (err) {
    console.error("Create password error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

// Login User (unchanged)
router.post("/login", async (req, res) => {
  const { username, password } = req.body;

  try {
    const [rows] = await db.query("SELECT * FROM users WHERE username = ?", [username]);
    if (rows.length === 0) {
      return res.status(401).json({ success: false, message: "Invalid username or password" });
    }

    const user = rows[0];
    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) {
      return res.status(401).json({ success: false, message: "Invalid username or password" });
    }

    const token = jwt.sign(
      { userId: user.user_id, username: user.username },
      process.env.JWT_SECRET,
      { expiresIn: "1h" }
    );

    res.json({
      success: true,
      message: "Login successful",
      token
    });
  } catch (err) {
    console.error("Login error:", err.message);
    res.status(500).json({ success: false, message: "Internal server error" });
  }
});

module.exports = router;
