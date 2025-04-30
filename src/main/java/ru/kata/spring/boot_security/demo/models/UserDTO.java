package ru.kata.spring.boot_security.demo.models;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;

    public UserDTO() {
    }
    public UserDTO(int id, String firstName, String lastName, String email, Set<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.id = id;
    }

    public static UserDTO fromUser(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), roleNames);
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
