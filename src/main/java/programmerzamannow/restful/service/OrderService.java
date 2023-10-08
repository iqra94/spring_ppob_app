package programmerzamannow.restful.service;

import programmerzamannow.restful.exception.OrderException;
import programmerzamannow.restful.model.Address;
import programmerzamannow.restful.model.Order;
import programmerzamannow.restful.model.User;

import java.util.List;

public interface OrderService {

  public List<Order> userOrderHistory(Long userId);

  public List<Order> getAllOrders();

  public Order createOrder(User user, Address shippingAddress);

  public Order findOrderById(Long orderId) throws OrderException;

  public Order placedOrder(Long orderId) throws OrderException;

  public Order confirmedOrder(Long orderId) throws OrderException;

  public Order shippedOrder(Long orderId) throws OrderException;

  public Order deliveredOrder(Long orderId) throws OrderException;

  public Order canceledOrder(Long orderId) throws OrderException;

  public void deleteOrder(Long orderId) throws OrderException;
}
