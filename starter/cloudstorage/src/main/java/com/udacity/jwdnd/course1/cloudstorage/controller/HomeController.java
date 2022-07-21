package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private final NoteService noteService;
  private final FileService fileService;
  private final CredentialService credentialService;
  private final UserService userService;

  @Autowired
  public HomeController(NoteService noteService, FileService fileService,
      CredentialService credentialService, UserService userService) {
    this.noteService = noteService;
    this.fileService = fileService;
    this.credentialService = credentialService;
    this.userService = userService;
  }

  @GetMapping(value = {"/", "/home"})
  public ModelAndView getHome(Authentication authentication){
    ModelAndView modelAndView = new ModelAndView("home");
    User user = userService.getUserByUsername(authentication.getName());
    modelAndView.addObject("noteList", noteService.getNotesByUserId(user.getUserId()));
    modelAndView.addObject("note", new Note());
    modelAndView.addObject("credentialList", credentialService.getCredentialsByUserId(user.getUserId()));
    modelAndView.addObject("credential", new Credential());
    modelAndView.addObject("fileList", fileService.getFilesByUserId(user.getUserId()));
    modelAndView.addObject("file", new File());
    return modelAndView;
  }
}
