package a204.ssayeon.api.response.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAnswerRes {

    private Long id;
    private String description;
    private Long userId;
    private String nickname;
    private Boolean isSelected;

    private Long articleId;
}
