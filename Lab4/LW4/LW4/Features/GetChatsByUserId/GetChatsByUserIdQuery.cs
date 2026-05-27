using LW4.DTOs;
using MediatR;

namespace LW4.Features.GetChatsByUserId;

public record GetChatsByUserIdQuery(int userId) : IRequest<IEnumerable<ChatDto>>;