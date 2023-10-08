package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.exception.OrderException;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.Cart;
import programmerzamannow.restful.model.Order;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.request.AddItemRequest;
import programmerzamannow.restful.response.ApiResponse;
import programmerzamannow.restful.service.CartService;
import programmerzamannow.restful.service.OrderService;
import programmerzamannow.restful.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
//@Tag(name = "Cart Management", description = "find user cart, add item to cart")
public class CartController {

  @Autowired
  private CartService cartService;
  @Autowired
  private UserService userService;

  @GetMapping("/")
  //@Operation(description = "find cart by user id")
  public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
    User user = userService.findUserProfileByJwt(jwt);
    Cart cart = cartService.findUserCart(user.getId());

    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
  }

  @PutMapping("/add")
  //@Operation(description = "add item to cart")
  public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
    User user = userService.findUserProfileByJwt(jwt);
    cartService.addCartItem(user.getId(), req);

    ApiResponse res = new ApiResponse();
    res.setMessage("item added to cart");
    res.setStatus(true);

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

}
