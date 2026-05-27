using LW4.Abstractions;
using LW4.Data;
using LW4.Data.Repositories;
using LW4.Features.CreateChat;
using Microsoft.EntityFrameworkCore;

namespace LW4.Extensions;

public static class DependencyInjection
{
    public static IServiceCollection AddProjectServices(this IServiceCollection services, IConfiguration configuration)
    {
        var connectionString = configuration.GetConnectionString("DefaultConnection");

        services.AddDbContext<AppDbContext>(options => options.UseSqlServer(connectionString));

        services.AddMediatR(cfg => {
            cfg.RegisterServicesFromAssembly(typeof(CreateChatCommand).Assembly);
        });

        services.AddScoped<IUserRepository, UserRepository>();
        services.AddScoped<IChatRepository, ChatRepository>();
        services.AddScoped<IMessageRepository, MessageRepository>();
        services.AddScoped<IFriendshipRepository, FriendshipRepository>();
        services.AddScoped<IPostRepository, PostRepository>();
        services.AddScoped<ICommentRepository, CommentRepository>();
        services.AddScoped<IRequestLogRepository, RequestLogRepository>();

        return services;
    }
}
