package com.mastery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mastery.entity.User;
import com.mastery.service.UserService;

@Controller
public class UserController {

  private Long flag_id;

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  // create new User
  @RequestMapping(value = "/users/create")
  public String createForm(Model model) {
    model.addAttribute("userone", new User());
    return "creates";
  }

  @PostMapping(value = "/users")
  public String createSubmit(@ModelAttribute User user, Model model) {
    model.addAttribute("userone", user);
    userService.saveUser(user);
    return "saveuser";
  }

  //show List users
  @GetMapping("/users")
  public String getAllUsers(Model model) {
    model.addAttribute("users", userService.findAll());
    return "users";
  }

  //show User by Id
  @GetMapping("/users/{id}")
  public String getByUserId(@PathVariable Long id, Model model) {
    User user = userService.getUserById(id);
    model.addAttribute("user", user);
    return "showuser";
  }

  //delete
  @PostMapping("/users/{id}/delete")
  public String deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return "redirect:/users";
  }

  // update
  @GetMapping(value = "/users/{id}/edit")
  public String updateForm(@PathVariable Long id, Model model) {
    model.addAttribute("userupdate", userService.getUserById(id));
    flag_id = id;
    return "updateuser";
  }

  @PostMapping(value = "/users/{id}/edit")
  public String updateSubmit(@ModelAttribute User user, Model model) {
    user.setUserId(flag_id);
    model.addAttribute("userupdate", user);
    userService.updateUser(user);
    return "resultupdate";
  }

}