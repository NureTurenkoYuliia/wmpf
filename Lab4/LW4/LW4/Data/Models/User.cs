namespace LW4.Data.Models;

public class User
{
    public int Id { get; set; }
    public string UserName { get; set; } = null!;
    public string Email { get; set; } = null!;
    public DateTime DateOfBirth { get; set; }
    public string? Avatar { get; set; }
    public string? Bio { get; set; }

    public ICollection<Friendship> Friends { get; set; } = new List<Friendship>();
    public ICollection<Post> Posts { get; set; } = new List<Post>();
    public ICollection<Comment> Comments { get; set; } = new List<Comment>();
    public ICollection<Message> Messages { get; set; } = new List<Message>();
}
