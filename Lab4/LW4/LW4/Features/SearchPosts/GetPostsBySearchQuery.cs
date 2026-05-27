using LW4.DTOs;
using MediatR;

namespace LW4.Features.SearchPosts;

public record GetPostsBySearchQuery(string? SearchTerm) : IRequest<IEnumerable<PostDto>>;