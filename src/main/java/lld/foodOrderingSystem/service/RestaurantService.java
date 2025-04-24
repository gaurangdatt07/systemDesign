package lld.foodOrderingSystem.service;

import lld.foodOrderingSystem.entities.MenuItems;
import lld.foodOrderingSystem.entities.Order;
import lld.foodOrderingSystem.entities.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<MenuItems> showMenu(Integer restaurantId);
    List<Order> listOrders(Integer restaurantId);
    void createRestaurant(Restaurant restaurant);
    void createMenuItems(Integer restaurantId,MenuItems menuItems);
}
