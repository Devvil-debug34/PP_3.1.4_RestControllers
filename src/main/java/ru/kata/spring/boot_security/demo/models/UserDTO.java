package ru.kata.spring.boot_security.demo.models;

import java.util.Set;

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;
    private String password;

    public UserDTO() {
    }

    public UserDTO(int id, String firstName, String lastName, String email, Set<String> roles, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.id = id;
        this.password = password;
    }

    // Геттеры и сеттеры

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
