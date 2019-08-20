package com.mastery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mastery.entity.User;
import com.mastery.service.UserService;

@RestController
@CrossOrigin
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/users/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable("id") Long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {
    Long id = userService.saveUser(user);

    UriComponents uriComponent = ucBuilder.path("/users/{id}").buildAndExpand(id);
    return ResponseEntity.created(uriComponent.toUri()).build();
  }

  @PutMapping("/users/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
    user.setUserId(id);

    userService.updateUser(user);
  }

  @DeleteMapping("/users/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
  }

}