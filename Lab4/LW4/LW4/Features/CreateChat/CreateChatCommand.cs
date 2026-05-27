using LW4.Data.Enums;
using LW4.DTOs;
using MediatR;

namespace LW4.Features.CreateChat;

public record CreateChatCommand(string? Name, ChatType Type, List<int> UserIds) : IRequest<ChatResultDto>;