package programmerzamannow.restful.latihan.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReq {
  private String email;
  private String password;
}
