using LW4.Data.Models;

namespace LW4.Abstractions;

public interface IChatRepository
{
    Task<IEnumerable<Chat>> GetAllByUserIdAsync(int userId);
    Task<Chat?> GetByIdAsync(int id);
    Task AddAsync(Chat chat, List<int> userIds);
    void Update(Chat chat);
    void Delete(Chat chat);
    Task AddUserToChatAsync(int chatId, int userId);
    Task RemoveUserFromChatAsync(int chatId, int userId);
    Task<bool> PrivateChatExistsAsync(int user1Id, int user2Id);
    Task<bool> SaveChangesAsync();
}
