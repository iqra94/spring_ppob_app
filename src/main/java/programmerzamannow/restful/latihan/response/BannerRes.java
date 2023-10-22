package programmerzamannow.restful.latihan.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BannerRes {
  private String banner_name;
  private String banner_image;
  private String description;
}
