using LW4.Data.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace LW4.Data.Configurations;

public class RequestLogConfiguration : IEntityTypeConfiguration<RequestLog>
{
    public void Configure(EntityTypeBuilder<RequestLog> builder)
    {
        builder.HasKey(u => u.Id);

        builder.Property(u => u.Method)
            .IsRequired().HasMaxLength(10);

        builder.Property(u => u.Path)
            .IsRequired().HasMaxLength(1000);

        builder.Property(u => u.IpAddress)
            .HasMaxLength(45);

        builder.HasIndex(u => u.Timestamp);
    }
}