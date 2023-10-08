package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmerzamannow.restful.model.OrderItem;
import programmerzamannow.restful.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Override
  public OrderItem createOrderItem(OrderItem orderItem) {
    return orderItemRepository.save(orderItem);
  }
}
