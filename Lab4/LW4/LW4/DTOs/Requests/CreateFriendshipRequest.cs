using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class CreateFriendshipRequest
{
    [Required(ErrorMessage = "ID першого користувача є обов'язковим.")]
    public int User1Id { get; set; }

    [Required(ErrorMessage = "ID другого користувача є обов'язковим.")]
    public int User2Id { get; set; }
}
