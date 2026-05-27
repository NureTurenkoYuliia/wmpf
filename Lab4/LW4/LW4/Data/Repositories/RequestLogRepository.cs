using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data.Repositories;

public class RequestLogRepository : IRequestLogRepository
{
    private readonly AppDbContext _db;

    public RequestLogRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task AddAsync(RequestLog log)
        => await _db.RequestLogs.AddAsync(log);

    public async Task<IEnumerable<RequestLog>> GetPagedLogsAsync(int page, int pageSize)
    {
        return await _db.RequestLogs
            .OrderByDescending(l => l.Timestamp)
            .Skip((page - 1) * pageSize)
            .Take(pageSize)
            .ToListAsync();
    }

    public async Task<IEnumerable<RequestLog>> GetSlowRequestsAsync(long minDurationMs)
    {
        return await _db.RequestLogs
            .Where(l => l.DurationMs >= minDurationMs)
            .OrderByDescending(l => l.DurationMs)
            .Take(100)
            .ToListAsync();
    }

    public async Task<IEnumerable<RequestLog>> GetLogsByUserIdAsync(int userId)
    {
        return await _db.RequestLogs
            .Where(l => l.UserId == userId)
            .OrderByDescending(l => l.Timestamp)
            .Take(100)
            .ToListAsync();
    }

    public async Task<bool> SaveChangesAsync()
        => await _db.SaveChangesAsync() > 0;
}