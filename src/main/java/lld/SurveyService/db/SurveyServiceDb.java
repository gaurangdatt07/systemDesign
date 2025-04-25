package lld.SurveyService.db;

import lld.SurveyService.entity.Attempt;
import lld.SurveyService.entity.Survey;

import java.util.HashMap;
import java.util.List;

public class SurveyServiceDb {
    private static HashMap<String, List<Attempt>>userToAttemptsMap=new HashMap<>();
    private static HashMap<String, Survey>surveyHashMap=new HashMap<>();
    private static HashMap<String,List<Attempt>>userIdSurveyIdToAttempts=new HashMap<>();
    private static HashMap<String,Integer>userIdSurveyIdToHighestMarks=new HashMap<>();

    public static HashMap<String, List<Attempt>> getUserToAttemptsMap(){
        return userToAttemptsMap;
    }

    public  static HashMap<String, Survey> getSurveyHashMap(){
        return surveyHashMap;
    }

    public static  HashMap<String,List<Attempt>> getUserIdSurveyIdToAttempts(){
        return userIdSurveyIdToAttempts;
    }
    public static HashMap<String,Integer>userIdSurveyIdToHighestMarks(){
        return userIdSurveyIdToHighestMarks;
    }
}
