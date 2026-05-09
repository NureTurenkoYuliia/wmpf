const express = require("express");
const router = express.Router();

const { readDB, writeDB } = require("../utils/db");
const { validateComment } = require("../middleware/validationMiddleware");

const createComment = require("../models/comment");

// GET /comments/:postId
router.get("/:postId", (req, res) => {
  const db = readDB();

  const filtered = db.comments.filter(
    c => c.postId == req.params.postId
  );

  res.json(filtered);
});

// POST /comments
router.post("/", validateComment, (req, res) => {
  const db = readDB();

  const { postId, userId, text } = req.body;
  const newComment = createComment(postId, userId, text);

  db.comments.push(newComment);
  writeDB(db);

  res.status(201).json(newComment);
});

module.exports = router;