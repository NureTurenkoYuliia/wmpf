import { useState } from "react";
import { Room } from "../types/types";

type Props = {
  rooms: Room[];
  currentRoom: number | null;
  setCurrentRoom: (id: number) => void;
  addRoom: (name: string, type: string) => void;
};

export default function RoomList({
  rooms,
  currentRoom,
  setCurrentRoom,
  addRoom,
}: Props) {
  const [roomName, setRoomName] = useState("");
  const [roomType, setRoomType] = useState("public");
  const [search, setSearch] = useState("");
  const [sort, setSort] = useState("newest");
  const [filterType, setFilterType] = useState("all");

  const handleAddRoom = () => {
    if (!roomName.trim()) return;

    addRoom(roomName, roomType);
    setRoomName("");
  };

  let filteredRooms = [...rooms];

  filteredRooms = filteredRooms.filter((r) =>
    r.name.toLowerCase().includes(search.toLowerCase())
  );

  if (filterType !== "all") {
    filteredRooms = filteredRooms.filter((r) => r.type === filterType);
  }

  filteredRooms = filteredRooms.sort((a, b) =>
    sort === "newest"
      ? b.createdAt - a.createdAt
      : a.createdAt - b.createdAt
  );

  return (
    <div className="rooms">
      <h2>Rooms</h2>
      <div className="controls">
        <input
          type="text"
          placeholder="Search room..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        <select value={filterType} onChange={(e) => setFilterType(e.target.value)}>
          <option value="all">All</option>
          <option value="public">Public</option>
          <option value="private">Private</option>
        </select>

        <select value={sort} onChange={(e) => setSort(e.target.value)}>
          <option value="newest">Newest</option>
          <option value="oldest">Oldest</option>
        </select>
      </div>

      <ul>
        {filteredRooms.map((room) => (
          <li
            key={room.id}
            onClick={() => setCurrentRoom(room.id)}
            style={{
              cursor: "pointer",
              fontWeight: currentRoom === room.id ? "bold" : "normal",
            }}
          >
            {room.name} ({room.type})
          </li>
        ))}
      </ul>

      <div className="input-area">
        <input
          type="text"
          placeholder="Room name"
          value={roomName}
          onChange={(e) => setRoomName(e.target.value)}
        />

        <select value={roomType} onChange={(e) => setRoomType(e.target.value)}>
          <option value="public">Public</option>
          <option value="private">Private</option>
        </select>

        <button onClick={handleAddRoom}>Create</button>
      </div>
    </div>
  );
}