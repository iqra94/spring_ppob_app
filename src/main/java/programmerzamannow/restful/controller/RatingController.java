package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.Rating;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.request.RatingRequest;
import programmerzamannow.restful.service.RatingService;
import programmerzamannow.restful.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

  @Autowired
  private RatingService ratingService;
  @Autowired
  private UserService userService;

  @PostMapping("/create")
  public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
    User user = userService.findUserProfileByJwt(jwt);
    Rating rating = ratingService.createRating(req, user);

    return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<Rating>> getProductsRating(@PathVariable("Id") Long productId, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
    User user = userService.findUserProfileByJwt(jwt);
    List<Rating> ratings = ratingService.getProductsRating(productId);

    return new ResponseEntity<>(ratings, HttpStatus.CREATED);
  }

}
