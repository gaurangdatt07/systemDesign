package lld.foodOrderingSystem.dbConfig;

import lld.foodOrderingSystem.entities.*;

import java.util.HashMap;
import java.util.List;

public class OrderPlacementDbConfig {
    private static HashMap<Integer, List<CartItem>>userToCartMap= new HashMap<>();
    private static HashMap<Integer,List<Order>>UserToOrderMap=new HashMap<>();
    private static HashMap<Integer,List<Order>>restaurantToOrderMap=new HashMap<>();
    private static HashMap<String, User>userMap=new HashMap<>();
    private static HashMap<Integer, List<MenuItems>>restaurantToMenuMap=new HashMap<>();
    private static HashMap<String, Restaurant>restaurantHashMap=new HashMap<>();



    public  static HashMap<Integer, List<CartItem>> getCartItem(){
        return userToCartMap;
    }

    public static HashMap<Integer,List<Order>> getOrders(){
        return UserToOrderMap;
    }
    public static HashMap<Integer,List<Order>> getRestaurantToOrderMap(){
        return restaurantToOrderMap;
    }

    public static HashMap<String, User> getUsers(){
        return userMap;
    }

    public static HashMap<Integer, List<MenuItems>>getRestaurantToMenuMap(){
        return restaurantToMenuMap;
    }

    public static HashMap<String,Restaurant>getRestaurantHashMap(){
        return restaurantHashMap;
    }
}
