package programmerzamannow.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  private double rating;

  private LocalDateTime createdAt;
}
