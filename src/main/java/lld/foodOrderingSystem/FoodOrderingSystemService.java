package lld.foodOrderingSystem;

import lld.foodOrderingSystem.entities.*;
import lld.foodOrderingSystem.enums.CartAction;
import lld.foodOrderingSystem.enums.Cuisine;
import lld.foodOrderingSystem.enums.Role;
import lld.foodOrderingSystem.service.impl.CartServiceImpl;
import lld.foodOrderingSystem.service.impl.OrderSercviceImpl;
import lld.foodOrderingSystem.service.impl.RestaurantServiceImpl;
import lld.foodOrderingSystem.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FoodOrderingSystemService {
    public static void main(String[] args) {
        // STEP 1: Register a restaurant
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Tandoori Flames");
        restaurant.setAddress("Koramangala, Bangalore");

        // Simulate login as restaurant
        User restaurantUser = new User(1000, "tandoori", "123456", new ArrayList<>(),new ArrayList<>(),Role.RESTAURANT);
        FoodOrderingSecurityAuth.login(restaurantUser);

        RestaurantServiceImpl restaurantService = new RestaurantServiceImpl();
        restaurantService.createRestaurant(restaurant);

        // STEP 2: Add menu items
        MenuItems item1 = new MenuItems(101, 1,"Paneer Tikka", 220,"paneer", Cuisine.INDIAN);
        item1.setRestaurantId(1);
        restaurantService.createMenuItems(1, item1);

        MenuItems item2 = new MenuItems(102, 1,"Butter Naan", 50, "naan",Cuisine.INDIAN);
        item2.setRestaurantId(1);
        restaurantService.createMenuItems(1, item2);

        // STEP 3: Register a user
        User user = new User(2000, "gaurang", "987654",new ArrayList<>(),new ArrayList<>(), Role.USER);
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUser(user); // also logs in

        // STEP 4: Add items to cart
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(1);
        cartItem1.setMenuItemId(101);
        cartItem1.setRestaurantId(1);
        cartItem1.setQuantity(2);

        CartItem cartItem2 = new CartItem();
        cartItem2.setId(2);
        cartItem2.setMenuItemId(102);
        cartItem2.setRestaurantId(1);
        cartItem2.setQuantity(3);

        CartServiceImpl cartService = new CartServiceImpl();
        cartService.addOrRemoveItems(user.getId(), cartItem1, CartAction.ADD);
        cartService.addOrRemoveItems(user.getId(), cartItem2, CartAction.ADD);

        // STEP 5: Place the order
        OrderSercviceImpl orderService = new OrderSercviceImpl();
        Order placedOrder = orderService.createOrder(user.getId());

        System.out.println("Order Placed: ID = " + placedOrder.getOrderId() + ", Total = ₹" + placedOrder.getTotalPrice());

        // STEP 6: View user order history
        List<Order> userOrders = userService.viewOrders(user.getId());
        System.out.println("\nUser's Orders:");
        userOrders.forEach(order ->
                System.out.println("OrderId: " + order.getOrderId() + ", Amount: ₹" + order.getTotalPrice() + ", Status: " + order.getOrderStatus())
        );

        // STEP 7: View restaurant order history
        FoodOrderingSecurityAuth.login(restaurantUser); // log back in as restaurant
        List<Order> restaurantOrders = restaurantService.listOrders(restaurant.getId());
        System.out.println("\nRestaurant's Orders:");
        restaurantOrders.forEach(order ->
                System.out.println("OrderId: " + order.getOrderId() + ", User: " + order.getUserId() + ", Amount: ₹" + order.getTotalPrice())
        );
    }
}
