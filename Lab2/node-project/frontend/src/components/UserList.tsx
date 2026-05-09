import "../styles/style.css";
import { useEffect, useState } from "react";
import { getUsers, createUser } from "../services/api";
import { User } from "../types/user"

export default function UserList({ currentUser, setCurrentUser }: any) {
  const [users, setUsers] = useState<User[]>([]);
  const [name, setName] = useState("");

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const data = await getUsers();
    setUsers(data);
  };

  const handleAddUser = async () => {
    if (!name) return;

    await createUser(name);
    setName("");
    loadUsers();
  };

  const [search, setSearch] = useState("");

  const filteredUsers = users.filter((u) =>
    u.name.toLowerCase().includes(search.toLowerCase()),
  );

  return (
    <div className="users">
      <h2>Users</h2>
      
      <div className="search-container">
        <input
          className="search-input"
          placeholder="Search users..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      <ul>
        {filteredUsers.map((u) => (
          <li key={u.id}>
            <div className="user-row" onClick={() => setCurrentUser(u.id)}>
              <span
                style={{
                  fontWeight: currentUser === u.id ? "bold" : "normal",
                }}
              >
                {u.name}
              </span>
            </div>

          </li>
        ))}
      </ul>

      <div className="user-input">
        <input
          type="text"
          placeholder="New user..."
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <button onClick={handleAddUser}>Add</button>
      </div>
    </div>
  );
}
