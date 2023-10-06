package programmerzamannow.restful.request;

import lombok.Getter;
import lombok.Setter;
import programmerzamannow.restful.model.Size;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateProductRequest {

  private String title;
  private String description;
  private String brand;
  private String color;
  private String imageUrl;
  private String topLevelCategory;
  private String secondLevelCategory;
  private String thirdLevelCategory;

  private int price;
  private int discountedPrice;
  private int discountPercent;
  private int quantity;

  private Set<Size> sizes = new HashSet<>();
}
