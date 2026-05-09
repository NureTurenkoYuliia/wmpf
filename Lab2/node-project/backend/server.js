const express = require("express");
const cors = require("cors");
const compression = require("compression");

const logger = require("./middleware/loggerMiddleware");
const errorHandler = require("./middleware/errorMiddleware");

const userRoutes = require("./routes/users");
const postRoutes = require("./routes/posts");
const commentRoutes = require("./routes/comments");
const messageRoutes = require("./routes/messages");
const chatRoutes = require("./routes/chats");

const app = express();
app.use(compression());
app.use(cors());
app.use(express.json());
app.use(logger);

app.use("/users", userRoutes);
app.use("/posts", postRoutes);
app.use("/comments", commentRoutes);
app.use("/messages", messageRoutes);
app.use("/chats", chatRoutes);

app.use(errorHandler);

app.listen(5000, () => {
  console.log("Server running on http://localhost:5000");
});