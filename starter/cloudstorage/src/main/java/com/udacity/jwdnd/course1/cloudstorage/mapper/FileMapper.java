package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

  @Insert("INSERT INTO FILES (file_name, content_type, file_size, user_id, file_data)"
      + " VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  Long persist(File file);

  @Select("SELECT * FROM FILES WHERE file_id = #{fileId}")
  File getFileById(Long fileId);

  @Select("SELECT * FROM FILES WHERE user_id = #{userId}")
  List<File> getFilesByUserId(Long userId);

  @Select("SELECT * FROM FILES")
  List<File> getFiles();

  @Delete("DELETE FROM FILES WHERE file_id = #{fileId}")
  void remove(Long fileId);

}
