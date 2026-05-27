using LW4.Data.Enums;

namespace LW4.Data.Models;

public class Chat
{
    public int Id { get; set; }
    public string Name { get; set; } = null!;
    public ChatType Type { get; set; }
    public DateTime CreatedAt { get; set; }

    public ICollection<ChatUser> ChatUsers { get; set; } = new List<ChatUser>();
    public ICollection<Message> Messages { get; set; } = new List<Message>();
}
