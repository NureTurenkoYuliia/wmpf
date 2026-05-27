using LW4.Data.Models;

namespace LW4.Abstractions;

public interface IPostRepository
{
    Task<IEnumerable<Post>> GetAllAsync();
    Task<Post?> GetByIdAsync(int id);
    Task AddAsync(Post post);
    void Update(Post post);
    void Delete(Post post);
    Task<bool> SaveChangesAsync();
}
