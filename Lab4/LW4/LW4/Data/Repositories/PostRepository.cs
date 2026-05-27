using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class PostRepository : IPostRepository
{
    private readonly AppDbContext _db;
    public PostRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<IEnumerable<Post>> GetAllAsync() 
        => await _db.Posts.ToListAsync();

    public async Task<Post?> GetByIdAsync(int id) 
        => await _db.Posts.FindAsync(id);

    public async Task AddAsync(Post post) 
        => await _db.Posts.AddAsync(post);

    public void Update(Post post) 
        => _db.Posts.Update(post);

    public void Delete(Post post) 
        => _db.Posts.Remove(post);

    public async Task<bool> SaveChangesAsync() 
        => await _db.SaveChangesAsync() > 0;
}