package programmerzamannow.restful.latihan.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRes {
  private String invoice_number;
  private String service_code;
  private String service_name;
  private String transaction_type;
  private String created_on;
  private Integer total_amount;
}
