package lld.SurveyService.service.impl;

import lld.SurveyService.auth.SurveyAuthFiilterClass;
import lld.SurveyService.db.SurveyServiceDb;
import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Option;
import lld.SurveyService.entity.Question;
import lld.SurveyService.entity.Survey;
import lld.SurveyService.enums.SurveyRole;
import lld.SurveyService.helper.SurveyHelper;
import lld.SurveyService.service.AttemptService;

import java.util.*;
import java.util.stream.Collectors;

public class AttemptServiceImpl implements AttemptService {
    @Override
    public List<Attempt> viewAllAttempts() {
        if(!SurveyAuthFiilterClass.isAuthorised(SurveyRole.ADMIN)){
            throw new RuntimeException("user not authorised");
        }
        return SurveyServiceDb.getUserIdSurveyIdToAttempts()
                .values().stream()
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<Attempt> getForUserAndSurveyId(String surveyId, String userId) {
        if(!SurveyAuthFiilterClass.isAuthorised(SurveyRole.ADMIN)){
            throw new RuntimeException("user not authorised");
        }

        return SurveyServiceDb.getUserIdSurveyIdToAttempts()
                .getOrDefault(SurveyHelper.getUserSurveyKey(userId,surveyId),new ArrayList<>());

    }



    @Override
    public void addAttemptToSurvey(String surveyId, String userId, Map<String, String> questionToOptionMap) {
        // get the survery from surveyId
        SurveyServiceImpl surveyService=new SurveyServiceImpl();
        Survey survey = surveyService.fetchSurveyById(surveyId);
        if(Objects.isNull(survey)){
            throw new RuntimeException("invalid survey");
        }

        List<Question> questions = survey.getQuestions();

        Map<String, Option> questionOptionKeyToOptionMap = questions.stream()
                .flatMap(question -> question.getOptions().stream()
                        .map(option -> Map.entry(SurveyHelper.getQuestionOptionKey(question.getId(),option.getId()), option)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        int totalMarks=0;
        for(Question question:questions){
            String optionId = questionToOptionMap.getOrDefault(question.getId(), null);
            if(Objects.isNull(optionId)){
                continue;
            }
            String questionOptionKey = SurveyHelper.getQuestionOptionKey(question.getId(), optionId);

            Option option = questionOptionKeyToOptionMap.getOrDefault(questionOptionKey, null);
            if(Objects.isNull(option)){
                continue;
            }

            totalMarks+= option.getMarks();

        }
        String userSurveyKey = SurveyHelper.getUserSurveyKey(userId, surveyId);
        List<Attempt> attempts = SurveyServiceDb.getUserIdSurveyIdToAttempts()
                .getOrDefault(userSurveyKey, new ArrayList<>());


        // add new attempt
        Attempt attempt=createAttemptObject(surveyId,userId,totalMarks,questionToOptionMap);
        if(attempts.isEmpty()){
            SurveyServiceDb.userIdSurveyIdToHighestMarks()
                    .put(userSurveyKey,attempt.getTotalScore());
        }else{
            Integer highestMarks = SurveyServiceDb.userIdSurveyIdToHighestMarks()
                    .getOrDefault(userSurveyKey, null);
            if(Objects.nonNull(highestMarks)&&totalMarks>highestMarks){
                SurveyServiceDb.userIdSurveyIdToHighestMarks()
                        .put(userSurveyKey,totalMarks);
            }

        }
        attempts.add(attempt);
        SurveyServiceDb.getUserIdSurveyIdToAttempts()
                .put(userSurveyKey,attempts);
    }

    private Attempt createAttemptObject(String surveyId,String userId,Integer totalMarks,Map<String, String> questionToOptionMap) {

        return new Attempt(
                UUID.randomUUID().toString(),surveyId,userId,
                questionToOptionMap,totalMarks,new Date());
    }
}
