package lld.SurveyService.entity;

public class Option {
    private String id;
    private String text;
    private Integer marks;


    public Option(String id, String text, Integer marks) {
        this.id = id;
        this.text = text;
        this.marks = marks;
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

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
}
