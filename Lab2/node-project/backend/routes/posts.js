const express = require("express");
const router = express.Router();

const { readDB, writeDB } = require("../utils/db");
const { validatePost } = require("../middleware/validationMiddleware");

const createPost = require("../models/post");

// GET /posts
router.get("/", (req, res) => {
  const db = readDB();
  res.json(db.posts);
});

// POST /posts
router.post("/", validatePost, (req, res) => {
  const db = readDB();

  const { userId, content } = req.body;
  const newPost = createPost(userId, content);

  db.posts.push(newPost);
  writeDB(db);

  res.status(201).json(newPost);
});

module.exports = router;