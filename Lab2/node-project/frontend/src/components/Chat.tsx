import "../styles/style.css";
import { useEffect, useState } from "react";
import { getMessages, sendMessage } from "../services/api";

export default function Chat({ currentUser, chatId }: any) {
  const [messages, setMessages] = useState<any[]>([]);
  const [text, setText] = useState("");

  useEffect(() => {
    loadMessages();
  }, [chatId]);

  const loadMessages = async () => {
    const data = await getMessages(chatId);
    setMessages(data);
  };

  const handleSend = async () => {
    if (!text) return;

    await sendMessage(chatId, currentUser, text);
    setText("");
    loadMessages();
  };

  return (
    <div className="chat-box">
      <div className="messages scroll">
        {messages.map((m) => (
          <div
            key={m.id}
            className={
              m.senderId === currentUser ? "msg my" : "msg other"
            }
          >
            {m.text}
          </div>
        ))}
      </div>

      <div className="message-input">
        <input
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="Message..."
        />
        <button onClick={handleSend}>Send</button>
      </div>
    </div>
  );
}