using LW4.Abstractions;
using LW4.Data.Models;
using LW4.DTOs;
using MediatR;

namespace LW4.Features.SearchPosts;

public class GetPostsBySearchQueryHandler : IRequestHandler<GetPostsBySearchQuery, IEnumerable<PostDto>>
{
    private readonly IPostRepository _postRepository;

    public GetPostsBySearchQueryHandler(IPostRepository postRepository)
    {
        _postRepository = postRepository;
    }

    public async Task<IEnumerable<PostDto>> Handle(GetPostsBySearchQuery request, CancellationToken cancellationToken)
    {
        IEnumerable<Post> posts = await _postRepository.GetAllAsync();

        if (!string.IsNullOrWhiteSpace(request.SearchTerm))
        {
            var term = request.SearchTerm.Trim().ToLower();
            posts = posts.Where(p => p.Content.ToLower().Contains(term));
        }

        return posts
            .OrderByDescending(p => p.CreatedAt)
            .Select(p => new PostDto(p.Id, p.UserId, p.Content, p.CreatedAt))
            .ToList();
    }
}