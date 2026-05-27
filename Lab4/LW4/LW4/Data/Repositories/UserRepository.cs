using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class UserRepository : IUserRepository
{
    private readonly AppDbContext _db;
    public UserRepository(AppDbContext db)
    {
        _db = db;
    }
    public async Task<IEnumerable<User>> GetAllAsync() 
        => await _db.Users.ToListAsync();

    public async Task<User?> GetByIdAsync(int id) 
        => await _db.Users.FindAsync(id);

    public async Task AddAsync(User user) 
        => await _db.Users.AddAsync(user);

    public void Update(User user) 
        => _db.Users.Update(user);

    public void Delete(User user) 
        => _db.Users.Remove(user);

    public async Task<bool> ExistsAsync(int id) 
        => await _db.Users.AnyAsync(u => u.Id == id);

    public async Task<bool> SaveChangesAsync() 
        => await _db.SaveChangesAsync() > 0;
}
