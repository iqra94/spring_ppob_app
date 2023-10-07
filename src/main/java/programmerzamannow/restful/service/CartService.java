package programmerzamannow.restful.service;

import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Cart;
import programmerzamannow.restful.model.User;
import programmerzamannow.restful.request.AddItemRequest;

public interface CartService {

  public Cart createCart(User user);

  public Cart findUserCart(Long userId);

  public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

}
