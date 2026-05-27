using Azure.Core;
using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs.Requests;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class MessagesController : ControllerBase
{
    private readonly IMessageRepository _messageRepository;

    public MessagesController(IMessageRepository messageRepository)
    {
        _messageRepository = messageRepository;
    }

    [HttpGet("chat/{chatId:int}")]
    public async Task<ActionResult<IEnumerable<Message>>> GetByChatId(int chatId)
    {
        var messages = await _messageRepository.GetByChatIdAsync(chatId);
        return Ok();
    }

    [HttpPost]
    public async Task<IActionResult> CreateMessage([FromBody] CreateMessageRequest request)
    {
        Message message = new Message
        {
            ChatId = request.ChatId,
            UserId = request.UserId,
            Text = request.Text,
            CreatedAt = DateTime.UtcNow
        };

        await _messageRepository.AddAsync(message);

        var saved = await _messageRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося відправити повідомлення.");

        return Ok(message);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateMessage([FromBody] UpdateMessageRequest request)
    {
        var message = await _messageRepository.GetByIdAsync(request.Id);

        if (message == null)
            return NotFound($"Повідомлення з Id {request.Id} не знайдено.");

        message.Text = request.Text;

        _messageRepository.Update(message);

        var saved = await _messageRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося оновити повідомлення або зміни відсутні.");

        return Ok(message);
    }

    [HttpDelete]
    public async Task<IActionResult> DeleteMessage(int id)
    {
        var message = await _messageRepository.GetByIdAsync(id);
        _messageRepository.Delete(message);

        var saved = await _messageRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити повідомлення.");

        return Ok();
    }
}