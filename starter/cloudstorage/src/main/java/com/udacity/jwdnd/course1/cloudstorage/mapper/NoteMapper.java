package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {

  @Insert("INSERT INTO NOTES (note_title, note_description, user_id) "
      + "VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  Long persist(Note note);

  @Select("SELECT * FROM NOTES WHERE user_id = #{userId}")
  List<Note> getNotesByUserId(Long userId);

  @Select("SELECT * FROM NOTES WHERE note_id = #{noteId}")
  Note getNoteById(Long noteId);

  @Update("UPDATE NOTES SET note_title = #{noteTitle}, note_description = #{noteDescription} "
      + "WHERE note_id = #{noteId}")
  Long merge(Note note);

  @Delete("DELETE FROM NOTES WHERE note_id = #{noteId}")
  void remove(Long noteId);

}
