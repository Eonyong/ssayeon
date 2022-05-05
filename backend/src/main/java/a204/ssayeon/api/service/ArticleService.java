package a204.ssayeon.api.service;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.api.request.article.ArticleUpdateReq;
import a204.ssayeon.api.request.article.CommentCreateReq;
import a204.ssayeon.api.response.article.ArticleRes;
import a204.ssayeon.api.response.article.BoardRes;
import a204.ssayeon.api.response.article.CategoryRes;
import a204.ssayeon.api.response.article.TagRes;
import a204.ssayeon.common.exceptions.ForbiddenException;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.article.*;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.UserRepository;
import a204.ssayeon.db.repository.article.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final BoardRepository boardRepository;

    private final ArticleHasTagRepository articleHasTagRepository;

    private final TagRepository tagRepository;

    private final CategoryRepository categoryRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ArticleLikesRepository articleLikesRepository;


    public List<ArticleRes> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleRes> articleListRes = new ArrayList<>();
        for(Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return articleListRes;
    }

    public Article createArticle(ArticleCreateReq articleCreateReq, User user) {

        Article article = Article.builder()
                .title(articleCreateReq.getTitle())
                .content(articleCreateReq.getContent())
                .user(user)
                .board(boardRepository.getById(articleCreateReq.getBoardId()))
                .category(categoryRepository.getById(articleCreateReq.getCategoryId()))
                .build();
        article = articleRepository.save(article);

        List<Long> tagList = articleCreateReq.getTagList();
        for(Long tagId : tagList) {
            ArticleHasTag articleHasTag = ArticleHasTag.builder()
                    .article(articleRepository.getById(article.getId()))
                    .tag(tagRepository.getById(tagId))
                    .build();
            articleHasTagRepository.save(articleHasTag);
        }
        return article;
    }

    public ArticleRes getArticleById(Long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        List<ArticleHasTag> tagList = articleHasTagRepository.findByArticleId(article.getId());
        List<TagRes> tagListRes = new ArrayList<>();
        for(ArticleHasTag tag : tagList) {
            tagListRes.add(TagRes.builder()
                    .id(tag.getId())
                    .name(tagRepository.getById(tag.getId()).getName())
                    .build());
        }

        Boolean isLiked = false;

        if (user != null) {
            ArticleLikes articleLikes = articleLikesRepository.findByArticleIdAndUserId(articleId, user.getId());
            if ( articleLikes != null ) {
                isLiked = true;
            }
        }

        ArticleRes articleRes = ArticleRes.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .views(article.getViews())
                .userId(article.getUser().getId())
                .nickname(article.getUser().getNickname())
                .isLiked(isLiked)
                .category(getCategoryRes(article.getCategory().getId()))
                .board(getBoardRes(article.getBoard().getId()))
                .tagList(tagListRes)
                .build();
        return articleRes;
    }

    public Article updateArticle(Long articleId, ArticleUpdateReq articleUpdateReq, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(() ->
            new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));


        articleRepository.findByIdAndUser(articleId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));


        article.update(articleUpdateReq, categoryRepository.getById(articleUpdateReq.getCategoryId()));
        articleRepository.save(article);

        List<ArticleHasTag> tagList = articleHasTagRepository.findByArticleId(article.getId());
        for(ArticleHasTag articleHasTag : tagList) {
            articleHasTagRepository.delete(articleHasTag);
        }

        List<Long> newTagList = articleUpdateReq.getTagList();
        for(Long tagId : newTagList) {
            ArticleHasTag articleHasTag = ArticleHasTag.builder()
                    .article(articleRepository.getById(article.getId()))
                    .tag(tagRepository.getById(tagId))
                    .build();
            articleHasTagRepository.save(articleHasTag);
        }

        return article;
    }

    public void deleteArticle(Long articleId, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        articleRepository.findByIdAndUser(articleId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        articleRepository.delete(article);

    }

    // 보드 아이디 별로 아이디 가져오기
    public List<ArticleRes> getArticlesByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findByBoardId(boardId);
        List<ArticleRes> articleListRes = new ArrayList<>();
        for(Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return articleListRes;
    }

    // 게시글 좋아요
    public void likeArticle(Long articleId, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        ArticleLikes articleLikes = articleLikesRepository.findByArticleIdAndUserId(articleId, user.getId());

        if (articleLikes == null) {
            ArticleLikes articleLike = ArticleLikes.builder()
                    .article(articleRepository.getById(articleId))
                    .user(user)
                    .build();
            articleLikesRepository.save(articleLike);
        } else {
            articleLikesRepository.delete(articleLikes);
        }
    }

    public BoardRes getBoardRes(Long boardId) {
        Board board = boardRepository.getById(boardId);
        BoardRes boardRes = BoardRes.builder()
                .id(board.getId())
                .name(board.getName())
                .build();
        return boardRes;
    }

    public CategoryRes getCategoryRes(Long categoryId) {
        Category category = categoryRepository.getById(categoryId);
        CategoryRes categoryRes = CategoryRes.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryRes;
    }

    public ArticleComments createComment(Long articleId, CommentCreateReq commentCreateReq) {

        Article article = articleRepository.findById(articleId).orElse(null);

        if (article == null) {
            return null;
        }

        ArticleComments comment = ArticleComments.builder()
                .description(commentCreateReq.getDescription())
                .article(article)
                .build();
        comment = commentRepository.save(comment);
        return comment;
    }
}
