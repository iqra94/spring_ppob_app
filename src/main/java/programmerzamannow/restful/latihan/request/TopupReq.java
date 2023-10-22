package programmerzamannow.restful.latihan.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TopupReq {
//  @Pattern(regexp = "\\d+", message = "Amount harus berupa angka")
//  @Min(value = 0, message = "Amount tidak boleh lebih kecil dari 0")
  private int topUpAmount;
}
