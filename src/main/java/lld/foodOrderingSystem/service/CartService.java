package lld.foodOrderingSystem.service;

import lld.foodOrderingSystem.entities.CartItem;
import lld.foodOrderingSystem.enums.CartAction;

import java.util.List;

public interface CartService {
     List<CartItem> viewCart(Integer userID);
     List<CartItem> addOrRemoveItems(Integer userId, CartItem cartItem, CartAction cartAction);
}
