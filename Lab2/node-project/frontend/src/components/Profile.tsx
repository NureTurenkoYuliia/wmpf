import "../styles/style.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getUsers, addFriend, getOrCreatePrivateChat } from "../services/api";
import Chat from "../components/Chat";
import { useNavigate } from "react-router-dom";

export default function Profile() {
  const { id } = useParams();
  const currentUser = Number(id);

  const [users, setUsers] = useState<any[]>([]);
  const [selectedFriend, setSelectedFriend] = useState<number | null>(null);
  const [activeChat, setActiveChat] = useState<number | null>(null);

  const navigate = useNavigate();
  
  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const data = await getUsers();
    setUsers(data);
  };

  const current = users.find((u) => u.id === currentUser);

  const handleAddFriend = async () => {
    if (!selectedFriend) return;

    await addFriend(currentUser, selectedFriend);
    setSelectedFriend(null);
    loadUsers();
  };

  const getUserName = (id: number) => {
    const u = users.find((x) => x.id === id);
    return u ? u.name : "Unknown";
  };

  if (!current) return <p>Loading...</p>;

  const handleOpenChat = async (friendId: number) => {
    const chat = await getOrCreatePrivateChat(
      currentUser,
      friendId
    );

    setActiveChat(chat.id);
  };

  return (
    <div>
      <button
        className="back-btn"
        onClick={() => navigate("/")}
      >
        ← Home
      </button>


    <div className="chat-layout">
      <div className="friends-panel">
        <h3>Friends</h3>

        <div className="scroll">
          {current.friends.map((fid: number) => (
            <div
              key={fid}
              className="friend-item"
              onClick={() => handleOpenChat(fid)}
            >
              {getUserName(fid)}
            </div>
          ))}
        </div>

        <div className="input-area">
          <select
            value={selectedFriend || ""}
            onChange={(e) => setSelectedFriend(Number(e.target.value))}
          >
            <option value="">Add friend</option>
            {users
              .filter((u) => u.id !== currentUser)
              .map((u) => (
                <option key={u.id} value={u.id}>
                  {u.name}
                </option>
              ))}
          </select>

          <button onClick={handleAddFriend}>Add</button>
        </div>
      </div>

      <div className="chat-panel">
        {activeChat && (
          <Chat
            currentUser={currentUser}
            chatId={activeChat}
          />
        )}
      </div>
    </div>
        </div>
  );
}