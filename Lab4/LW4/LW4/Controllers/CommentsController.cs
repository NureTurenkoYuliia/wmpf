using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs.Requests;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class CommentsController : ControllerBase
{
    private readonly ICommentRepository _commentRepository;

    public CommentsController(ICommentRepository commentRepository)
    {
        _commentRepository = commentRepository;
    }

    [HttpGet("post/{postId:int}")]
    public async Task<ActionResult<IEnumerable<Comment>>> GetByPostId(int postId)
    {
        var comments = await _commentRepository.GetByPostIdAsync(postId);
        return Ok();
    }

    [HttpPost]
    public async Task<IActionResult> Create([FromBody] CreateCommentRequest request)
    {
        Comment comment = new Comment
        {
            UserId = request.UserId,
            PostId = request.PostId,
            Content = request.Content,
            CreatedAt = DateTime.UtcNow
        };

        await _commentRepository.AddAsync(comment);

        var saved = await _commentRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося додати коментар.");

        return Ok(comment);
    }

    [HttpPut]
    public async Task<IActionResult> Update([FromBody] UpdateCommentRequest request)
    {
        var comment = await _commentRepository.GetByIdAsync(request.Id);

        if (comment == null)
            return NotFound($"Коментар з Id {request.Id} не знайдено.");

        comment.Content = request.Content;

        _commentRepository.Update(comment);

        var saved = await _commentRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося оновити коментар або зміни відсутні.");

        return Ok(comment);
    }

    [HttpDelete("{id:int}")]
    public async Task<IActionResult> Delete(int id)
    {
        var comment = await _commentRepository.GetByIdAsync(id);
        if (comment == null) return NotFound($"Коментар з ID {id} не знайдено.");

        _commentRepository.Delete(comment);

        var saved = await _commentRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити коментар.");

        return Ok();
    }
}