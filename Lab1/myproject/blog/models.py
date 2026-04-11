from django.db import models

# 3.1 Django-модель Категорія
class Category(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField(blank=True)

    def __str__(self):
        return self.name

class Article(models.Model):
    title = models.CharField(max_length=200)
    content = models.TextField()
    author = models.CharField(max_length=200)
    created_at = models.DateField()

    category = models.ForeignKey(
        Category,
        on_delete=models.CASCADE,
        related_name='articles'
    )

    def __str__(self):
        return self.title
    
# 3.2 Django-модель Коментар
class Comment(models.Model):
    article = models.ForeignKey(
        Article,
        on_delete=models.CASCADE,
        related_name='comments'
    )

    author = models.CharField(max_length=100) 
    text = models.TextField()
    created_at = models.DateField()

    def __str__(self):
        return f'Comment by {self.author}'
    