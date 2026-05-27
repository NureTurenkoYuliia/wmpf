using Azure.Core;
using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs.Requests;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class FriendshipsController : ControllerBase
{
    private readonly IFriendshipRepository _friendshipRepository;

    public FriendshipsController(IFriendshipRepository friendshipRepository)
    {
        _friendshipRepository = friendshipRepository;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Friendship>>> GetAll()
    {
        var friendships = await _friendshipRepository.GetAllAsync();
        return Ok(friendships);
    }

    [HttpPost]
    public async Task<IActionResult> Create([FromBody] CreateFriendshipRequest request)
    {
        Friendship friendship = new Friendship
        {
            User1Id = request.User1Id,
            User2Id = request.User2Id,
            CreatedAt = DateTime.UtcNow
        };

        await _friendshipRepository.AddAsync(friendship);

        var saved = await _friendshipRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося створити зв'язок дружби.");

        return Ok(friendship);
    }

    [HttpDelete("{id:int}")]
    public async Task<IActionResult> Delete(int id)
    {
        var friendship = await _friendshipRepository.GetByIdAsync(id);
        if (friendship == null) return NotFound($"Зв'язок дружби з ID {id} не знайдено.");

        _friendshipRepository.Delete(friendship);

        var saved = await _friendshipRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити зв'язок дружби.");

        return Ok();
    }
}