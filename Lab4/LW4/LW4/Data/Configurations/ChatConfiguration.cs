using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace LW4.Data.Configurations;

public class ChatConfiguration : IEntityTypeConfiguration<Chat>
{
    public void Configure(EntityTypeBuilder<Chat> builder)
    {
        builder.HasKey(c => c.Id);

        builder.Property(c => c.Name)
            .IsRequired().HasMaxLength(100);

        builder.Property(c => c.Type)
            .HasConversion<int>().IsRequired();

        builder.Property(c => c.CreatedAt)
            .HasDefaultValueSql("GETUTCDATE()");
    }
}