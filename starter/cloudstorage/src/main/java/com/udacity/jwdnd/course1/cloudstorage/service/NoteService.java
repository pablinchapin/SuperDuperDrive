package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

  private final NoteMapper noteMapper;

  @Autowired
  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public Long persistNote(Note note, Long userId){
    note.setUserId(userId);
    return noteMapper.persist(note);
  }

  public List<Note> getNotesByUserId(Long userId){
    return noteMapper.getNotesByUserId(userId);
  }

  public Note getNoteById(Long noteId){
    return noteMapper.getNoteById(noteId);
  }

  public Long mergeNote(Note note){
    return noteMapper.merge(note);
  }

  public void removeNote(Long noteId){
    noteMapper.remove(noteId);
  }

}
