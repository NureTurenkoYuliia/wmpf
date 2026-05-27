using LW4.Abstractions;
using LW4.Data.Enums;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class ChatRepository : IChatRepository
{
    private readonly AppDbContext _db;
    public ChatRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<IEnumerable<Chat>> GetAllByUserIdAsync(int userId)
    {
        return await _db.Chats
            .Where(c => c.ChatUsers.Any(cu => cu.UserId == userId))
            .Include(c => c.ChatUsers)
                .ThenInclude(cu => cu.User)
            .ToListAsync();
    }

    public async Task<Chat?> GetByIdAsync(int id) 
        => await _db.Chats.Include(c => c.ChatUsers).FirstOrDefaultAsync(c => c.Id == id);

    public async Task AddAsync(Chat chat, List<int> userIds)
    {
        await _db.Chats.AddAsync(chat);

        foreach (var userId in userIds)
        {
            chat.ChatUsers.Add(new ChatUser { UserId = userId });
        }
    }

    public void Update(Chat chat) 
        => _db.Chats.Update(chat);

    public void Delete(Chat chat) 
        => _db.Chats.Remove(chat);

    public async Task AddUserToChatAsync(int chatId, int userId)
    {
        var chatUser = new ChatUser { ChatId = chatId, UserId = userId };
        await _db.ChatUsers.AddAsync(chatUser);
    }

    public async Task RemoveUserFromChatAsync(int chatId, int userId)
    {
        var chatUser = await _db.ChatUsers
            .FirstOrDefaultAsync(cu => cu.ChatId == chatId && cu.UserId == userId);
        if (chatUser != null)
        {
            _db.ChatUsers.Remove(chatUser);
        }
    }

    public async Task<bool> PrivateChatExistsAsync(int user1Id, int user2Id)
    {
        return await _db.Chats
            .Where(c => c.Type == ChatType.Private)
            .AnyAsync(c => c.ChatUsers.Any(cu => cu.UserId == user1Id) &&
                           c.ChatUsers.Any(cu => cu.UserId == user2Id));
    }

    public async Task<bool> SaveChangesAsync() 
        => await _db.SaveChangesAsync() > 0;
}