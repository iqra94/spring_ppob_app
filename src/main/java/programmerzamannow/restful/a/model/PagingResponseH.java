package programmerzamannow.restful.a.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponseH {

    private Integer currentPage;

    private Integer totalPage;

    private Integer size;
}
