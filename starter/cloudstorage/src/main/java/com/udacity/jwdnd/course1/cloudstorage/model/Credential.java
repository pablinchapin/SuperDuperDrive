package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

  private Long credential_id;
  private String url;
  private String username;
  private String key;
  private String password;
  private Long user_id;

  public Credential() {
  }

  public Credential(Long credentialId, String url, String username, String key, String password,
      Long userId) {
    this.credential_id = credentialId;
    this.url = url;
    this.username = username;
    this.key = key;
    this.password = password;
    this.user_id = userId;
  }

  public Long getCredentialId() {
    return credential_id;
  }

  public void setCredentialId(Long credentialId) {
    this.credential_id = credentialId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getUserId() {
    return user_id;
  }

  public void setUserId(Long userId) {
    this.user_id = userId;
  }
}
