package a204.ssayeon.api.service;

import a204.ssayeon.api.request.article.ArticleCreateReq;
import a204.ssayeon.db.entity.article.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();             // 전체 게시글
    Article createArticle(ArticleCreateReq articleCreateReq);                    // 게시글 작성
    Article getArticleById(Long articleId);                       // 게시글 하나 가져오기

    Article updateArticle();                    // 게시글 수정
    Article deleteArticle();                    // 게시글 삭제
}
