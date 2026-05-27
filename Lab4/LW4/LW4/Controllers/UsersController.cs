using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs.Requests;
using LW4.Features.SearchUsers;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace LW4.Controllers;

[ApiController]
[Route("api/[controller]")]
public class UsersController : ControllerBase
{
    private readonly IMediator _mediator;
    private readonly IUserRepository _userRepository;
    public UsersController(IMediator mediator, IUserRepository userRepository)
    {
        _mediator = mediator;
        _userRepository = userRepository;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<User>>> GetAll()
    {
        var users = await _userRepository.GetAllAsync();
        return Ok(users);
    }

    [HttpGet("{id:int}")]
    public async Task<ActionResult<User>> GetById(int id)
    {
        var user = await _userRepository.GetByIdAsync(id);
        if (user == null) return NotFound($"Користувача з ID {id} не знайдено.");

        return Ok(user);
    }

    [HttpPost]
    public async Task<IActionResult> Create([FromBody] CreateUserRequest request)
    {
        User user = new User
        {
            UserName = request.UserName,
            Email = request.Email,
            DateOfBirth = request.DateOfBirth,
            Avatar = request.Avatar,
            Bio = request.Bio,
        };

        await _userRepository.AddAsync(user);

        var saved = await _userRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося створити користувача.");

        return Ok();
    }

    [HttpPut]
    public async Task<IActionResult> Update([FromBody] UpdateUserRequest request)
    {
        var user = await _userRepository.GetByIdAsync(request.Id);

        if (user == null)
            return NotFound($"Користувача з Id {request.Id} не знайдено.");

        user.UserName = request.UserName;
        user.DateOfBirth = request.DateOfBirth;
        user.Avatar = request.Avatar;
        user.Bio = request.Bio;

        _userRepository.Update(user);

        var saved = await _userRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося оновити користувача або зміни відсутні.");

        return Ok(user);
    }

    [HttpDelete("{id:int}")]
    public async Task<IActionResult> Delete(int id)
    {
        var user = await _userRepository.GetByIdAsync(id);
        if (user == null) return NotFound($"Користувача з ID {id} не знайдено.");

        _userRepository.Delete(user);

        var saved = await _userRepository.SaveChangesAsync();
        if (!saved) return BadRequest("Не вдалося видалити користувача.");

        return Ok();
    }

    [HttpGet("search")]
    public async Task<IActionResult> SearchUsers([FromQuery] string term)
    {
        var query = new GetUsersBySearchQuery(term);
        var result = await _mediator.Send(query);
        return Ok(result);
    }
}