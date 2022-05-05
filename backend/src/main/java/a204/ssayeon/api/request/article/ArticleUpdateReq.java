package a204.ssayeon.api.request.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateReq {

    private String title;
    private String content;
    private Long categoryId;
    private List<Long> tagList;
}
