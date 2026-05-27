using LW4.Data.Models;

namespace LW4.Abstractions;

public interface IUserRepository
{
    Task<IEnumerable<User>> GetAllAsync();
    Task<User?> GetByIdAsync(int id);
    Task AddAsync(User user);
    void Update(User user);
    void Delete(User user);
    Task<bool> ExistsAsync(int id);
    Task<bool> SaveChangesAsync();
}
