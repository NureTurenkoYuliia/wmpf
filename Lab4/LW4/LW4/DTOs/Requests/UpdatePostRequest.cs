using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class UpdatePostRequest
{
    [Required(ErrorMessage = "ID поста є обов'язковим для оновлення.")]
    public int Id { get; set; }

    [Required(ErrorMessage = "Вміст поста не може бути пустим.")]
    [StringLength(2000, ErrorMessage = "Пост не може перевищувати 2000 символів.")]
    public string Content { get; set; } = null!;
}
