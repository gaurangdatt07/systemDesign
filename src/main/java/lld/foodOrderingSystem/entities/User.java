package lld.foodOrderingSystem.entities;

import lld.foodOrderingSystem.enums.Role;

import java.util.List;

public class User {
    private Integer id ;
    private String namee;
    private String phoneNumber;
    private List<CartItem> cart;
    private List<Order> orders;
    private Role role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(Integer id, String namee, String phoneNumber, List<CartItem> cart, List<Order> orders, Role role) {
        this.id = id;
        this.namee = namee;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.orders = orders;
        this.role = role;
    }
}


