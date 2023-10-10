package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.exception.CartItemException;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.CartItem;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.response.ApiResponse;
import programmerzamannow.restful.service.CartItemService;
import programmerzamannow.restful.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

  @Autowired
  private UserService userService;
  @Autowired
  private CartItemService cartItemService;

  @DeleteMapping("/{cartItemId}")
  //@Operation(description = "remove cart item from cart")
  public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
    User user = userService.findUserProfileByJwt(jwt);
    cartItemService.removeCartItem(user.getId(), cartItemId);

    ApiResponse res = new ApiResponse();
    res.setMessage("item deleted from cart");
    res.setStatus(true);

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PutMapping("/{cartItemId}")
  //@Operation(description = "update item to cart")
  public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
    User user = userService.findUserProfileByJwt(jwt);

    CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

    return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
  }
}
