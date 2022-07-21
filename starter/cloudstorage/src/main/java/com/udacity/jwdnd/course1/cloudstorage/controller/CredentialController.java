package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

  private final CredentialService credentialService;
  private final UserService userService;

  @Autowired
  public CredentialController(CredentialService credentialService, UserService userService) {
    this.credentialService = credentialService;
    this.userService = userService;
  }

  @PostMapping("/add")
  public String addCredential(Authentication authentication, Credential credential, Model model){
    User user = userService.getUserByUsername(authentication.getName());

    if(credential.getCredentialId() != null){
      credentialService.mergeCredential(credential);
    }else{
        credentialService.persistCredential(credential, user.getUserId());
    }
    model.addAttribute("success", true);
    return "result";
  }

  @GetMapping("/delete/{credentialId}")
  public String removeCredential(@PathVariable Long credentialId, Model model){
    try{
      credentialService.removeCredential(credentialId);
      model.addAttribute("success", true);
    }catch (Exception e){
      model.addAttribute("error", true);
      model.addAttribute("errorMessage", e.getMessage());
    }
    return "result";
  }

}
