package lld.SurveyService;

import lld.SurveyService.auth.SurveyAuthFiilterClass;
import lld.SurveyService.entity.*;
import lld.SurveyService.enums.SurveyRole;
import lld.SurveyService.service.AdminService;
import lld.SurveyService.service.SurveyService;
import lld.SurveyService.service.UserService;
import lld.SurveyService.service.impl.AdminServiceImpl;
import lld.SurveyService.service.impl.SurveyServiceImpl;
import lld.SurveyService.service.impl.UserServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class SurveyServiceSystem {
    public static void main(String[] args) {
        // created three users
        SurveyUser gaurang = new SurveyUser(UUID.randomUUID().toString(),
               "gaurang", SurveyRole.USER );

        SurveyUser gunavina = new SurveyUser(UUID.randomUUID().toString(),
                "gaurang", SurveyRole.USER );

        SurveyUser adminUser = new SurveyUser(UUID.randomUUID().toString(),
                "admin",SurveyRole.ADMIN);

        // admin flow
        Option option1 = new Option(UUID.randomUUID().toString(),
                "option1",4);
        Option option2 = new Option(UUID.randomUUID().toString(),
                "option2",0);
        Option option3 = new Option(UUID.randomUUID().toString(),
                "option3",1);


        // TODO admin creates survey
        Question firstSurveyQuestion1  = new Question(UUID.randomUUID().toString(),"question 1",
                Arrays.asList(option1,option2,option3));
        Question firstSurveyQuestion2  = new Question(UUID.randomUUID().toString(),"question 2",
                Arrays.asList(option1,option2,option3));
        Question firstSurveyQuestion3  = new Question(UUID.randomUUID().toString(),"question 3",
                Arrays.asList(option1,option2,option3));
        Survey firstSurvey = new Survey(UUID.randomUUID().toString(),
                "first survey",
                Arrays.asList(firstSurveyQuestion1,firstSurveyQuestion2,firstSurveyQuestion3),
                true, adminUser.getId());

        Survey secondSurvey = new Survey(UUID.randomUUID().toString(),
                "first survey",
                Arrays.asList(firstSurveyQuestion1,firstSurveyQuestion2,firstSurveyQuestion3),
                false, adminUser.getId());

        // login
        SurveyAuthFiilterClass.login(adminUser);
        AdminService adminService = new AdminServiceImpl();
        adminService.createSurvey(firstSurvey);
        adminService.createSurvey(secondSurvey);


        // user will take survey
        SurveyAuthFiilterClass.logOut();
        SurveyAuthFiilterClass.login(gaurang);

        UserService userService = new UserServiceImpl();
        List<Survey> surveys = userService.viewAvailableSurveys();
        System.out.println("available survey"+surveys.stream()
                .map(Survey::getTitle).toList());

        // gaurang takes first survey
        Survey survey = surveys.get(0);
        Map<String,String> questionToOptionMap = new HashMap<>();
        questionToOptionMap.put(firstSurveyQuestion1.getId(),
                option1.getId());
        questionToOptionMap.put(firstSurveyQuestion2.getId(),
                option2.getId());
        questionToOptionMap.put(firstSurveyQuestion3.getId(),
                option3.getId());
        userService.takeSurvey(survey.getId(),gaurang.getId(),questionToOptionMap);


        // now admin can check user score
        SurveyAuthFiilterClass.logOut();
        SurveyAuthFiilterClass.login(adminUser);
        System.out.println(adminService.viewSubmissions(gaurang.getId())
                .stream().map(Attempt::getTotalScore).toList());
        System.out.println(adminService.getHighestScoreOfSurveyForAUser(firstSurvey.getId(),gaurang.getId()));

        // gaurang will take another attempt of same survey1
        SurveyAuthFiilterClass.logOut();
        SurveyAuthFiilterClass.login(gaurang);
        questionToOptionMap.replace(firstSurveyQuestion2.getId(),option1.getId());
        questionToOptionMap.replace(firstSurveyQuestion3.getId(),
                option1.getId());
        userService.takeSurvey(survey.getId(),gaurang.getId(),questionToOptionMap);



        SurveyAuthFiilterClass.logOut();
        SurveyAuthFiilterClass.login(adminUser);
        System.out.println(adminService.viewSubmissions(gaurang.getId())
                .stream().map(Attempt::getTotalScore).toList());

        System.out.println(adminService.getHighestScoreOfSurveyForAUser(firstSurvey.getId(),gaurang.getId()));
    }
}
