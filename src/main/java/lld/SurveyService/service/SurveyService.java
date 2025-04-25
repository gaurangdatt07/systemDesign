package lld.SurveyService.service;

import lld.SurveyService.entity.Survey;

import java.util.List;

public interface SurveyService {
    Survey fetchSurveyById(String surveyId);
    void createSurvey(Survey survey);

    List<Survey> fetchAllSurveys(boolean showOnlyAvailable);
}
