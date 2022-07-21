package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private final FileMapper fileMapper;

  @Autowired
  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  public Long persistFile(MultipartFile multipartFile, Long userId) throws IOException {
    File file = new File();
    file.setFileName(multipartFile.getOriginalFilename());
    file.setContentType(multipartFile.getContentType());
    file.setFileSize(String.valueOf(multipartFile.getSize()));
    file.setUserId(userId);
    file.setFileData(multipartFile.getBytes());

    return fileMapper.persist(file);
  }

  public File getFileById(Long fileId){
    return fileMapper.getFileById(fileId);
  }

  public List<File> getFilesByUserId(Long userId){
    return fileMapper.getFilesByUserId(userId);
  }

  public List<File> getFiles(){
    return fileMapper.getFiles();
  }

  public void removeFile(Long fileId){
    fileMapper.remove(fileId);
  }

}
