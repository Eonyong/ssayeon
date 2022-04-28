package a204.ssayeon.api.service;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.db.entity.article.Article;
import a204.ssayeon.db.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    @Override
    public Article createArticle(ArticleCreateReq articleCreateReq) {
        Article article = Article.builder()
                .title(articleCreateReq.getTitle())
                .content(articleCreateReq.getContent())
                .build();
        article = articleRepository.save(article);
        return article;
    }

    @Override
    public Article getArticleById(Long articleId) {
        Article article = articleRepository.getById(articleId);
        return article;
    }

    @Override
    public Article updateArticle() {
        return null;
    }

    @Override
    public Article deleteArticle() {
        return null;
    }
}
