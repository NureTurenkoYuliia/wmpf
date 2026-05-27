using LW4.Abstractions;
using LW4.Data.Enums;
using LW4.Data.Models;
using LW4.DTOs;
using MediatR;

namespace LW4.Features.CreateChat;

public class CreateChatCommandHandler : IRequestHandler<CreateChatCommand, ChatResultDto>
{
    private readonly IChatRepository _chatRepository;

    public CreateChatCommandHandler(IChatRepository chatRepository)
    {
        _chatRepository = chatRepository;
    }

    public async Task<ChatResultDto> Handle(CreateChatCommand request, CancellationToken cancellationToken)
    {
        if (request.UserIds == null || request.UserIds.Count < 2)
        {
            return new ChatResultDto(0, string.Empty, request.Type.ToString(), false, "Чат повинен мати принаймні 2 учасників.");
        }

        string chatName = request.Name ?? "New Chat";

        if (request.Type == ChatType.Private)
        {
            if (request.UserIds.Count != 2)
            {
                return new ChatResultDto(0, string.Empty, request.Type.ToString(), false, "Приватний чат може мати строго 2 учасників.");
            }

            bool chatExists = await _chatRepository.PrivateChatExistsAsync(request.UserIds[0], request.UserIds[1]);

            if (chatExists)
            {
                return new ChatResultDto(0, string.Empty, request.Type.ToString(), false, "Приватний чат між цими користувачами вже існує.");
            }

            chatName = "private";
        }
        else
        {
            if (string.IsNullOrWhiteSpace(request.Name))
            {
                return new ChatResultDto(0, string.Empty, request.Type.ToString(), false, "Для групового чату обов'язково вказати назву.");
            }
        }

        var newChat = new Chat
        {
            Name = chatName,
            Type = request.Type,
            CreatedAt = DateTime.UtcNow
        };

        await _chatRepository.AddAsync(newChat, request.UserIds);
        await _chatRepository.SaveChangesAsync();

        return new ChatResultDto(newChat.Id, newChat.Name, newChat.Type.ToString(), true);
    }
}