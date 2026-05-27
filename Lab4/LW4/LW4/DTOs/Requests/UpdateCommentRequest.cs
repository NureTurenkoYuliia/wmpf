using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class UpdateCommentRequest
{
    [Required(ErrorMessage = "ID коментаря є обов'язковим для оновлення.")]
    public int Id { get; set; }

    [Required(ErrorMessage = "Текст коментаря не може бути пустим.")]
    [StringLength(1000, ErrorMessage = "Коментар не може перевищувати 1000 символів.")]
    public string Content { get; set; } = null!;
}
