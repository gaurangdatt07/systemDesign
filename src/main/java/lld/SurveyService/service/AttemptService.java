package lld.SurveyService.service;

import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Survey;

import java.util.List;
import java.util.Map;

public interface AttemptService {
    List<Attempt> viewAllAttempts();
    List<Attempt> getForUserAndSurveyId(String surveyId,String userId);
    void addAttemptToSurvey(String surveyId, String userId, Map<String,String>questionToOptionMap);
}
