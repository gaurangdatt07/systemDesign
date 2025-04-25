package lld.SurveyService.service;

import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Survey;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Attempt> listSurveysTakenByUser(String userId);
    Survey viewSurvey(String surveyId);
    void takeSurvey(String surveyId,String userId,Map<String,String>QuestionToOptionMap);
    List<Survey>viewAvailableSurveys();

}
