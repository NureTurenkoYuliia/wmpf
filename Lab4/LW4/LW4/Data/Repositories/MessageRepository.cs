using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class MessageRepository : IMessageRepository
{
    private readonly AppDbContext _db;
    public MessageRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<Message?> GetByIdAsync(int id) 
        => await _db.Messages.FindAsync(id);

    public async Task<IEnumerable<Message>> GetByChatIdAsync(int chatId) 
        => await _db.Messages.Where(m => m.ChatId == chatId).OrderBy(m => m.CreatedAt).ToListAsync();

    public async Task AddAsync(Message message) 
        => await _db.Messages.AddAsync(message);

    public void Update(Message message) 
        => _db.Messages.Update(message);

    public void Delete(Message message) 
        => _db.Messages.Remove(message);

    public async Task<bool> SaveChangesAsync() 
        => await _db.SaveChangesAsync() > 0;
}