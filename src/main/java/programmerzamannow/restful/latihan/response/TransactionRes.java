package programmerzamannow.restful.latihan.response;

import lombok.*;

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
