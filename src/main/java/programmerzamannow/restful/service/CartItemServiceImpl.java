package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmerzamannow.restful.exception.CartItemException;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.Cart;
import programmerzamannow.restful.model.CartItem;
import programmerzamannow.restful.model.Product;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.repository.CartItemRepository;
import programmerzamannow.restful.repository.CartRepository;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService{

  @Autowired
  private CartItemRepository cartItemRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private CartRepository cartRepository;

  @Override
  public CartItem createCartItem(CartItem cartItem) {
    cartItem.setQuantity(1);
    cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
    cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

    CartItem createdCartItem = cartItemRepository.save(cartItem);

    return createdCartItem;
  }

  @Override
  public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
    CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
    return cartItem;
  }

  @Override
  public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
    CartItem item = findCartItemById(id);
    User user = userService.findUserById(item.getUserId());

    if (user.getId().equals(userId)) {
      item.setQuantity(cartItem.getQuantity());
      item.setPrice(item.getQuantity() * item.getProduct().getPrice());
      item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
    }

    return cartItemRepository.save(item);
  }

  @Override
  public CartItem findCartItemById(Long cartItemId) throws CartItemException {
    Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

    if (opt.isPresent()) {
      return opt.get();
    }

    throw new CartItemException("cartItem not found with id : " +cartItemId);
  }

  @Override
  public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
    CartItem cartItem = findCartItemById(cartItemId);
    User user = userService.findUserById(cartItem.getUserId());
    User reqUser = userService.findUserById(userId);

    if (user.getId().equals(reqUser.getId())) {
      cartItemRepository.deleteById(cartItemId);
    } else {
      throw new UserException("you can't remove another user items");
    }
  }
}
