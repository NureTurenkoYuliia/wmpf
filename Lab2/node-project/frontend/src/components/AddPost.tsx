import "../styles/style.css"
import { useState } from "react";
import { createPost } from "../services/api";

export default function AddPost({ reload, currentUser }: any) {
  const [content, setContent] = useState("");

  const handleAdd = async () => {
    if (!content || !currentUser) return;

    await createPost(currentUser, content);
    setContent("");
    reload();
  };

  return (
    <div className="post-input">
      <input
        type="text"
        placeholder="Post..."
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <button onClick={handleAdd}>Add</button>
    </div>
  );
}