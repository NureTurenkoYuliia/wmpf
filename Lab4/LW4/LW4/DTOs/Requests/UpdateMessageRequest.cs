using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class UpdateMessageRequest
{
    [Required(ErrorMessage = "ID повідомлення є обов'язковим для оновлення.")]
    public int Id { get; set; }

    [Required(ErrorMessage = "Текст повідомлення не може бути пустим.")]
    [StringLength(4000, ErrorMessage = "Повідомлення не може перевищувати 4000 символів.")]
    public string Text { get; set; } = null!;
}
