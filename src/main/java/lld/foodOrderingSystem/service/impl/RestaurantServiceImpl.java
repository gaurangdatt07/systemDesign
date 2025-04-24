package lld.foodOrderingSystem.service.impl;

import lld.foodOrderingSystem.FoodOrderingSecurityAuth;
import lld.foodOrderingSystem.dbConfig.OrderPlacementDbConfig;
import lld.foodOrderingSystem.entities.MenuItems;
import lld.foodOrderingSystem.entities.Order;
import lld.foodOrderingSystem.entities.Restaurant;
import lld.foodOrderingSystem.entities.User;
import lld.foodOrderingSystem.enums.Role;
import lld.foodOrderingSystem.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public List<MenuItems> showMenu(Integer restaurantId) {
        isUserValid();
        return OrderPlacementDbConfig.getRestaurantToMenuMap()
                .getOrDefault(restaurantId,new ArrayList<>());
    }

    private void isUserValid() {
        User currentUser = FoodOrderingSecurityAuth.getCurrentUser();
        if(Objects.isNull(currentUser)||!currentUser.getRole().equals(Role.RESTAURANT)){
            throw new RuntimeException("not allowed to access this feature");
        }
    }

    @Override
    public List<Order> listOrders(Integer restaurantId) {
        isUserValid();
        return OrderPlacementDbConfig.getRestaurantToOrderMap()
                .getOrDefault(restaurantId,new ArrayList<>());
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        isUserValid();
        Restaurant existing = OrderPlacementDbConfig.getRestaurantHashMap()
                .getOrDefault(restaurant.restaurantUniqueKey(), null);

        if(Objects.nonNull(existing)){
            throw new RuntimeException("restaurant already exists");
        }

        OrderPlacementDbConfig.getRestaurantHashMap()
                .put(restaurant.restaurantUniqueKey(),restaurant);


    }

    @Override
    public void createMenuItems(Integer restaurantId, MenuItems menuItems) {
        // get menu for restuarant.
        List<MenuItems> menuItemsList = OrderPlacementDbConfig.getRestaurantToMenuMap()
                .getOrDefault(restaurantId, new ArrayList<>());
        // check if menu item present already
        if(menuItemsList.isEmpty()){
            menuItemsList.add(menuItems);
            OrderPlacementDbConfig.getRestaurantToMenuMap()
                    .put(restaurantId,menuItemsList);
            return;
        }
        // if not then add
        Map<String, MenuItems> menuItemsMap = menuItemsList.stream()
                .collect(Collectors.toMap(MenuItems::getName, Function.identity()));

        if(menuItemsMap.containsKey(menuItems.getName())){
            throw  new RuntimeException("item already present");
        }

        menuItemsList.add(menuItems);
        OrderPlacementDbConfig.getRestaurantToMenuMap()
                .put(restaurantId,menuItemsList);

    }
}
