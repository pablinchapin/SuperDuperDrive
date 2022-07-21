package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {

  private Long user_id;
  private String username;
  private String salt;
  private String password;
  private String first_name;
  private String last_name;

  public User() {
  }

  public User(Long user_id, String username, String salt, String password, String first_name,
      String last_name) {
    this.user_id = user_id;
    this.username = username;
    this.salt = salt;
    this.password = password;
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public Long getUserId() {
    return user_id;
  }

  public void setUserId(Long userId) {
    this.user_id = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return first_name;
  }

  public void setFirstName(String firstName) {
    this.first_name = firstName;
  }

  public String getLastName() {
    return last_name;
  }

  public void setLastName(String lastName) {
    this.last_name = lastName;
  }
}
