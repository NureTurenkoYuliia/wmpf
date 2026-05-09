const API_URL = "http://localhost:5000";

// USERS
export const getUsers = async () => {
  const res = await fetch(`${API_URL}/users`);
  return res.json();
};

export const createUser = async (name: string) => {
  const res = await fetch(`${API_URL}/users`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name }),
  });
  return res.json();
};

export const addFriend = async (userId: number, friendId: number) => {
  const res = await fetch(`${API_URL}/users/${userId}/add-friend`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ friendId }),
  });
  return res.json();
};

export const getUserName = async (userId: number) => {
  const res = await fetch(`${API_URL}/users/${userId}/name`);
  return res.json();
};

// POSTS
export const getPosts = async () => {
  const res = await fetch(`${API_URL}/posts`);
  return res.json();
};

export const createPost = async (userId: number, content: string) => {
  const res = await fetch(`${API_URL}/posts`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userId, content }),
  });
  return res.json();
};

// COMMENTS
export const getComments = async (postId: number) => {
  const res = await fetch(`${API_URL}/comments/${postId}`);
  return res.json();
};

export const createComment = async (
  postId: number,
  userId: number,
  text: string,
) => {
  const res = await fetch(`${API_URL}/comments`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ postId, userId, text }),
  });
  return res.json();
};

//CHATS
export const getOrCreatePrivateChat = async (
  user1: number,
  user2: number
) => {
  const res = await fetch(
    `${API_URL}/chats/private`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        user1,
        user2,
      }),
    }
  );

  return res.json();
};

// MESSAGES
export const getMessages = async (chatId: number) => {
   const res = await fetch(
    `${API_URL}/messages/chat/${chatId}`
  );

  return res.json();
};

export const sendMessage = async (
  chatId: number,
  senderId: number,
  text: string
) => {
  const res = await fetch(`${API_URL}/messages`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ chatId, senderId, text }),
  });
  return res.json();
};