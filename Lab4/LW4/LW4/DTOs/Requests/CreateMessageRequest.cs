using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class CreateMessageRequest
{
    [Required(ErrorMessage = "ID чату є обов'язковим.")]
    public int ChatId { get; set; }

    [Required(ErrorMessage = "ID користувача є обов'язковим.")]
    public int UserId { get; set; }

    [Required(ErrorMessage = "Текст повідомлення не може бути пустим.")]
    [StringLength(4000, ErrorMessage = "Повідомлення не може перевищувати 4000 символів.")]
    public string Text { get; set; } = null!;
}
