// middleware/authMiddleware.js
const jwt = require("jsonwebtoken");

function verifyToken(req, res, next) {
  // Token should be sent in Authorization header as: Bearer <token>
  const authHeader = req.headers["authorization"];
  const token = authHeader && authHeader.split(" ")[1];

  if (!token) {
    return res.status(403).json({ success: false, message: "No token provided" });
  }

  try {
    // Verify token
    const decoded = jwt.verify(token, process.env.JWT_SECRET);

    // Attach decoded payload to request
    req.user = {
      userId: decoded.userId,   // 👈 available in routes
      username: decoded.username
    };

    next();
  } catch (err) {
    console.error("Token verification error:", err.message);
    return res.status(401).json({ success: false, message: "Unauthorized" });
  }
}

module.exports = verifyToken;

