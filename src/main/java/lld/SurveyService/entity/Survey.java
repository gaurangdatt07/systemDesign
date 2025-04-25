package lld.SurveyService.entity;

import lld.SurveyService.entity.Question;

import java.util.List;

public class Survey {
    private String id;
    private String title;
    private List<Question> questions;
    private boolean isAvailable;
    private String createdBy;

    public Survey(String id, String title, List<Question> questions, boolean isAvailable, String createdBy) {
        this.id = id;
        this.title = title;
        this.questions = questions;
        this.isAvailable = isAvailable;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
