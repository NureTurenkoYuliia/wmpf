import "../styles/style.css";
import { useEffect, useState } from "react";
import { getPosts } from "../services/api";
import PostItem from "./PostItem";
import AddPost from "./AddPost";
import { Post } from "../types/post"

export default function PostList({ currentUser }: any) {
  const [posts, setPosts] = useState<Post[]>([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    loadPosts();
  }, []);

  const loadPosts = async () => {
    const data = await getPosts();
    setPosts(data);
  };

  const filtered = posts.filter((p) =>
    p.content.toLowerCase().includes(search.toLowerCase()),
  );

  return (
    <div>
      <h2>Posts</h2>

      <AddPost reload={loadPosts} currentUser={currentUser} />

      <div className="search-container">
        <input
          className="search-input"
          placeholder="Search posts..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      {filtered.map((post) => (
        <PostItem key={post.id} post={post} currentUser={currentUser} />
      ))}
    </div>
  );
}
