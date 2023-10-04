package programmerzamannow.restful.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class PaymentInformation {

  @Column(name = "card_holder_name")
  private String cardHolderName;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "expiration_date")
  private LocalDate expirationDate;

  private String cvv;

}
