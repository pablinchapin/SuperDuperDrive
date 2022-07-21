package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

  private Long file_id;
  private String file_name;
  private String content_type;
  private String file_size;
  private Long user_id;
  private byte[] file_data;

  public File() {
  }

  public File(Long file_id, String file_name, String content_type, String file_size, Long user_id,
      byte[] file_data) {
    this.file_id = file_id;
    this.file_name = file_name;
    this.content_type = content_type;
    this.file_size = file_size;
    this.user_id = user_id;
    this.file_data = file_data;
  }

  public Long getFileId() {
    return file_id;
  }

  public void setFileId(Long fileId) {
    this.file_id = fileId;
  }

  public String getFileName() {
    return file_name;
  }

  public void setFileName(String fileName) {
    this.file_name = fileName;
  }

  public String getContentType() {
    return content_type;
  }

  public void setContentType(String contentType) {
    this.content_type = contentType;
  }

  public String getFileSize() {
    return file_size;
  }

  public void setFileSize(String fileSize) {
    this.file_size = fileSize;
  }

  public Long getUserId() {
    return user_id;
  }

  public void setUserId(Long userId) {
    this.user_id = userId;
  }

  public byte[] getFileData() {
    return file_data;
  }

  public void setFileData(byte[] fileData) {
    this.file_data = fileData;
  }
}
