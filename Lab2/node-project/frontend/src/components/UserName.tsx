import { useState, useEffect } from "react";
import { getUserName } from "../services/api";

interface UserNameProps {
  userId: number;
}

export const UserName = ({ userId }: UserNameProps) => {
  const [name, setName] = useState<string>("Loading...");
  
  useEffect(() => {
    const fetchName = async () => {
      try {
        const data = await getUserName(userId);
        
        if (data && data.name) {
          setName(data.name);
        } else {
          setName("Unknown User");
        }
      } catch (error) {
        console.error("Помилка при отриманні імені:", error);
        setName("Unknown User");
      }
    };

    if (userId) {
      fetchName();
    }
  }, [userId]);

  return <span className="comment-author">{name}</span>;
};
