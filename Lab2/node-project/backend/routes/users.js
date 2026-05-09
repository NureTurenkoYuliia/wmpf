const express = require("express");
const router = express.Router();

const { readDB, writeDB } = require("../utils/db");
const { validateUser } = require("../middleware/validationMiddleware");

const createUser = require("../models/user");

// GET /users
router.get("/", (req, res) => {
  const db = readDB();
  res.json(db.users);
});

// POST /users
router.post("/", validateUser, (req, res) => {
  const db = readDB();

  const { name } = req.body;
  const newUser = createUser(name);

  db.users.push(newUser);
  writeDB(db);

  res.status(201).json(newUser);
});

// POST /users/:id/add-friend
router.post("/:id/add-friend", (req, res) => {
  const db = readDB();

  const user = db.users.find(u => u.id == req.params.id);
  const { friendId } = req.body;

  if (!user) {
    return res.status(404).json({ error: "User not found" });
  }

  user.friends.push(friendId);

  writeDB(db);

  res.status(201).json(user);
});

// GET /users/:userId/name
router.get("/:userId/name", (req, res) => {
  const db = readDB();
  const user = db.users.find(u => u.id.toString() === req.params.userId.toString());
  
  if (!user) {
    return res.status(404).json({ message: "User not found" });
  }

  res.json({ name: user.name });
});

module.exports = router;