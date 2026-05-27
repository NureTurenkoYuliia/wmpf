using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace LW4.Data.Configurations;

public class UserConfiguration : IEntityTypeConfiguration<User>
{
    public void Configure(EntityTypeBuilder<User> builder)
    {
        builder.HasKey(u => u.Id);

        builder.Property(u => u.UserName)
            .IsRequired().HasMaxLength(50);

        builder.Property(u => u.Email)
            .IsRequired().HasMaxLength(100);

        builder.HasIndex(u => u.Email)
            .IsUnique();

        builder.Property(u => u.Avatar)
            .HasMaxLength(500);

        builder.Property(u => u.Bio)
            .HasMaxLength(300);
    }
}