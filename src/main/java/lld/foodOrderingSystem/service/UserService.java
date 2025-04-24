package lld.foodOrderingSystem.service;

import lld.foodOrderingSystem.entities.Order;
import lld.foodOrderingSystem.entities.Restaurant;
import lld.foodOrderingSystem.entities.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<Order> viewOrders(Integer userId);

    Order createOrder(Integer userId);

}
