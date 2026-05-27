using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs;
using MediatR;

namespace LW4.Features.SearchUsers;

public class GetUsersBySearchQueryHandler : IRequestHandler<GetUsersBySearchQuery, IEnumerable<UserDto>>
{
    private readonly IUserRepository _userRepository;

    public GetUsersBySearchQueryHandler(IUserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public async Task<IEnumerable<UserDto>> Handle(GetUsersBySearchQuery request, CancellationToken cancellationToken)
    {
        if (string.IsNullOrWhiteSpace(request.SearchTerm))
        {
            return Enumerable.Empty<UserDto>();
        }

        var term = request.SearchTerm.Trim().ToLower();
        IEnumerable<User> users = await _userRepository.GetAllAsync();

        return users
            .Where(u => u.UserName.ToLower().Contains(term))
            .Select(u => new UserDto(u.Id, u.UserName, u.Email, u.Avatar))
            .ToList();
    }
}