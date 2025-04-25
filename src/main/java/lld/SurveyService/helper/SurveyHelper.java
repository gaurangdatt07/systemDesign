package lld.SurveyService.helper;

public class SurveyHelper {
    public static String getUserSurveyKey(String userId,String surveyId){
        return userId+":"+surveyId;
    }
    public static String getQuestionOptionKey(String questionId,String optionId){return questionId+":"+optionId;}
}
