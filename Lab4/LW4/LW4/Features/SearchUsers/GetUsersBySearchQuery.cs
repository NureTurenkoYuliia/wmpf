using LW4.DTOs;
using MediatR;

namespace LW4.Features.SearchUsers;

public record GetUsersBySearchQuery(string SearchTerm) : IRequest<IEnumerable<UserDto>>;