package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.mappers.UserMapper;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.UserDTO;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public AdminRestController(UserService userService, RoleService roleService, UserMapper userMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<User> userOpt = Optional.ofNullable(userService.findById(id));
        return userOpt.map(u -> ResponseEntity.ok(userMapper.toDto(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/roles")
    public List<String> getAllRoles() {
        return roleService.findAll().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userMapper.toEntity(userDTO);
        userService.add(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        User user = userService.findById(id);

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleService::findByName)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        userService.update(id, user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}