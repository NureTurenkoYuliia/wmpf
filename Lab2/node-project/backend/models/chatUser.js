function createChatUser(chatId, userId) {
  return {
    id: Date.now() + Math.random(),
    chatId,
    userId,
  };
}

module.exports = createChatUser;