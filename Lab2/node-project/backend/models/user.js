function createUser(name) {
  return {
    id: Date.now(),
    name,
    friends: []
  };
}

module.exports = createUser;