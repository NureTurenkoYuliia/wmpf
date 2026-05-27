using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class CreateCommentRequest
{
    [Required(ErrorMessage = "ID користувача є обов'язковим.")]
    public int UserId { get; set; }

    [Required(ErrorMessage = "ID поста є обов'язковим.")]
    public int PostId { get; set; }

    [Required(ErrorMessage = "Текст коментаря не може бути пустим.")]
    [StringLength(1000, ErrorMessage = "Коментар не може перевищувати 1000 символів.")]
    public string Content { get; set; } = null!;
}
