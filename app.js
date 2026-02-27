require("dotenv").config();
const express = require("express");
const path = require("path");
const app = express();

app.use(express.json());

// Import routes
const userRoutes = require("./routes/userRoutes");
const depositRoutes = require("./routes/depositRoutes");

// Mount routes
app.use("/api/users", userRoutes);
app.use("/api/deposits", depositRoutes);

// Serve frontend folder (absolute path)
app.use(express.static("C:/Users/Dipak Ghadge/Desktop/Dipak/SmartDepositAPI/Frontend"));

// Default route → open home.html
app.get("/", (req, res) => {
  res.sendFile(path.join("C:/Users/Dipak Ghadge/Desktop/Dipak/SmartDepositAPI/Frontend", "home.html"));
});

// 404 handler (must be last)
app.use((req, res) => {
  res.status(404).json({
    success: false,
    message: "Route not found"
  });
});

// Start server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
