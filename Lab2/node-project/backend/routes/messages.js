const express = require("express");
const router = express.Router();

const { readDB, writeDB } = require("../utils/db");
const { validateMessage } = require("../middleware/validationMiddleware");

const createMessage = require("../models/message");

// GET /messages/chat/:chatId
router.get("/chat/:chatId", (req, res) => {
  const db = readDB();
  const chatId = Number(req.params.chatId);
  const messages = db.messages.filter(
    (m) => m.chatId === chatId
  );

  res.json(messages);
});

// POST /messages
router.post("/", validateMessage, (req, res) => {
  const db = readDB();

  const { chatId, senderId, text } = req.body;
  const newMessage = createMessage(chatId, senderId, text);

  db.messages.push(newMessage);

  writeDB(db);

  res.status(201).json(newMessage);
});

module.exports = router;