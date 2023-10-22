package programmerzamannow.restful.latihan.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
