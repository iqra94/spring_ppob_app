package programmerzamannow.restful.a.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import programmerzamannow.restful.model.PagingResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponseA<T> {

    private T data;

    private String errors;

    private PagingResponseH paging;
}
