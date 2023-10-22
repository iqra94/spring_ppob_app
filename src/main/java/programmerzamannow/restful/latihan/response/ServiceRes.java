package programmerzamannow.restful.latihan.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRes {
  private String service_code;
  private String service_name;
  private String service_icon;
  private Integer service_tariff;
}
