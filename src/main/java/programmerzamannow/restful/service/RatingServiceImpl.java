package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Product;
import programmerzamannow.restful.model.Rating;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.repository.RatingRepository;
import programmerzamannow.restful.request.RatingRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

  @Autowired
  private RatingRepository ratingRepository;

//  @Autowired
  private ProductService productService;

  @Override
  public Rating createRating(RatingRequest req, User user) throws ProductException {
    Product product = productService.findProductById(req.getProductId());

    Rating rating = new Rating();
    rating.setProduct(product);
    rating.setUser(user);
    rating.setRating(req.getRating());
    rating.setCreatedAt(LocalDateTime.now());

    return ratingRepository.save(rating);
  }

  @Override
  public List<Rating> getProductsRating(Long productId) {
    return ratingRepository.getAllProductsRating(productId);
  }
}
