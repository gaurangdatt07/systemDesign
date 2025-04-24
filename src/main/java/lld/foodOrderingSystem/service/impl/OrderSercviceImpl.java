package lld.foodOrderingSystem.service.impl;

import lld.foodOrderingSystem.FoodOrderingSecurityAuth;
import lld.foodOrderingSystem.dbConfig.OrderPlacementDbConfig;
import lld.foodOrderingSystem.entities.CartItem;
import lld.foodOrderingSystem.entities.MenuItems;
import lld.foodOrderingSystem.entities.Order;
import lld.foodOrderingSystem.entities.User;
import lld.foodOrderingSystem.enums.Role;
import lld.foodOrderingSystem.enums.Status;
import lld.foodOrderingSystem.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderSercviceImpl implements OrderService {
    @Override
    public Order createOrder(Integer userId) {
        User currentUser = FoodOrderingSecurityAuth.getCurrentUser();

        if (currentUser == null || !currentUser.getId().equals(userId) || !currentUser.getRole().equals(Role.USER)) {
            throw new RuntimeException("Unauthorized or unauthenticated access.");
        }

        List<CartItem> cartItems = OrderPlacementDbConfig.getCartItem().getOrDefault(userId, new ArrayList<>());
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Please add items before placing an order.");
        }

        // Step 1: Group items by restaurant
        Map<Integer, List<CartItem>> restaurantToItems = cartItems.stream()
                .collect(Collectors.groupingBy(CartItem::getRestaurantId));

        if (restaurantToItems.size() > 1) {
            throw new RuntimeException("You can only place an order from one restaurant at a time.");
        }

        Integer restaurantId = restaurantToItems.keySet().iterator().next();
        List<CartItem> itemsForRestaurant = restaurantToItems.get(restaurantId);

        // Step 2: Fetch current prices from menu and calculate total
        List<MenuItems> menu = OrderPlacementDbConfig.getRestaurantToMenuMap()
                .getOrDefault(restaurantId, new ArrayList<>());

        Map<Integer, MenuItems> menuMap = menu.stream()
                .collect(Collectors.toMap(MenuItems::getId, m -> m));

        int totalAmount = 0;
        for (CartItem item : itemsForRestaurant) {
            MenuItems menuItem = menuMap.get(item.getMenuItemId());
            if (menuItem == null) {
                throw new RuntimeException("Menu item not found: " + item.getMenuItemId());
            }
            totalAmount += menuItem.getPrice() * item.getQuantity();
        }

        // Step 3: Build Order
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().variant());
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setCartList(new ArrayList<>(itemsForRestaurant));
        order.setOrderStatus(Status.PLACED);
        order.setTotalPrice(totalAmount);

        // Step 4: Save order in user → orders map
        OrderPlacementDbConfig.getOrders()
                .computeIfAbsent(userId, k -> new ArrayList<>())
                .add(order);

        // Step 5: Save order in restaurant → orders map
        OrderPlacementDbConfig.getRestaurantToOrderMap()
                .computeIfAbsent(restaurantId, k -> new ArrayList<>())
                .add(order);

        // Step 6: Clear user's cart
        OrderPlacementDbConfig.getCartItem().put(userId, new ArrayList<>());

        return order;
    }
}
