package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Product;
import programmerzamannow.restful.model.Review;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.repository.ProductRepository;
import programmerzamannow.restful.repository.ReviewRepository;
import programmerzamannow.restful.request.ReviewRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Review createReview(ReviewRequest req, User user) throws ProductException {
    Product product = productService.findProductById(req.getProductId());

    Review review = new Review();
    review.setProduct(product);
    review.setUser(user);
    review.setReview(req.getReview());
    review.setCreatedAt(LocalDateTime.now());

    return reviewRepository.save(review);
  }

  @Override
  public List<Review> getAllReview(Long productId) {
    return reviewRepository.getAllProductsReview(productId);
  }
}
