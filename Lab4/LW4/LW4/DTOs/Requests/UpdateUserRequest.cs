using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class UpdateUserRequest
{
    [Required(ErrorMessage = "Id користувача є обов'язковим для оновлення.")]
    public int Id { get; set; }

    [Required(ErrorMessage = "Ім'я користувача є обов'язковим.")]
    [StringLength(50, MinimumLength = 3, ErrorMessage = "Ім'я повинно бути від 3 до 50 символів.")]
    public string UserName { get; set; } = null!;

    [Required]
    [DataType(DataType.Date)]
    public DateTime DateOfBirth { get; set; }

    [Url(ErrorMessage = "Посилання на аватар має бути валідним URL.")]
    public string? Avatar { get; set; }

    [StringLength(500, ErrorMessage = "Біографія не може перевищувати 500 символів.")]
    public string? Bio { get; set; }
}
