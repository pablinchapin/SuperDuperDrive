package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
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
@RequestMapping("/notes")
public class NoteController {

  private final NoteService noteService;
  private final UserService userService;

  @Autowired
  public NoteController(NoteService noteService, UserService userService) {
    this.noteService = noteService;
    this.userService = userService;
  }

  @PostMapping("/add")
  public String addNote(Authentication authentication, Note note, Model model){
    User user = userService.getUserByUsername(authentication.getName());

    if(note.getNoteId() != null){
      noteService.mergeNote(note);
    }else{
        noteService.persistNote(note, user.getUserId());
    }
    model.addAttribute("success", true);
  return "result";
  }

  @GetMapping("/{noteId}")
  public Note getNote(@PathVariable Long noteId){
    return noteService.getNoteById(noteId);
  }

  @GetMapping("/delete/{noteId}")
  public String removeNote(@PathVariable Long noteId, Model model){
    try {
      noteService.removeNote(noteId);
      model.addAttribute("success", true);
    }catch (Exception e){
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", e.getMessage());
    }
    return "result";
  }

}
