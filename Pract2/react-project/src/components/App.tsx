import { useState, useEffect } from "react";
import type { Room, Message } from "../types/types.ts";
import RoomList from "./RoomList.tsx";
import MessageList from "./MessageList.tsx";

export default function App() {
  const [rooms, setRooms] = useState<Room[]>([]);
  const [messages, setMessages] = useState<Message[]>([]);
  const [currentRoom, setCurrentRoom] = useState<number | null>(null);

  const addRoom = (name: string, type: string) => {
    const newRoom: Room = {
      id: Date.now(),
      name,
      type,
      createdAt: Date.now(),
    };
    setRooms([...rooms, newRoom]);
  };

  const addMessage = (text: string) => {
    if (currentRoom === null) return;

    const newMessage: Message = {
      id: Date.now(),
      roomId: currentRoom,
      text,
    };

    setMessages([...messages, newMessage]);
  };

  useEffect(() => {
    const savedRooms = localStorage.getItem("rooms");
    const savedMessages = localStorage.getItem("messages");

    if (savedRooms) {
      try {
        setRooms(JSON.parse(savedRooms));
      } catch {
        setRooms([]);
      }
    }

    if (savedMessages) {
      try {
        setMessages(JSON.parse(savedMessages));
      } catch {
        setMessages([]);
      }
    }
  }, []);

  useEffect(() => {
    if (rooms.length > 0) {
      localStorage.setItem("rooms", JSON.stringify(rooms));
    }
  }, [rooms]);

  useEffect(() => {
    if (messages.length > 0) {
      localStorage.setItem("messages", JSON.stringify(messages));
    }
  }, [messages]);

  return (
    <div className="chat-container">
      <RoomList
        rooms={rooms}
        currentRoom={currentRoom}
        setCurrentRoom={setCurrentRoom}
        addRoom={addRoom}
      />

      {rooms.length === 0 ? (
        <div className="empty-state">No rooms yet. Create one.</div>
      ) : currentRoom === null ? (
        <div className="empty-state">Select a room</div>
      ) : (
        <MessageList
          messages={messages}
          currentRoom={currentRoom}
          addMessage={addMessage}
        />
      )}
    </div>
  );
}
