package lld.foodOrderingSystem;

import lld.foodOrderingSystem.entities.User;

public class FoodOrderingSecurityAuth {
    private static  User currentUser;

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void login(User user){
        currentUser=user;
    }

    public static void logout(){
        currentUser=null;
    }
}
