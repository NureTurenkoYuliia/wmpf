namespace LW4.Data.Models;

public class Message
{
    public int Id { get; set; }
    public int ChatId { get; set; }
    public virtual Chat Chat { get; set; } = null!;

    public int UserId { get; set; }
    public virtual User User { get; set; } = null!;

    public string Text { get; set; } = null!;
    public DateTime CreatedAt { get; set; }
}
