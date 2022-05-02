package a204.ssayeon.api.service;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.api.request.article.ArticleUpdateReq;
import a204.ssayeon.api.request.article.CommentCreateReq;
import a204.ssayeon.api.response.article.ArticleRes;
import a204.ssayeon.api.response.article.BoardRes;
import a204.ssayeon.api.response.article.CategoryRes;
import a204.ssayeon.api.response.article.TagRes;
import a204.ssayeon.db.entity.article.*;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.article.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ArticleHasTagRepository articleHasTagRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommentRepository commentRepository;

    public List<ArticleRes> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleRes> articleListRes = new ArrayList<>();
        for(Article article : articles) {
            articleListRes.add(ArticleRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
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
        return article;
    }

    public ArticleRes getArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        // 이 경우에 예외처리
        if (article == null) {
            return null;
        }

        List<ArticleHasTag> tagList = articleHasTagRepository.findByArticleId(article.getId());
        List<TagRes> tagListRes = new ArrayList<>();
        for(ArticleHasTag tag : tagList) {
            tagListRes.add(TagRes.builder()
                    .id(tag.getId())
                    .name(tagRepository.getById(tag.getId()).getName())
                    .build());
        }

        ArticleRes articleRes = ArticleRes.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .views(article.getViews())
                .nickname(article.getUser().getNickname())
                .category(getCategoryRes(article.getCategory().getId()))
                .board(getBoardRes(article.getBoard().getId()))
                .tagList(tagListRes)
                .build();
        return articleRes;
    }

    public Article updateArticle(Long id, ArticleUpdateReq articleUpdateReq, User user) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }

        // 여기서 자격 처리
        if (user.getId() != article.getUser().getId()) {
            return null;
        }

        Article updatedArticle = Article.builder()
                .id(article.getId())
                .title(articleUpdateReq.getTitle())
                .content(articleUpdateReq.getContent())
                .category(categoryRepository.getById(articleUpdateReq.getCategoryId()))
                .build();

        articleRepository.save(updatedArticle);
        return updatedArticle;
    }

    public Integer deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);

        // 이 경우에 예외처리
        if (article == null) {
            return 403;
        }

        // 작성자가 아닐 때

        articleRepository.delete(article);
        return 200;
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
                    .nickname(article.getUser().getNickname())
                    .board(getBoardRes(article.getBoard().getId()))
                    .category(getCategoryRes(article.getCategory().getId()))
                    .build());
        }
        return articleListRes;
    }

    // 게시글 좋아요

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
        System.out.println("article = " + article);
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
