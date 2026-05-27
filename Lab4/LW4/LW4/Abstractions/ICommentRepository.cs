using LW4.Data.Models;

namespace LW4.Abstractions;

public interface ICommentRepository
{
    Task<Comment?> GetByIdAsync(int id);
    Task<IEnumerable<Comment>> GetByPostIdAsync(int postId);
    Task AddAsync(Comment comment);
    void Update(Comment comment);
    void Delete(Comment comment);
    Task<bool> SaveChangesAsync();
}
