package lld.SurveyService.entity;

import java.util.List;

public class Question {
    private String id;
    private String text;
    private List<Option> options;

    public Question(String id, String text, List<Option> options) {
        this.id = id;
        this.text = text;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
