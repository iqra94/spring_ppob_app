package programmerzamannow.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String firstName;
  private String lastName;
  private String password;
  private String email;
  private String role;
  private String mobile;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Address> addresses = new ArrayList<>();

  @Embedded
  @ElementCollection
  @CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
  private List<PaymentInformation>
paymentInformations = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Rating> ratings = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  private LocalDateTime createdAt;
}
