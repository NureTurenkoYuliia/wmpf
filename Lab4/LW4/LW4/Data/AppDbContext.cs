using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace LW4.Data;

public class AppDbContext : DbContext
{
    public DbSet<User> Users { get; set; }
    public DbSet<Friendship> Friendships { get; set; }
    public DbSet<Post> Posts { get; set; }
    public DbSet<Comment> Comments { get; set; }
    public DbSet<Chat> Chats { get; set; }
    public DbSet<ChatUser> ChatUsers { get; set; }
    public DbSet<Message> Messages { get; set; }
    public DbSet<RequestLog> RequestLogs { get; set; }

    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    protected override void OnModelCreating(ModelBuilder builder)
    {
        base.OnModelCreating(builder);

        builder.HasDefaultSchema("LW4");
        builder.ApplyConfigurationsFromAssembly(typeof(AppDbContext).Assembly,
            t => t.Namespace == "LW4.Data.Configurations");
    }
}