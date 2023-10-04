package programmerzamannow.restful.response;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {

  private String jwt;
  private String message;

}
