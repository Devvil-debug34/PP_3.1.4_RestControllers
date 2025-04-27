package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface RoleService {

    List<Role> findAll();

    void add(Role role);

    Role findByName(String name);




}
