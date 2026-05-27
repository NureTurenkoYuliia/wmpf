using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs;
using LW4.DTOs.Requests;
using LW4.Features.CreateChat;
using LW4.Features.GetChatsByUserId;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class ChatsController : ControllerBase
{
    private readonly IMediator _mediator;
    private readonly IChatRepository _chatRepository;
    public ChatsController(IMediator mediator, IChatRepository chatRepository)
    {
        _mediator = mediator;
        _chatRepository = chatRepository;
    }

    [HttpPost]
    public async Task<IActionResult> CreateChat([FromBody] CreateChatCommand command)
    {
        var result = await _mediator.Send(command);

        if (!result.IsSuccess)
        {
            return BadRequest(result.ErrorMessage);
        }

        return Ok(result);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateChat([FromBody] UpdateChatRequest request)
    {
        var chat = await _chatRepository.GetByIdAsync(request.Id);

        if (chat == null)
            return NotFound($"Чат з Id {request.Id} не знайдено.");

        chat.Name = request.Name;
        chat.Type = request.Type;

        _chatRepository.Update(chat);

        var saved = await _chatRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося оновити чат або зміни відсутні.");

        return Ok(chat);
    }

    [HttpDelete]
    public async Task<IActionResult> DeleteChat(int id)
    {
        var chat = await _chatRepository.GetByIdAsync(id);
        _chatRepository.Delete(chat);

        var saved = await _chatRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити чат.");

        return Ok();
    }

    [HttpGet("{userId:int}/chats")]
    public async Task<ActionResult<IEnumerable<ChatDto>>> GetChats(int userId)
    {
        var query = new GetChatsByUserIdQuery(userId);
        var result = await _mediator.Send(query);

        return Ok(result);
    }

    [HttpPost("{chatId:int}/users/{userId:int}")]
    public async Task<IActionResult> AddUserToChat(int chatId, int userId)
    {
        await _chatRepository.AddUserToChatAsync(chatId, userId);

        var saved = await _chatRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося додати користувача до чату.");

        return Ok("Користувача успішно додано до чату.");
    }

    [HttpDelete("{chatId:int}/users/{userId:int}")]
    public async Task<IActionResult> RemoveUserFromChat(int chatId, int userId)
    {
        await _chatRepository.RemoveUserFromChatAsync(chatId, userId);

        var saved = await _chatRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити користувача з чату (або його там не було).");

        return Ok();
    }
}