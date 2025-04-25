package lld.SurveyService.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Attempt {
    private String id;
    private String surveyId;
    private String userId;
    private Map<String,String> questionIdToOptionIdMap;
    private Integer totalScore;
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, String> getQuestionIdToOptionIdMap() {
        return questionIdToOptionIdMap;
    }

    public void setQuestionIdToOptionIdMap(Map<String, String> questionIdToOptionIdMap) {
        this.questionIdToOptionIdMap = questionIdToOptionIdMap;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Attempt(String id, String surveyId, String userId, Map<String, String> questionIdToOptionIdMap, Integer totalScore, Date createdAt) {
        this.id = id;
        this.surveyId = surveyId;
        this.userId = userId;
        this.questionIdToOptionIdMap = questionIdToOptionIdMap;
        this.totalScore = totalScore;
        this.createdAt = createdAt;
    }
}
