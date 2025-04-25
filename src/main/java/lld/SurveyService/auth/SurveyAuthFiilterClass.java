package lld.SurveyService.auth;

import lld.SurveyService.entity.SurveyUser;
import lld.SurveyService.enums.SurveyRole;

import java.util.Objects;

public class SurveyAuthFiilterClass {
    private static SurveyUser currentUser;

    public static SurveyUser getCurrentUser(){
        return currentUser;
    }
    public static void login(SurveyUser surveyUser){
        currentUser=surveyUser;
    }
    public static void logOut(){
        currentUser=null;
    }

    public static boolean isAuthorised(SurveyRole surveyRole){
        return Objects.nonNull(currentUser)
                && currentUser.getRole().equals(surveyRole);
    }

    public static boolean isSameUser(String userId){
        return Objects.nonNull(currentUser)
                && currentUser.getId().equals(userId);
    }
}
