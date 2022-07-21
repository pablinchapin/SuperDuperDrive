package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

  private Long note_id;
  private String note_title;
  private String note_description;
  private Long user_id;

  public Note() {
  }


  public Note(Long note_id, String note_title, String note_description, Long user_id) {
    this.note_id = note_id;
    this.note_title = note_title;
    this.note_description = note_description;
    this.user_id = user_id;
  }

  public Long getNoteId() {
    return note_id;
  }

  public void setNoteId(Long noteId) {
    this.note_id = noteId;
  }

  public String getNoteTitle() {
    return note_title;
  }

  public void setNoteTitle(String noteTitle) {
    this.note_title = noteTitle;
  }

  public String getNoteDescription() {
    return note_description;
  }

  public void setNoteDescription(String noteDescription) {
    this.note_description = noteDescription;
  }

  public Long getUserId() {
    return user_id;
  }

  public void setUserId(Long userId) {
    this.user_id = userId;
  }
}
