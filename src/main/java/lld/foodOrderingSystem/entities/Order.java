package lld.foodOrderingSystem.entities;

import lld.foodOrderingSystem.enums.Status;

import java.util.List;

public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer restaurantId;
    private List<CartItem> cartList;
    private Status orderStatus;
    private Integer totalPrice;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<CartItem> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartItem> cartList) {
        this.cartList = cartList;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(Integer orderId, Integer userId, Integer restaurantId, List<CartItem> cartList, Status orderStatus, Integer totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.cartList = cartList;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }
}
