package programmerzamannow.restful.service;

import programmerzamannow.restful.exception.CartItemException;
import programmerzamannow.restful.exception.UserException;
import programmerzamannow.restful.model.Cart;
import programmerzamannow.restful.model.CartItem;
import programmerzamannow.restful.model.Product;

public interface CartItemService {

  public CartItem createCartItem(CartItem cartItem);

  public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

  public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

  public CartItem findCartItemById(Long cartItemId) throws CartItemException;

  public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
}
