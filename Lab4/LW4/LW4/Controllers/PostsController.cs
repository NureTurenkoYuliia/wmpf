using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs.Requests;
using LW4.Features.SearchPosts;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class PostsController : ControllerBase
{
    private readonly IMediator _mediator; 
    private readonly IPostRepository _postRepository;

    public PostsController(IMediator mediator, IPostRepository postRepository)
    {
        _mediator = mediator;
        _postRepository = postRepository;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Post>>> GetAll()
    {
        var posts = await _postRepository.GetAllAsync();
        return Ok(posts);
    }

    [HttpPost]
    public async Task<IActionResult> Create([FromBody] CreatePostRequest request)
    {
        Post post = new Post
        {
            UserId = request.UserId,
            Content = request.Content,
            CreatedAt = DateTime.UtcNow
        };

        await _postRepository.AddAsync(post);

        var saved = await _postRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося створить пост.");

        return Ok(post);
    }

    [HttpPut]
    public async Task<IActionResult> Update([FromBody] UpdatePostRequest request)
    {
        var post = await _postRepository.GetByIdAsync(request.Id);

        if (post == null)
            return NotFound($"Пост з Id {request.Id} не знайдено.");

        post.Content = request.Content;

        _postRepository.Update(post);

        var saved = await _postRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося оновити пост або зміни відсутні.");

        return Ok(post);
    }

    [HttpDelete("{id:int}")]
    public async Task<IActionResult> Delete(int id)
    {
        var post = await _postRepository.GetByIdAsync(id);
        if (post == null) return NotFound($"Пост з ID {id} не знайдено.");

        _postRepository.Delete(post);

        var saved = await _postRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити пост.");

        return Ok();
    }

    [HttpGet("search")]
    public async Task<IActionResult> SearchPosts([FromQuery] string? searchTerm)
    {
        var query = new GetPostsBySearchQuery(searchTerm);
        var result = await _mediator.Send(query);
        return Ok(result);
    }
}