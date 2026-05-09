function createPost(userId, content) {
  return {
    id: Date.now(),
    userId,
    content,
    createdAt: new Date().toISOString(),
  };
}

module.exports = createPost;