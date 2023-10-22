package programmerzamannow.restful.latihan.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@Table(name = "test_service")
public class Service {

  @Id
  @Column(name = "service_code")
  private String serviceCode;

  @Column(name = "service_name")
  private String serviceName;

  @Column(name = "service_icon")
  private String serviceIcon;

  @Column(name = "service_tariff")
  private Integer serviceTariff;

}
