package lld.foodOrderingSystem.service.impl;

import lld.foodOrderingSystem.dbConfig.OrderPlacementDbConfig;
import lld.foodOrderingSystem.entities.CartItem;
import lld.foodOrderingSystem.enums.CartAction;
import lld.foodOrderingSystem.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CartServiceImpl implements CartService {

    @Override
    public List<CartItem> viewCart(Integer userID) {
        return OrderPlacementDbConfig.getCartItem()
                .getOrDefault(userID,new ArrayList<>());
    }

    @Override
    public List<CartItem> addOrRemoveItems(Integer userId, CartItem cartItem, CartAction cartAction) {
        // get cart for user
        List<CartItem> cartItems = OrderPlacementDbConfig.getCartItem()
                .getOrDefault(userId, new ArrayList<>());

        if(cartItems.isEmpty()&&cartAction.equals(CartAction.REMOVE)){
            throw new RuntimeException("there are no items to be removed");
        }
        if(cartAction.equals(CartAction.ADD)){
            addItem(cartItems,cartItem);
        }else{
            removeItem(cartItems,cartItem);
        }
        OrderPlacementDbConfig.getCartItem().put(userId,cartItems);
        return cartItems;
    }

    private void removeItem(List<CartItem> cartItems, CartItem cartItem) {
        Map<Integer, CartItem> cartItemMap = cartItems.stream()
                .collect(Collectors.toMap(CartItem::getMenuItemId, Function.identity()));

        if(cartItemMap.containsKey(cartItem.getMenuItemId())){
            CartItem cartItem1 = cartItemMap.get(cartItem.getMenuItemId());
            cartItems.remove(cartItem1);
        }else{
            cartItems.add(cartItem);

        }


    }

    private void addItem(List<CartItem> cartItems, CartItem cartItem) {
        //create a cartItemMap
        Map<Integer, CartItem> cartItemMap = cartItems
                .stream()
                .collect(Collectors.toMap(CartItem::getMenuItemId, Function.identity()));


        // check if menuItem already exists
        if(cartItemMap.containsKey(cartItem.getMenuItemId())){
            CartItem existing = cartItemMap.get(cartItem.getMenuItemId());
            existing.setQuantity(existing.getQuantity()+ cartItem.getQuantity());
        }else{
            cartItems.add(cartItem);
        }
    }
}
