package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.api.request.article.ArticleUpdateReq;
import a204.ssayeon.api.request.article.CommentCreateReq;
import a204.ssayeon.api.response.article.ArticleCommentsRes;
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
        return AdvancedResponseBody.of(Status.CREATED, article);
    }

    @GetMapping("/{articleId}")
    public AdvancedResponseBody<ArticleRes> getArticleById(@PathVariable Long articleId, @CurrentUser User user) {

        ArticleRes articleRes = articleService.getArticleById(articleId, user);

        return AdvancedResponseBody.of(Status.OK, articleRes);
    }

    @DeleteMapping("/{articleId}")
    public AdvancedResponseBody<String> deleteArticleById(@PathVariable Long articleId, @CurrentUser User user) {
        articleService.deleteArticle(articleId, user);

        return AdvancedResponseBody.of(Status.NO_CONTENT);
    }

    @PatchMapping("/{articleId}")
    public AdvancedResponseBody<Article> updateArticleById(@PathVariable Long articleId, @RequestBody ArticleUpdateReq articleUpdateReq, @CurrentUser User user) {
        Article article = articleService.updateArticle(articleId, articleUpdateReq, user);

        return AdvancedResponseBody.of(Status.OK, article);

    }

    @GetMapping("/list/{boardId}")
    public AdvancedResponseBody<List<ArticleRes>> getArticlesByBoardId(@PathVariable Long boardId) {
        List<ArticleRes> articleListRes = articleService.getArticlesByBoardId(boardId);
        return AdvancedResponseBody.of(Status.OK, articleListRes);
    }

    @PostMapping("/likes/{articleId}")
    public BaseResponseBody likeArticle(@PathVariable Long articleId, @CurrentUser User user) {
        articleService.likeArticle(articleId, user);
        BaseResponseBody baseResponseBody = new BaseResponseBody(Status.OK);
        return baseResponseBody;
    }

    @PostMapping("/scrap/{articleId}")
    public BaseResponseBody scrapArticle(@PathVariable Long articleId, @CurrentUser User user) {
        articleService.scrapArticle(articleId, user);
        BaseResponseBody baseResponseBody = new BaseResponseBody(Status.OK);
        return baseResponseBody;
    }

    @GetMapping("/{articleId}/comments/list")
    public AdvancedResponseBody<List<ArticleCommentsRes>> getCommentsList(@PathVariable Long articleId, @CurrentUser User user) {
        List<ArticleCommentsRes> articleCommentsResList = articleService.getCommentsByArticleId(articleId, user);
        return AdvancedResponseBody.of(Status.OK, articleCommentsResList);
    }

    @PostMapping("/{articleId}/comments")
    public AdvancedResponseBody<ArticleComments> createComment(@PathVariable Long articleId, @RequestBody CommentCreateReq commentCreateReq, @CurrentUser User user) {
        ArticleComments comment = articleService.createComment(articleId, commentCreateReq, user);
        return AdvancedResponseBody.of(Status.OK, comment);
    }

    @PatchMapping("/comments/{commentId}")
    public AdvancedResponseBody<ArticleComments> updateComment(@PathVariable Long commentId, @RequestBody CommentCreateReq commentCreateReq, @CurrentUser User user) {
        ArticleComments articleComment = articleService.updateComment(commentId, commentCreateReq, user);
        return AdvancedResponseBody.of(Status.OK, articleComment);
    }

    @DeleteMapping("/comments/{commentId}")
    public AdvancedResponseBody<ArticleComments> deleteComment(@PathVariable Long commentId, @CurrentUser User user) {
        articleService.deleteComment(commentId, user);
        return AdvancedResponseBody.of(Status.NO_CONTENT);
    }

    @PostMapping("/comments/likes/{commentId}")
    public AdvancedResponseBody<String> likeComment(@PathVariable Long commentId, @CurrentUser User user){
        articleService.likeComment(commentId, user);
        return AdvancedResponseBody.of(Status.OK);
    }
}
