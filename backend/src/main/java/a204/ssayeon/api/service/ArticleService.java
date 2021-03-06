package a204.ssayeon.api.service;

import a204.ssayeon.api.request.article.*;
import a204.ssayeon.api.response.article.*;
import a204.ssayeon.common.exceptions.ForbiddenException;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.exceptions.UnAuthorizedException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.Pagination;
import a204.ssayeon.db.entity.article.*;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.article.*;
import a204.ssayeon.db.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ArticleScrapRepository articleScrapRepository;

    private final ArticleCommentsRepository articleCommentsRepository;

    private final ArticleCommentsLikesRepository articleCommentsLikesRepository;

    private final ArticleAnswerRepository articleAnswerRepository;


    public Page<Article> getAllArticles(Pageable pageable) {

        return articleRepository.findAll(pageable);
    }

    public Article createArticle(ArticleCreateReq articleCreateReq, User user) {

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        Article article = Article.builder()
                .title(articleCreateReq.getTitle())
                .content(articleCreateReq.getContent())
                .user(user)
                .board(boardRepository.getById(articleCreateReq.getBoardId()))
                .category(categoryRepository.getById(articleCreateReq.getCategoryId()))
                .build();
        article = articleRepository.save(article);

        List<Long> tagList = articleCreateReq.getTagList();
        for (Long tagId : tagList) {
            ArticleHasTag articleHasTag = ArticleHasTag.builder()
                    .article(articleRepository.getById(article.getId()))
                    .tag(tagRepository.getById(tagId))
                    .build();
            articleHasTagRepository.save(articleHasTag);
        }
        return article;
    }

    public ArticleRes getArticleById(Long articleId, User user) {
        Article article1 = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        article1.updateViews();

        Article article = articleRepository.save(article1);

        List<ArticleHasTag> tagList = articleHasTagRepository.findByArticleId(article.getId());
        List<TagRes> tagListRes = new ArrayList<>();
        for (ArticleHasTag tag : tagList) {
            tagListRes.add(TagRes.builder()
                    .id(tag.getTag().getId())
                    .name(tagRepository.getById(tag.getTag().getId()).getName())
                    .build());
        }

        Boolean isLiked = false;

        if (user != null) {
            ArticleLikes articleLikes = articleLikesRepository.findByArticleIdAndUserId(articleId, user.getId());
            if (articleLikes != null) {
                isLiked = true;
            }
        }



        ArticleRes articleRes = ArticleRes.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .views(article.getViews())
                .likesCount(article.getLikesCount())
                .userId(article.getUser().getId())
                .nickname(article.getUser().getNickname())
                .isLiked(isLiked)
                .category(getCategoryRes(article.getCategory().getId()))
                .board(getBoardRes(article.getBoard().getId()))
                .createdAt(article.getCreatedAt())
                .tagList(tagListRes)
                .build();
        return articleRes;
    }

    public Article updateArticle(Long articleId, ArticleUpdateReq articleUpdateReq, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleRepository.findByIdAndUser(articleId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));


        article.update(articleUpdateReq, categoryRepository.getById(articleUpdateReq.getCategoryId()));
        articleRepository.save(article);

        List<ArticleHasTag> tagList = articleHasTagRepository.findByArticleId(article.getId());
        for (ArticleHasTag articleHasTag : tagList) {
            articleHasTagRepository.delete(articleHasTag);
        }

        List<Long> newTagList = articleUpdateReq.getTagList();
        for (Long tagId : newTagList) {
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

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleRepository.findByIdAndUser(articleId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        articleRepository.delete(article);

    }

    // ?????? ????????? ?????? ????????? ????????????
    public Page<Article> getArticlesByBoardId(Long boardId, Pageable pageable) {
        Page<Article> articles = articleRepository.findByBoardId(boardId, pageable);

        return articles;
    }

    // ????????? ?????????
    public void likeArticle(Long articleId, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        ArticleLikes articleLikes = articleLikesRepository.findByArticleIdAndUserId(articleId, user.getId());

        if (articleLikes == null) {
            ArticleLikes articleLike = ArticleLikes.builder()
                    .article(articleRepository.getById(articleId))
                    .user(user)
                    .build();
            articleLikesRepository.save(articleLike);

            article.updateLikesCount(article.getLikesCount() + 1);
            articleRepository.save(article);
        } else {
            articleLikesRepository.delete(articleLikes);

            article.updateLikesCount(article.getLikesCount() - 1);
            articleRepository.save(article);
        }
    }

    // ????????? ?????????
    public void scrapArticle(Long articleId, User user) {
        articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        ArticleScrap articleScrap = articleScrapRepository.findByArticleIdAndUserId(articleId, user.getId());

        if (articleScrap == null) {
            ArticleScrap scrap = ArticleScrap.builder()
                    .article(articleRepository.getById(articleId))
                    .user(user)
                    .build();
            articleScrapRepository.save(scrap);
        } else {
            articleScrapRepository.delete(articleScrap);
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

    public List<ArticleCommentsRes> getCommentsByArticleId(Long articleId, User user) {
        List<ArticleComments> commentsList = articleCommentsRepository.findByArticleId(articleId);
        List<ArticleCommentsRes> articleCommentsListRes = new ArrayList<>();

        for (ArticleComments articleComments : commentsList) {
            Boolean isLiked = false;
            ArticleCommentsLikes articleCommentsLikes = articleCommentsLikesRepository.findByArticleCommentsIdAndUser(articleComments.getId(), user);
            if (articleCommentsLikes != null) {
                isLiked = true;
            }
            articleCommentsListRes.add(ArticleCommentsRes.builder()
                    .id(articleComments.getId())
                    .description(articleComments.getDescription())
                    .userId(articleComments.getUser().getId())
                    .nickname(articleComments.getUser().getNickname())
                    .isLiked(isLiked)
                    .build());
        }

        return articleCommentsListRes;
    }

    public ArticleComments createComment(Long articleId, CommentCreateReq commentCreateReq, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        ArticleComments comment = ArticleComments.builder()
                .description(commentCreateReq.getDescription())
                .user(user)
                .article(article)
                .build();
        comment = commentRepository.save(comment);
        return comment;
    }

    public ArticleComments updateComment(Long commentId, CommentCreateReq commentCreateReq, User user) {

        ArticleComments articleComment = articleCommentsRepository.findById(commentId).orElseThrow(() ->
                new NotExistException(ErrorMessage.COMMENT_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleCommentsRepository.findByIdAndUser(commentId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        articleComment.update(commentCreateReq);
        articleCommentsRepository.save(articleComment);

        return articleComment;
    }

    public void deleteComment(Long commentId, User user) {

        ArticleComments articleComment = articleCommentsRepository.findById(commentId).orElseThrow(() ->
                new NotExistException(ErrorMessage.COMMENT_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleCommentsRepository.findByIdAndUser(commentId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        articleCommentsRepository.delete(articleComment);
    }

    // ?????? ?????????
    public void likeComment(Long commentId, User user) {

        articleCommentsRepository.findById(commentId).orElseThrow(() ->
                new NotExistException(ErrorMessage.COMMENT_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        ArticleCommentsLikes articleCommentsLikes = articleCommentsLikesRepository.findByArticleCommentsIdAndUser(commentId, user);

        if (articleCommentsLikes == null) {
            ArticleCommentsLikes articleCommentsLike = ArticleCommentsLikes.builder()
                    .articleComments(articleCommentsRepository.getById(commentId))
                    .user(user)
                    .build();
            articleCommentsLikesRepository.save(articleCommentsLike);
        } else {
            articleCommentsLikesRepository.delete(articleCommentsLikes);
        }
    }

    // ????????? ??????
    public List<ArticleRes> getArticleBySearchWord(Long boardId, Integer type, String search) {

        List<ArticleRes> articleListRes = new ArrayList<>();
        List<Article> articles = new ArrayList<>();

        // 1 = ??????, 2 = ??????, 3 = ?????????
        if (type == 1) {
            articles = articleRepository.findByTitleContainsAndBoardId(search, boardId);
        } else if (type == 2) {
            articles = articleRepository.findByContentContainsAndBoardId(search, boardId);
        } else if (type == 3) {
            User user = userRepository.findByNickname(search).get();
            System.out.println("user = " + user);
            articles = articleRepository.findByUserIdAndBoardId(user.getId(), boardId);
        }

        for (Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }

        return articleListRes;
    }

    @Transactional(readOnly = true)
    public Object[] getAllArticleBySearchWord(String search, Pageable pageable) {
        Page<Article> articlePage = articleRepository.findByTitleContainsOrContentContains(search, search, pageable);
        Pagination pagination = Pagination.getPagination(articlePage); //??????????????????

        List<ArticleRes> articleList = new ArrayList<>();

        articlePage.forEach((article) -> {
            Board board = article.getBoard();
            Category category = article.getCategory();
            User user = article.getUser();
            articleList.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(user.getId())
                    .nickname(user.getName())
                    .board(BoardRes.builder()
                            .id(board.getId())
                            .name(board.getName())
                            .build())
                    .category(CategoryRes.builder().id(category.getId()).name(category.getName()).build())
                    .build());
        });

        return new Object[]{pagination, articleList};
    }

    // ?????? ?????????

    public List<ArticleRes> getTopArticles() {
        List<Article> articles = articleRepository.findAll(Sort.by(Sort.Direction.DESC, "likesCount"));
        List<ArticleRes> articleListRes = new ArrayList<>();
        for (Article article : articles) {
            if (articleListRes.size() >= 10) {
                break;
            }
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return articleListRes;
    }

    public List<ArticleRes> getTopArticlesByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findTop10ByBoardIdOrderByLikesCountDesc(boardId);
        List<ArticleRes> articleListRes = new ArrayList<>();
        for (Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return articleListRes;
    }

    public List<ArticleRes> getLatestArticlesByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findTop3ByBoardIdOrderByIdDesc(boardId);
        List<ArticleRes> articleListRes = new ArrayList<>();
        for (Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .likesCount(article.getLikesCount())
                    .userId(article.getUser().getId())
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return articleListRes;
    }

    public ArticleAnswer createArticleAnswer(ArticleAnswerCreateReq articleAnswerCreateReq, User user) {

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        ArticleAnswer articleAnswer = ArticleAnswer.builder()
                .article(articleRepository.getById(articleAnswerCreateReq.getArticleId()))
                .description(articleAnswerCreateReq.getDescription())
                .user(user)
                .build();

        articleAnswerRepository.save(articleAnswer);
        return articleAnswer;
    }

    public ArticleAnswerRes getArticleAnswer(Long articleAnswerId) {
        ArticleAnswer articleAnswer = articleAnswerRepository.findById(articleAnswerId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        ArticleAnswerRes articleAnswerRes = ArticleAnswerRes.builder()
                .id(articleAnswer.getId())
                .description(articleAnswer.getDescription())
                .userId(articleAnswer.getUser().getId())
                .nickname(articleAnswer.getUser().getNickname())
                .isSelected(articleAnswer.getIsSelected())
                .articleId(articleAnswer.getArticle().getId())
                .build();

        return articleAnswerRes;
    }

    public List<ArticleAnswerRes> getArticleAnswerListByArticleId(Long articleId) {
        articleRepository.findById(articleId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        List<ArticleAnswer> articleAnswerList = articleAnswerRepository.findByArticleId(articleId);
        List<ArticleAnswerRes> articleAnswerListRes = new ArrayList<>();

        for (ArticleAnswer articleAnswer : articleAnswerList) {
            articleAnswerListRes.add(ArticleAnswerRes.builder()
                    .id(articleAnswer.getId())
                    .description(articleAnswer.getDescription())
                    .userId(articleAnswer.getUser().getId())
                    .nickname(articleAnswer.getUser().getNickname())
                    .isSelected(articleAnswer.getIsSelected())
                    .articleId(articleAnswer.getArticle().getId())
                    .build());
        }

        return articleAnswerListRes;
    }

    public ArticleAnswer updateArticleAnswer(Long articleAnswerId, ArticleAnswerUpdateReq articleAnswerUpdateReq, User user) {
        ArticleAnswer articleAnswer = articleAnswerRepository.findById(articleAnswerId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleAnswerRepository.findByIdAndUser(articleAnswerId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        articleAnswer.update(articleAnswerUpdateReq.getDescription());
        articleAnswerRepository.save(articleAnswer);

        return articleAnswer;
    }

    public ArticleAnswer selectArticleAnswer(Long articleAnswerId, User user) {
        ArticleAnswer articleAnswer = articleAnswerRepository.findById(articleAnswerId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleRepository.findByIdAndUser(articleAnswer.getArticle().getId(), user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        if (articleAnswer.getIsSelected() == true) {
            articleAnswer.select(false);
        } else {
            articleAnswer.select(true);
        }

        articleAnswerRepository.save(articleAnswer);

        return articleAnswer;
    }

    public void deleteArticleAnswer(Long articleAnswerId, User user) {
        ArticleAnswer articleAnswer = articleAnswerRepository.findById(articleAnswerId).orElseThrow(() ->
                new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));

        if (user == null) {
            throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
        }

        articleAnswerRepository.findByIdAndUser(articleAnswerId, user).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.FORBIDDEN));

        articleAnswerRepository.delete(articleAnswer);
    }
}
