package lld.MedicalPerscriptionManagementSystem.entity;

public class User {
    private Integer id;
    private Role role;

    public User(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
