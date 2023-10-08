package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.Review;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.request.ReviewRequest;
import programmerzamannow.restful.service.ReviewService;
import programmerzamannow.restful.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;
  @Autowired
  private UserService userService;

  @PostMapping("/create")
  public ResponseEntity<Review> createdOrder(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
    User user = userService.findUserProfileByJwt(jwt);
    Review review = reviewService.createReview(req, user);

    return new ResponseEntity<>(review, HttpStatus.CREATED);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<Review>> getProductsReview(@PathVariable("Id") Long productId) throws UserException, ProductException {
    List<Review> reviews = reviewService.getAllReview(productId);

    return new ResponseEntity<>(reviews, HttpStatus.ACCEPTED);
  }

}
