package lld.SurveyService.service;

import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Survey;

import java.util.List;

public interface AdminService {
    void createSurvey(Survey survey);
    List<Attempt> viewSubmissions(String userId);
    Integer getHighestScoreOfSurveyForAUser(String surveyId,String userId);
}
