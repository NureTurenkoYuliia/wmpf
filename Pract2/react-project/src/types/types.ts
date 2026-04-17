export type Room = {
  id: number;
  name: string;
  type: string;  
  createdAt: number; 
};

export type Message = {
  id: number;
  roomId: number;
  text: string;
};