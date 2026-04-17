import { useState } from "react";
import { Message } from "../types/types";

type Props = {
  messages: Message[];
  currentRoom: number | null;
  addMessage: (text: string) => void;
};

export default function MessageList({
  messages,
  currentRoom,
  addMessage,
}: Props) {
  const [text, setText] = useState("");

  const filteredMessages = messages.filter((m) => m.roomId === currentRoom);

  const handleSend = () => {
    if (!text.trim()) return;

    addMessage(text);
    setText("");
  };

  return (
    <div className="messages">
      <h2>Messages</h2>
      <ul>
        {filteredMessages.map((msg) => (
          <li key={msg.id}>{msg.text}</li>
        ))}
      </ul>
      <div className="input-area">
        <input
          type="text"
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="Enter message..."
        />
        <button onClick={handleSend}>Send</button>
      </div>
    </div>
  );
}
