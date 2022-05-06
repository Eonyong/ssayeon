package a204.ssayeon.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    // 총 페이지 수
    private Integer totalPages;
    // 총 갯수
    private Long totalElements;
    // 현재 페이지
    private Integer currentPage;
    // 현재 페이지에 갖고 있는 element 수
    private Integer currentElements;

    public static <T> Pagination getPagination(Page<T> pagination){
        return Pagination.builder()
                .totalPages(pagination.getTotalPages())
                .totalElements(pagination.getTotalElements())
                .currentPage(pagination.getNumber() + 1)
                .currentElements(pagination.getNumberOfElements())
                .build();
    }
}
