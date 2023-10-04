package programmerzamannow.restful.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;
  private String description;
  private String brand;
  private String color;

  private int price;
  private int quantity;

  private LocalDateTime createdAt;

  @Column(name = "discounted_price")
  private int discountedPrice;

  @Column(name = "discount_price")
  private int discountPrice;

  @Column(name = "num_ratings")
  private int numRatings;

  @Column(name = "image_url")
  private String imageUrl;

  @Embedded
  @ElementCollection
  private Set<Size>
          sizes = new HashSet<>();

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Rating> ratings = new ArrayList<>();

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Review> reviews = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
