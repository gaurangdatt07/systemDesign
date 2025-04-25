package lld.SurveyService.service.impl;

import lld.SurveyService.auth.SurveyAuthFiilterClass;
import lld.SurveyService.db.SurveyServiceDb;
import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Survey;
import lld.SurveyService.enums.SurveyRole;
import lld.SurveyService.helper.SurveyHelper;
import lld.SurveyService.service.AdminService;
import lld.SurveyService.service.AttemptService;
import lld.SurveyService.service.SurveyService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {
    @Override
    public void createSurvey(Survey survey) {
        if(!SurveyAuthFiilterClass.isAuthorised(SurveyRole.ADMIN)){
            throw new RuntimeException("only admins can access this");
        }
        SurveyService surveyService= new SurveyServiceImpl();
        surveyService.createSurvey(survey);
    }

    @Override
    public List<Attempt> viewSubmissions(String userId) {
        AttemptService attemptService  = new AttemptServiceImpl();
        List<Attempt> attempts = attemptService.viewAllAttempts();
        return Objects.isNull(userId)? attempts:filterAttemptsOfUser(userId, attempts);
    }

    private List<Attempt> filterAttemptsOfUser(String userId, List<Attempt> attempts) {
        Map<String, List<Attempt>> userToAttemps = attempts.stream()
                .collect(Collectors.groupingBy(Attempt::getUserId));

        return userToAttemps.getOrDefault(userId,new ArrayList<>());
    }

    @Override
    public Integer getHighestScoreOfSurveyForAUser(String surveyId, String userId) {
        String userSurveyKey = SurveyHelper.getUserSurveyKey(userId, surveyId);
        return SurveyServiceDb.userIdSurveyIdToHighestMarks()
                .getOrDefault(userSurveyKey, 0);
    }
}
