package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.api.service.ArticleService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.db.entity.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
    public AdvancedResponseBody<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return AdvancedResponseBody.of(Status.OK, articles);
    }

    @PostMapping()
    public AdvancedResponseBody<Article> createArticle(@RequestBody ArticleCreateReq articleCreateReq) {
        Article article = articleService.createArticle(articleCreateReq);
        return AdvancedResponseBody.of(Status.OK, article);
    }

    @GetMapping("{articleId}")
    public AdvancedResponseBody<Article> getArticleById(@PathVariable Long articleId) {
        System.out.println("articleId = " + articleId);
        Article article = articleService.getArticleById(articleId);
        return AdvancedResponseBody.of(Status.OK, article);
    }
}
