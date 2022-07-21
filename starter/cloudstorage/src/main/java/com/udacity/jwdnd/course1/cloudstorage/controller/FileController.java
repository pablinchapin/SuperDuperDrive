package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController {

  private final FileService fileService;
  private final UserService userService;

  @Autowired
  public FileController(FileService fileService, UserService userService) {
    this.fileService = fileService;
    this.userService = userService;
  }

  @PostMapping("/add")
  public String addFile(Authentication authentication,
      @RequestParam("fileUpload") MultipartFile fileUpload, Model model)
      throws IOException {
    User user = userService.getUserByUsername(authentication.getName());

    if(fileUpload.isEmpty()){
      model.addAttribute("error", true);
    }
    fileService.persistFile(fileUpload, user.getUserId());
    model.addAttribute("success", true);
    return "result";
  }

  @GetMapping("/view/{fileId}")
  public File getFile(@PathVariable Long fileId){
    return fileService.getFileById(fileId);
  }

  @GetMapping("/delete/{fileId}")
  public String removeFile(@PathVariable Long fileId, Model model){
    try{
      fileService.removeFile(fileId);
      model.addAttribute("success", true);
    }catch (Exception e){
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", e.getMessage());
    }
    return "result";
  }

  @GetMapping("/download/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId){
    File file = fileService.getFileById(fileId);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename" + file.getFileName());
    httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
    httpHeaders.add(HttpHeaders.PRAGMA, "no-cache");
    httpHeaders.add(HttpHeaders.EXPIRES, "0");

    ByteArrayResource resource = new ByteArrayResource(file.getFileData());

    return ResponseEntity.ok()
        .headers(httpHeaders)
        .contentLength(Long.parseLong(file.getFileSize()))
        .contentType(MediaType.parseMediaType(file.getContentType()))
        .body(resource);
  }


}
