package lld.SurveyService.service.impl;

import lld.SurveyService.auth.SurveyAuthFiilterClass;
import lld.SurveyService.db.SurveyServiceDb;
import lld.SurveyService.entity.Survey;
import lld.SurveyService.enums.SurveyRole;
import lld.SurveyService.service.SurveyService;

import java.util.List;

public class SurveyServiceImpl implements SurveyService {
    @Override
    public Survey fetchSurveyById(String surveyId) {
        return SurveyServiceDb.getSurveyHashMap()
                .getOrDefault(surveyId,null);
    }

    @Override
    public void createSurvey(Survey survey) {
        if(!SurveyAuthFiilterClass.isAuthorised(SurveyRole.ADMIN)){
            throw new RuntimeException("only admins can create survey");
        }
        SurveyServiceDb.getSurveyHashMap()
                .put(survey.getId(),survey);
    }

    @Override
    public List<Survey> fetchAllSurveys(boolean showOnlyAvailable) {
        List<Survey> surveys = SurveyServiceDb.getSurveyHashMap()
                .values().stream().toList();

        return showOnlyAvailable?filterOutAvailableSurveys(surveys):surveys;
    }

    private List<Survey> filterOutAvailableSurveys(List<Survey> surveys) {
        return surveys
                .stream()
                .filter(Survey::isAvailable)
                .toList();
    }
}
