package lld.SurveyService.service.impl;

import lld.SurveyService.auth.SurveyAuthFiilterClass;
import lld.SurveyService.db.SurveyServiceDb;
import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Survey;
import lld.SurveyService.enums.SurveyRole;
import lld.SurveyService.service.AttemptService;
import lld.SurveyService.service.SurveyService;
import lld.SurveyService.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public List<Attempt> listSurveysTakenByUser(String userId) {
        checkUseerAuth(userId);
        return SurveyServiceDb.getUserToAttemptsMap()
                .getOrDefault(userId,new ArrayList<>());

    }

    private static void checkUseerAuth(String userId) {
        if(!(SurveyAuthFiilterClass.isAuthorised(SurveyRole.USER)&&SurveyAuthFiilterClass.isSameUser(userId))){
            throw new RuntimeException("user not authorised");
        }
    }

    @Override
    public Survey viewSurvey(String surveyId) {
        SurveyService surveyService=new SurveyServiceImpl();
        return surveyService.fetchSurveyById(surveyId);
    }

    @Override
    public void takeSurvey(String surveyId, String userId, Map<String, String> questionToOptionMap) {

        AttemptService attemptService=new AttemptServiceImpl();
        attemptService.addAttemptToSurvey(surveyId,userId,questionToOptionMap);
    }

    @Override
    public List<Survey> viewAvailableSurveys() {
        SurveyService surveyService=new SurveyServiceImpl();
        return surveyService.fetchAllSurveys(true);
    }
}
