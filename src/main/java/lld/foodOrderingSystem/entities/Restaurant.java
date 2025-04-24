package lld.foodOrderingSystem.entities;

import lld.foodOrderingSystem.enums.Role;

import java.util.List;

public class Restaurant {
    private Integer id;
    private String name;
    private String address;
    private List<MenuItems> menuItemsList;
    private Role role;

    public String restaurantUniqueKey(){
        return name+":"+address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MenuItems> getMenuItemsList() {
        return menuItemsList;
    }

    public void setMenuItemsList(List<MenuItems> menuItemsList) {
        this.menuItemsList = menuItemsList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
