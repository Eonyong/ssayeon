package a204.ssayeon.api.response.user;

import a204.ssayeon.api.response.article.BoardRes;
import a204.ssayeon.api.response.article.CategoryRes;
import a204.ssayeon.api.response.article.TagRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShowUserActivityRes {
    private Long id;
    private String title;
    private String content;
    private Integer views;
    private BoardRes board;
    private CategoryRes category;
    private List<TagRes> tagList;      // id, name
}
