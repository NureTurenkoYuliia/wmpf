using LW4.Data.Models;

namespace LW4.Abstractions;

public interface IRequestLogRepository
{
    Task AddAsync(RequestLog log);
    Task<IEnumerable<RequestLog>> GetPagedLogsAsync(int page, int pageSize);
    Task<IEnumerable<RequestLog>> GetSlowRequestsAsync(long minDurationMs);
    Task<IEnumerable<RequestLog>> GetLogsByUserIdAsync(int userId);
    Task<bool> SaveChangesAsync();
}
