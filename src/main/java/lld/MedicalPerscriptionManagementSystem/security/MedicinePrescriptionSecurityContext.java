package lld.MedicalPerscriptionManagementSystem.security;

import lld.MedicalPerscriptionManagementSystem.entity.User;

public class MedicinePrescriptionSecurityContext {
    private static User currentUser;

    public static  void login(User user){
        currentUser=user;
    }
    public static User getCurrentUser(){
        return currentUser;
    }
    public static void logout(){
        currentUser=null;
    }
}
