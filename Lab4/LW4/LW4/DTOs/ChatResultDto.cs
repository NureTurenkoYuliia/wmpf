namespace LW4.DTOs;

public record ChatResultDto(int Id, string Name, string Type, bool IsSuccess, string? ErrorMessage = null);