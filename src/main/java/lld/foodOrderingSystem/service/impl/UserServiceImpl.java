package lld.foodOrderingSystem.service.impl;

import lld.foodOrderingSystem.FoodOrderingSecurityAuth;
import lld.foodOrderingSystem.dbConfig.OrderPlacementDbConfig;
import lld.foodOrderingSystem.entities.Order;
import lld.foodOrderingSystem.entities.User;
import lld.foodOrderingSystem.enums.Role;
import lld.foodOrderingSystem.service.OrderService;
import lld.foodOrderingSystem.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    @Override
    public void createUser(User user) {
        User existingUser
                = OrderPlacementDbConfig.getUsers().getOrDefault(user.getPhoneNumber(), null);
        if(Objects.nonNull(existingUser)){
            throw new RuntimeException("user already present for phone number");
        }
        OrderPlacementDbConfig.getUsers()
                .put(user.getPhoneNumber(),user);

        FoodOrderingSecurityAuth.login(user);

    }

    @Override
    public List<Order> viewOrders(Integer userId) {
        User currentUser = FoodOrderingSecurityAuth.getCurrentUser();
        if(Objects.isNull(currentUser)){
            throw new RuntimeException("ivalid session please login again");
        }

        if(!(currentUser.getId().equals(userId)&&currentUser.getRole().equals(Role.USER))){
            throw new RuntimeException("Unauthorized");
        }

        return OrderPlacementDbConfig.getOrders()
               .getOrDefault(userId,new ArrayList<>());
    }


    @Override
    public Order createOrder(Integer userId) {
        OrderService orderObject=new OrderSercviceImpl();
        return orderObject.createOrder(userId);
    }
}
