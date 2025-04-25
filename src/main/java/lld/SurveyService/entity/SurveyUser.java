package lld.SurveyService.entity;

import lld.SurveyService.enums.SurveyRole;

public class SurveyUser {
    private String id;
    private String name;
    private SurveyRole role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SurveyRole getRole() {
        return role;
    }

    public void setRole(SurveyRole role) {
        this.role = role;
    }

    public SurveyUser(String id, String name, SurveyRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
