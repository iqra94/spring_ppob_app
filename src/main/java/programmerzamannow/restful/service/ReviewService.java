package programmerzamannow.restful.service;

import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Review;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

  public Review createReview(ReviewRequest req, User user) throws ProductException;
  public List<Review> getAllReview(Long productId);
}
