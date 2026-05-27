using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class FriendshipRepository : IFriendshipRepository
{
    private readonly AppDbContext _db;
    public FriendshipRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<IEnumerable<Friendship>> GetAllAsync() 
        => await _db.Friendships.ToListAsync();

    public async Task<Friendship?> GetByIdAsync(int id) 
        => await _db.Friendships.FindAsync(id);

    public async Task AddAsync(Friendship friendship) 
        => await _db.Friendships.AddAsync(friendship);

    public void Delete(Friendship friendship) 
        => _db.Friendships.Remove(friendship);

    public async Task<bool> FriendshipExistsAsync(int user1Id, int user2Id)
    {
        return await _db.Friendships.AnyAsync(f =>
            (f.User1Id == user1Id && f.User2Id == user2Id) ||
            (f.User1Id == user2Id && f.User2Id == user1Id));
    }

    public async Task<bool> SaveChangesAsync() 
        => await _db.SaveChangesAsync() > 0;
}