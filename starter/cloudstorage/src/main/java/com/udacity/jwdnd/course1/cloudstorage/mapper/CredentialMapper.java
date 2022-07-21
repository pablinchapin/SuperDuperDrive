package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CredentialMapper {

  @Insert("INSERT INTO CREDENTIALS (url, username, key, password, user_id) "
      + "VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialId")
  Long persist(Credential credential);

  @Select("SELECT * FROM CREDENTIALS WHERE user_id = #{userId}")
  List<Credential> getCredentialsByUserId(Long userId);

  @Select("SELECT * FROM CREDENTIALS WHERE credential_id = #{credentialId}")
  Credential getCredentialById(Long credentialId);

  @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} "
      + "WHERE credential_id = #{credentialId}")
  Long merge(Credential credential);

  @Delete("DELETE FROM CREDENTIALS WHERE credential_id = #{credentialId}")
  void remove(Long credentialId);

}
