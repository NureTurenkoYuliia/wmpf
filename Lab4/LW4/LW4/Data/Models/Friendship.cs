namespace LW4.Data.Models;

public class Friendship
{
    public int Id { get; set; }
    public int User1Id { get; set; }
    public virtual User User1 { get; set; } = null!;

    public int User2Id { get; set; }
    public virtual User User2 { get; set; } = null!;

    public DateTime CreatedAt { get; set; } = DateTime.Now;
}
