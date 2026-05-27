namespace LW4.Data.Models;

public class Comment
{
    public int Id { get; set; }
    public int UserId { get; set; }
    public virtual User User { get; set; } = null!;

    public int PostId { get; set; }
    public virtual Post Post { get; set; } = null!;

    public string Content { get; set; } = null!;
    public DateTime CreatedAt { get; set; } = DateTime.Now;
}
