package programmerzamannow.restful.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddItemRequest {

  private Long productId;
  private String size;
  private int quantity;
  private Integer price;

}
