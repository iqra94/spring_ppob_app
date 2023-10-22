package programmerzamannow.restful.latihan.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRes {
  private String email;
  private String first_name;
  private String last_name;
  private String profile_image;
}
