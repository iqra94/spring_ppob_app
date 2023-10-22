package programmerzamannow.restful.latihan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "invoice_number")
  private String invoiceNumber;

  @Column(name = "transaction_type")
  private String transactionType;

  @Column(name = "created_on")
  private String createdOn;

  @Column(name = "total_amount")
  private Integer totalAmount;

  @ManyToOne
  @JoinColumn(name = "service_code", referencedColumnName = "service_code", insertable = false, updatable = false)
  private Service service;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "membership_id")
  private Membership membership;

}
