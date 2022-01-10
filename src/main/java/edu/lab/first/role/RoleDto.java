package edu.lab.first.role;

import java.util.Set;

public class RoleDto {
    private long id;
    private String role;
    private String description;

    private Set<Long> users;

    public RoleDto() {
    }

    public RoleDto(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public RoleDto(long id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
    }
}
