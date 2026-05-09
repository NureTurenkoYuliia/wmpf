function validateUser(req, res, next) {
  const { name } = req.body;

  if (!name) {
    return res.status(400).json({ error: "Name is required" });
  }

  next();
}

function validatePost(req, res, next) {
  const { content } = req.body;

  if (!content) {
    return res.status(400).json({ error: "Content is required" });
  }

  next();
}

function validateComment(req, res, next) {
  const { text } = req.body;

  if (!text) {
    return res.status(400).json({ error: "Text is required" });
  }

  next();
}

function validateMessage(req, res, next) {
  const { text } = req.body;

  if (!text) {
    return res.status(400).json({ error: "Text is required" });
  }

  next();
}

module.exports = {
  validateUser,
  validatePost,
  validateComment,
  validateMessage
};