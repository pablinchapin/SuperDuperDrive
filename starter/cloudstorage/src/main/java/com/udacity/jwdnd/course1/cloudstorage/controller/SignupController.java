package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

  private final UserService userService;

  @Autowired
  public SignupController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String signup(){
    return "signup";
  }

  @PostMapping
  public String addUser(@ModelAttribute User user, Model model){
    String result = "";
    if(!userService.isUsernameAvailable(user.getUsername())){
      result = "The user already exists";
    }
    if(!isValid(user.getPassword())){
      result = "Password didn't match minimum requirements";
    }

    if(!result.isEmpty()){
      Long userId = userService.persistUser(user);
      model.addAttribute("success", true);
    } else {
      model.addAttribute("error", true);
      model.addAttribute("errorMessage", result);
    }

    return "signup";
  }

  private boolean isValid(String password){
    String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    return password.matches(regex);
  }
}
