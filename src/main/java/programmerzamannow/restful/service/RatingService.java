package programmerzamannow.restful.service;

import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Rating;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.request.RatingRequest;

import java.util.List;

public interface RatingService {

  public Rating createRating(RatingRequest req, User user) throws ProductException;

  public List<Rating> getProductsRating(Long productId);
}
