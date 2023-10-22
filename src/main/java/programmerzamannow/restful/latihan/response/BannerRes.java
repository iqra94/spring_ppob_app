package programmerzamannow.restful.latihan.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BannerRes {
  private String banner_name;
  private String banner_image;
  private String description;
}
