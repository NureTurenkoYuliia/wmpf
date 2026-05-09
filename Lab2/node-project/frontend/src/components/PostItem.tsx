import "../styles/style.css";
import { useState } from "react";
import CommentList from "./CommentList";
import { createComment } from "../services/api";
import { UserName } from "../components/UserName";

export default function PostItem({ post, currentUser }: any) {
  const [showComments, setShowComments] = useState(false);
  const [text, setText] = useState("");

  const handleAdd = async () => {
    if (!text || !currentUser) return;

    await createComment(post.id, currentUser, text);
    setText("");
  };

  return (
    <div className="post">
      <div className="post-header">
        <UserName userId={post.userId} />
      </div>

      <div className="post-content">
        <p>{post.content}</p>
      </div>

      <button
        className="comments-toggle-btn"
        onClick={() => setShowComments(!showComments)}
      >
        {showComments ? "Hide Comments" : `Comments`}
      </button>

      {showComments && (
        <div className="post-comments-section">
          <CommentList postId={post.id} />
          <div className="comment-input">
            <input
              type="text"
              placeholder="Write a comment..."
              value={text}
              onChange={(e) => setText(e.target.value)}
            />
            <button onClick={handleAdd}>Send</button>
          </div>
        </div>
      )}
    </div>
  );
}
