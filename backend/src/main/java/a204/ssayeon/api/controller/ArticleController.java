package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.article.*;
import a204.ssayeon.api.response.article.ArticleAnswerRes;
import a204.ssayeon.api.response.article.ArticleCommentsRes;
import a204.ssayeon.api.response.article.ArticleRes;
import a204.ssayeon.api.service.ArticleService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.common.model.response.BaseResponseBody;
import a204.ssayeon.common.model.response.PaginationResponseBody;
import a204.ssayeon.config.auth.CurrentUser;
import a204.ssayeon.db.entity.Pagination;
import a204.ssayeon.db.entity.article.Article;
import a204.ssayeon.db.entity.article.ArticleAnswer;
import a204.ssayeon.db.entity.article.ArticleComments;
import a204.ssayeon.db.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public AdvancedResponseBody<List<ArticleRes>> getAllArticles(@PageableDefault(sort="id",direction = Sort.Direction.DESC,size=10) Pageable pageable) {
        Page<Article> articles = articleService.getAllArticles(pageable);
        List<ArticleRes> articleListRes = new ArrayList<>();
        Pagination pagination = Pagination.getPagination(articles);
        for(Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(articleService.getBoardRes(article.getBoard().getId()))
                    .category(articleService.getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return PaginationResponseBody.of(Status.OK, articleListRes, pagination);
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
    public AdvancedResponseBody<List<ArticleRes>> getArticlesByBoardId(@PathVariable Long boardId, @PageableDefault(sort="id",direction = Sort.Direction.DESC,size=10) Pageable pageable) {
        Page<Article> articles = articleService.getArticlesByBoardId(boardId, pageable);
        Pagination pagination = Pagination.getPagination(articles);
        List<ArticleRes> articleListRes = new ArrayList<>();
        for(Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(articleService.getBoardRes(article.getBoard().getId()))
                    .category(articleService.getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return PaginationResponseBody.of(Status.OK, articleListRes, pagination);
    }

    @GetMapping("/hot/{boardId}")
    public AdvancedResponseBody<List<ArticleRes>> getTopArticlesByBoardId(@PathVariable Long boardId) {
        List<ArticleRes> articleListRes = articleService.getTopArticlesByBoardId(boardId);
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

    @GetMapping("/{boardId}/{type}")
    public AdvancedResponseBody<List<ArticleRes>> searchArticle(@PathVariable Long boardId, @PathVariable Integer type, @RequestParam String search) {
        List<ArticleRes> articleListRes = articleService.getArticleBySearchWord(boardId, type, search);
        return AdvancedResponseBody.of(Status.OK, articleListRes);
    }

    @PostMapping("/answer")
    public AdvancedResponseBody<ArticleAnswer> createArticleAnswer(@RequestBody ArticleAnswerCreateReq articleAnswerCreateReq, @CurrentUser User user) {
        ArticleAnswer articleAnswer = articleService.createArticleAnswer(articleAnswerCreateReq, user);
        return AdvancedResponseBody.of(Status.CREATED, articleAnswer);
    }

    @GetMapping("/answer/{articleId}/list")
    public AdvancedResponseBody<List<ArticleAnswerRes>> getArticleAnswerListByArticleId(@PathVariable Long articleId) {
        List<ArticleAnswerRes> articleAnswerResList = articleService.getArticleAnswerListByArticleId(articleId);
        return AdvancedResponseBody.of(Status.OK, articleAnswerResList);
    }

    @GetMapping("/answer/{answerId}")
    public AdvancedResponseBody<ArticleAnswerRes> getArticleAnswer(@PathVariable Long answerId) {
        ArticleAnswerRes articleAnswerRes = articleService.getArticleAnswer(answerId);
        return AdvancedResponseBody.of(Status.OK, articleAnswerRes);
    }

    @PatchMapping("/answer/{answerId}")
    public AdvancedResponseBody<ArticleAnswer> updateArticleAnswer(@PathVariable Long answerId, @RequestBody ArticleAnswerUpdateReq articleAnswerUpdateReq, @CurrentUser User user) {
        ArticleAnswer articleAnswer = articleService.updateArticleAnswer(answerId, articleAnswerUpdateReq, user);
        return AdvancedResponseBody.of(Status.OK, articleAnswer);
    }

    @PatchMapping("/answer/{articleAnswerId}/select")
    public AdvancedResponseBody<ArticleAnswer> selectArticleAnswer(@PathVariable Long articleAnswerId, @CurrentUser User user) {
        ArticleAnswer articleAnswer = articleService.selectArticleAnswer(articleAnswerId, user);
        return AdvancedResponseBody.of(Status.OK, articleAnswer);
    }

    @DeleteMapping("/answer/{answerId}")
    public AdvancedResponseBody<String> deleteArticleAnswer(@PathVariable Long answerId, @CurrentUser User user) {
        articleService.deleteArticleAnswer(answerId, user);
        return AdvancedResponseBody.of(Status.NO_CONTENT);
    }
}
