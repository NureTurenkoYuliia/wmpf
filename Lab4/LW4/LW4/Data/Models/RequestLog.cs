namespace LW4.Data.Models;

public class RequestLog
{
    public int Id { get; set; }
    public string Method { get; set; } = null!;
    public string Path { get; set; } = null!;
    public int StatusCode { get; set; }
    public long DurationMs { get; set; }
    public DateTime Timestamp { get; set; }
    public string? IpAddress { get; set; }
    public int? UserId { get; set; }
}
