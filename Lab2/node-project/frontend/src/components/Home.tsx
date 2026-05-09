import "../styles/style.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import UserList from "../components/UserList";
import PostList from "../components/PostList";

export default function Home() {
  const [currentUser, setCurrentUser] = useState<number | null>(null);
  const navigate = useNavigate();

  return (
    <div className="layout">
      <div className="sidebar">
        <UserList
          currentUser={currentUser}
          setCurrentUser={setCurrentUser}
        />

        {currentUser && (
          <button
            className="open-profile"
            onClick={() => navigate(`/profile/${currentUser}`)}
          >
            Open Friends & Chat
          </button>
        )}
      </div>

      <div className="content">
        {currentUser ? (
          <PostList currentUser={currentUser} />
        ) : (
          <p>Select user</p>
        )}
      </div>
    </div>
  );
}