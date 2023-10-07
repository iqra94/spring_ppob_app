package programmerzamannow.restful.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "order_id")
  private String orderId;

  @ManyToOne
  private User user;

  @OneToOne
  private Address shippingAddress;

  @Embedded
  private PaymentDetails paymentDetails = new PaymentDetails();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  private LocalDateTime orderDate;
  private LocalDateTime deliverDate;
  private LocalDateTime createdAt;

  private  double totalPrice;
  private  int totalItem;

  private  Integer totalDiscountedPrice;
  private  Integer discount;

  private  String orderStatus;

}
