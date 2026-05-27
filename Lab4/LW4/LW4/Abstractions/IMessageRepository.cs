using LW4.Data.Models;

namespace LW4.Abstractions;

public interface IMessageRepository
{
    Task<Message?> GetByIdAsync(int id);
    Task<IEnumerable<Message>> GetByChatIdAsync(int chatId);
    Task AddAsync(Message message);
    void Update(Message message);
    void Delete(Message message);
    Task<bool> SaveChangesAsync();
}
