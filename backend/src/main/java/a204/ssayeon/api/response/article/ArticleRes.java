package a204.ssayeon.api.response.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRes {

    private Long id;
    private String title;
    private String content;
    private Integer views;

    private Long userId;
    private String nickname;
    private Boolean isLiked;

    private BoardRes board;
    private CategoryRes category;

    private List<TagRes> tagList;      // id, name

}
