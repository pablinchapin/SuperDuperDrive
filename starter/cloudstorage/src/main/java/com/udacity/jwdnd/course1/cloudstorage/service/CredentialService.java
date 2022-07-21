package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

  private final CredentialMapper credentialMapper;

  @Autowired
  public CredentialService(CredentialMapper credentialMapper) {
    this.credentialMapper = credentialMapper;
  }

  public Long persistCredential(Credential credential, Long userId){
    credential.setUserId(userId);
    return credentialMapper.persist(credential);
  }

  public List<Credential> getCredentialsByUserId(Long userId){
    return credentialMapper.getCredentialsByUserId(userId);
  }

  public Credential getCredentialById(Long credentialId){
    return credentialMapper.getCredentialById(credentialId);
  }

  public Long mergeCredential(Credential credential){
    return credentialMapper.merge(credential);
  }

  public void removeCredential(Long credentialId){
    credentialMapper.remove(credentialId);
  }

}
