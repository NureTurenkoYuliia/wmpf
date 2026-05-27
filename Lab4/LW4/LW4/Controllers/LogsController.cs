using LW4.Abstractions;
using LW4.Data.Models;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class LogsController : ControllerBase
{
    private readonly IRequestLogRepository _logRepository;

    public LogsController(IRequestLogRepository logRepository)
    {
        _logRepository = logRepository;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<RequestLog>>> GetLogs([FromQuery] int page = 1, [FromQuery] int pageSize = 50)
    {
        if (page < 1) page = 1;
        if (pageSize > 100) pageSize = 100;

        var logs = await _logRepository.GetPagedLogsAsync(page, pageSize);
        return Ok(logs);
    }

    [HttpGet("slow")]
    public async Task<ActionResult<IEnumerable<RequestLog>>> GetSlowRequests([FromQuery] long minDurationMs = 500)
    {
        var slowLogs = await _logRepository.GetSlowRequestsAsync(minDurationMs);
        return Ok(slowLogs);
    }

    [HttpGet("user/{userId:int}")]
    public async Task<ActionResult<IEnumerable<RequestLog>>> GetLogsByUserId(int userId)
    {
        var userLogs = await _logRepository.GetLogsByUserIdAsync(userId);
        return Ok(userLogs);
    }
}
