using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace LW4.Data.Configurations;

public class FriendshipConfiguration : IEntityTypeConfiguration<Friendship>
{
    public void Configure(EntityTypeBuilder<Friendship> builder)
    {
        builder.HasKey(f => f.Id);

        builder.Property(f => f.CreatedAt)
            .HasDefaultValueSql("GETUTCDATE()");

        builder.HasOne(f => f.User1)
            .WithMany(u => u.Friends)
            .HasForeignKey(f => f.User1Id)
            .OnDelete(DeleteBehavior.Restrict);

        builder.HasIndex(f => new { f.User1Id, f.User2Id }).IsUnique();
    }
}