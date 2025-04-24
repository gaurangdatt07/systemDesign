package lld.foodOrderingSystem.service;

import lld.foodOrderingSystem.entities.Order;

public interface OrderService {

    Order createOrder(Integer userId);
}
