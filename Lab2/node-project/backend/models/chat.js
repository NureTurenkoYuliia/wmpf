function createChat(name, type = "private") {
  return {
    id: Date.now(),
    name,
    type,
    createdAt: new Date().toISOString(),
  };
}

module.exports = createChat;