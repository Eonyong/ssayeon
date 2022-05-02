package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.api.request.article.ArticleUpdateReq;
import a204.ssayeon.api.request.article.CommentCreateReq;
import a204.ssayeon.api.response.article.ArticleRes;
import a204.ssayeon.api.service.ArticleService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.common.model.response.BaseResponseBody;
import a204.ssayeon.config.auth.CurrentUser;
import a204.ssayeon.db.entity.article.Article;
import a204.ssayeon.db.entity.article.ArticleComments;
import a204.ssayeon.db.entity.user.User;
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
    public AdvancedResponseBody<List<ArticleRes>> getAllArticles() {
        List<ArticleRes> articleListRes = articleService.getAllArticles();
        return AdvancedResponseBody.of(Status.OK, articleListRes);
    }

    @PostMapping()
    public AdvancedResponseBody<Article> createArticle(@RequestBody ArticleCreateReq articleCreateReq, @CurrentUser User user) {
        Article article = articleService.createArticle(articleCreateReq, user);
        return AdvancedResponseBody.of(Status.OK, article);
    }

    @GetMapping("/{articleId}")
    public AdvancedResponseBody<ArticleRes> getArticleById(@PathVariable Long articleId) {

        ArticleRes articleRes = articleService.getArticleById(articleId);

        return AdvancedResponseBody.of(Status.OK, articleRes);
    }

    @DeleteMapping("/{articleId}")
    public BaseResponseBody deleteArticleById(@PathVariable Long articleId) {
        Integer statusCode = articleService.deleteArticle(articleId);
        BaseResponseBody baseResponseBody = new BaseResponseBody();

        // 에러처리
        if (statusCode == 404) {
            baseResponseBody.setCode(statusCode);
            baseResponseBody.setMessage("해당 게시글이 없습니다.");
        } else if (statusCode == 200) {
            baseResponseBody.setCode(statusCode);
            baseResponseBody.setMessage("게시글이 삭제되었습니다.");
        }

        return baseResponseBody;
    }

    @PatchMapping("/{articleId}")
    public AdvancedResponseBody<Article> updateArticleById(@PathVariable Long articleId, @RequestBody ArticleUpdateReq articleUpdateReq, @CurrentUser User user) {
        Article article = articleService.updateArticle(articleId, articleUpdateReq, user);

        if (article == null) {

            return AdvancedResponseBody.of(Status.NOT_FOUND, null);
        }
        return AdvancedResponseBody.of(Status.OK, article);

    }

    @GetMapping("/list/{boardId}")
    public AdvancedResponseBody<List<ArticleRes>> getArticlesByBoardId(@PathVariable Long boardId) {
        List<ArticleRes> articleListRes = articleService.getArticlesByBoardId(boardId);
        return AdvancedResponseBody.of(Status.OK, articleListRes);
    }

    @PostMapping("/{articleId}/comment")
    public AdvancedResponseBody<ArticleComments> createComment(@PathVariable Long articleId, @RequestBody CommentCreateReq commentCreateReq) {
        ArticleComments comment = articleService.createComment(articleId, commentCreateReq);
        return AdvancedResponseBody.of(Status.OK, comment);
    }

}
