const express = require("express");
const router = express.Router();

const { readDB, writeDB } = require("../utils/db");

const createChat = require("../models/chat");
const createChatUser = require("../models/chatUser");

// POST /chats/private
router.post("/private", (req, res) => {
  const db = readDB();
  const { user1, user2 } = req.body;
  const user1Chats = db.chatUsers
    .filter((cu) => cu.userId === user1)
    .map((cu) => cu.chatId);

  let existingChat = null;

  for (const chatId of user1Chats) {
    const usersInChat = db.chatUsers
      .filter((cu) => cu.chatId === chatId)
      .map((cu) => cu.userId);

    if (
      usersInChat.includes(user1) &&
      usersInChat.includes(user2) &&
      usersInChat.length === 2
    ) {
      existingChat = db.chats.find(
        (c) => c.id === chatId
      );

      break;
    }
  }

  if (existingChat) {
    return res.json(existingChat);
  }

  const newChat = createChat("Private Chat");

  db.chats.push(newChat);

  db.chatUsers.push(
    createChatUser(newChat.id, user1)
  );

  db.chatUsers.push(
    createChatUser(newChat.id, user2)
  );

  writeDB(db);

  res.json(newChat);
});

module.exports = router;