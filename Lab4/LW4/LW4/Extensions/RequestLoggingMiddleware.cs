using LW4.Abstractions;
using LW4.Data;
using LW4.Data.Models;
using System.Diagnostics;
using System.Security.Claims;

namespace LW4.Extensions;

public class RequestLoggingMiddleware
{
    private readonly RequestDelegate _next;

    public RequestLoggingMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context, IRequestLogRepository logRepository)
    {
        var stopwatch = Stopwatch.StartNew();

        try
        {
            await _next(context);
        }
        finally
        {
            stopwatch.Stop();

            int? userId = null;
            var userIdClaim = context.User
                .FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (int.TryParse(userIdClaim, out int parsedId))
            {
                userId = parsedId;
            }

            var log = new RequestLog
            {
                Method = context.Request.Method,
                Path = context.Request.Path.Value ?? "/",
                StatusCode = context.Response.StatusCode,
                DurationMs = stopwatch.ElapsedMilliseconds,
                Timestamp = DateTime.UtcNow,
                IpAddress = context.Connection.RemoteIpAddress?.ToString(),
                UserId = userId
            };

            await logRepository.AddAsync(log);
            await logRepository.SaveChangesAsync();
        }
    }
}
