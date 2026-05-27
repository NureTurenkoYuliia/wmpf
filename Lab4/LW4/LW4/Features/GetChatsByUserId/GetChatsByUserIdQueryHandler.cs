using LW4.Abstractions;
using LW4.Data.Enums;
using LW4.Data.Models;
using LW4.DTOs;
using MediatR;

namespace LW4.Features.GetChatsByUserId;

public class GetChatsByUserIdQueryHandler : IRequestHandler<GetChatsByUserIdQuery, IEnumerable<ChatDto>>
{
    private readonly IUserRepository _userRepository;
    private readonly IChatRepository _chatRepository;

    public GetChatsByUserIdQueryHandler(IUserRepository userRepository, IChatRepository chatRepository)
    {
        _userRepository = userRepository;
        _chatRepository = chatRepository;
    }

    public async Task<IEnumerable<ChatDto>> Handle(GetChatsByUserIdQuery request, CancellationToken cancellationToken)
    {
        bool userExists = await _userRepository.ExistsAsync(request.userId);
        
        if (!userExists)  return [];

        IEnumerable<Chat> chats = await _chatRepository.GetAllByUserIdAsync(request.userId);

        var result = chats.Select(c =>
        {
            string displayName = c.Name;

            if (c.Type == ChatType.Private)
            {
                var otherUser = c.ChatUsers.FirstOrDefault(cu => cu.UserId != request.userId)?.User;

                if (otherUser != null)
                {
                    displayName = otherUser.UserName;
                }
            }

            return new ChatDto(c.Id, displayName);
        }).ToList();

        return result;
    }
}