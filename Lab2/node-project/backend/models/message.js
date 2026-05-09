function createMessage(chatId, senderId, text) {
  return {
    id: Date.now(),
    chatId,
    senderId,
    text,
    createdAt: new Date().toISOString(),
  };
}

module.exports = createMessage;