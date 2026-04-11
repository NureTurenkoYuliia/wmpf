from django.core.paginator import Paginator
from django.shortcuts import render
from .models import Article

def article_list(request):
    articles = Article.objects.all().order_by('-created_at')

    # 3.4 Пошук статей за ключовими словами
    query = request.GET.get('q')
    no_results = False

    if query:
        articles = articles.filter(
            title__icontains=query
        ) | articles.filter(
            content__icontains=query
        )

    if not articles.exists():
        no_results = True
        return render(request, 'blog/article_list.html', {
            'no_results': True,
            'query': query
        })

    # 3.3 Пагінація списку статей
    paginator = Paginator(articles, 5)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    return render(request, 'blog/article_list.html', {
        'page_obj': page_obj,
        'query': query,
        'no_results': no_results
    })