using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class CommentRepository : ICommentRepository
{
    private readonly AppDbContext _db;
    public CommentRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<Comment?> GetByIdAsync(int id) 
        => await _db.Comments.FindAsync(id);

    public async Task<IEnumerable<Comment>> GetByPostIdAsync(int postId) 
        => await _db.Comments.Where(c => c.PostId == postId).ToListAsync();

    public async Task AddAsync(Comment comment) 
        => await _db.Comments.AddAsync(comment);

    public void Update(Comment comment) 
        => _db.Comments.Update(comment);

    public void Delete(Comment comment) 
        => _db.Comments.Remove(comment);

    public async Task<bool> SaveChangesAsync() 
        => await _db.SaveChangesAsync() > 0;
}