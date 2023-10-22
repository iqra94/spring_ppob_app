package programmerzamannow.restful.latihan.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiRes<T> {
  private Integer status;
  private String message;
  private T data;
}
