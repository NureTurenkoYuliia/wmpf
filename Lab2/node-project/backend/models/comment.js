function createComment(postId, userId, text) {
  return {
    id: Date.now(),
    postId,
    userId,
    text,
    createdAt: new Date().toISOString(),
  };
}

module.exports = createComment;