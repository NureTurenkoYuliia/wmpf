using LW4.Data.Models;

namespace LW4.Abstractions;

public interface IFriendshipRepository
{
    Task<IEnumerable<Friendship>> GetAllAsync();
    Task<Friendship?> GetByIdAsync(int id);
    Task AddAsync(Friendship friendship);
    void Delete(Friendship friendship);
    Task<bool> FriendshipExistsAsync(int user1Id, int user2Id);
    Task<bool> SaveChangesAsync();
}
