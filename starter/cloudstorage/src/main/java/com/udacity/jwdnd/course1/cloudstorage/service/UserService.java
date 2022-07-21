package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final HashService hashService;
  private final UserMapper userMapper;

  @Autowired
  public UserService(HashService hashService, UserMapper userMapper) {
    this.hashService = hashService;
    this.userMapper = userMapper;
  }

  public boolean isUsernameAvailable(String username){
    return userMapper.getUserByUsername(username) == null;
  }

  public Long persistUser(User user){
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
    return userMapper.persist(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
  }

  public User getUserByUsername(String username){
    return userMapper.getUserByUsername(username);
  }

  public User getUserById(Long userId){
    return userMapper.getUserById(userId);
  }

}
