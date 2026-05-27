using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class CreateUserRequest
{
    [Required(ErrorMessage = "Ім'я користувача є обов'язковим.")]
    [StringLength(50, MinimumLength = 3, ErrorMessage = "Ім'я повинно бути від 3 до 50 символів.")]
    public string UserName { get; set; } = null!;

    [Required(ErrorMessage = "Email є обов'язковим.")]
    [EmailAddress(ErrorMessage = "Некоректний формат Email.")]
    public string Email { get; set; } = null!;

    [Required]
    [DataType(DataType.Date)]
    public DateTime DateOfBirth { get; set; }

    public string? Avatar { get; set; }

    [StringLength(300, ErrorMessage = "Біографія не може перевищувати 300 символів.")]
    public string? Bio { get; set; }
}
