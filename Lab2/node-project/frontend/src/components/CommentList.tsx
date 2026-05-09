import "../styles/style.css";
import { useEffect, useState } from "react";
import { getComments } from "../services/api";
import { Comment } from "../types/comment";
import { UserName } from "../components/UserName";

export default function CommentList({ postId }: any) {
  const [comments, setComments] = useState<Comment[]>([]);

  useEffect(() => {
    loadComments();
  }, [postId]);

  const loadComments = async () => {
    const data = await getComments(postId);
    setComments(data);
  };

  return (
    <div className="comments-container">
      {comments.map((c) => (
        <div key={c.id} className="comment-item">
          <div className="comment-header">
            <UserName userId={c.userId} />
          </div>
          <p className="comment-text">{c.text}</p>
        </div>
      ))}
    </div>
  );
}
