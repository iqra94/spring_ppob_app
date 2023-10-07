package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmerzamannow.restful.exception.OrderException;
import programmerzamannow.restful.model.Address;
import programmerzamannow.restful.model.Order;
import programmerzamannow.restful.model.User;
//import programmerzamannow.restful.repository.CartRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

//  @Autowired
//  private CartRepository cartRepository;
//
//  @Autowired
//  private CartService cartService;
//
//  @Autowired
//  private ProductService productService;

  @Override
  public List<Order> userOrderHistory(Long orderId) {
    return null;
  }

  @Override
  public List<Order> getAllOrders() {
    return null;
  }

  @Override
  public Order createOrder(User user, Address shippingAddress) {
    return null;
  }

  @Override
  public Order findOrderById(Long orderId) throws OrderException {
    return null;
  }

  @Override
  public Order placedOrder(Long orderId) throws OrderException {
    return null;
  }

  @Override
  public Order confirmedOrder(Long orderId) throws OrderException {
    return null;
  }

  @Override
  public Order shippedOrder(Long orderId) throws OrderException {
    return null;
  }

  @Override
  public Order deliveredOrder(Long orderId) throws OrderException {
    return null;
  }

  @Override
  public Order canceledOrder(Long orderId) throws OrderException {
    return null;
  }

  @Override
  public Order deleteOrder(Long orderId) throws OrderException {
    return null;
  }
}
