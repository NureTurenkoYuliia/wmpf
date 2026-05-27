using LW4.Data.Enums;
using System.ComponentModel.DataAnnotations;

namespace LW4.DTOs.Requests;

public class UpdateChatRequest
{
    [Required(ErrorMessage = "ID чату є обов'язковим для оновлення.")]
    public int Id { get; set; }

    [Required(ErrorMessage = "Назва чату є обов'язковою.")]
    [StringLength(100, MinimumLength = 1, ErrorMessage = "Назва чату має бути від 1 до 100 символів.")]
    public string Name { get; set; } = null!;

    [Required(ErrorMessage = "Тип чату є обов'язковим.")]
    public ChatType Type { get; set; }
}
