package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Credential {

  @FindBy(id="nav-credentials-tab")
  private WebElement navCredentialsTab;
  @FindBy(id="showCredentialModal")
  private WebElement showCredentialModal;
  @FindBy(id="credential-url")
  protected WebElement inputCredentialUrl;
  @FindBy(id="credential-username")
  protected WebElement inputCredentialUsername;
  @FindBy(id="credential-password")
  protected WebElement inputCredentialPassword;
  @FindBy(id="credential-url-item")
  protected WebElement credentialUrlItem;
  @FindBy(id="credential-username-item")
  protected WebElement credentialUsernameItem;
  @FindBy(id="credential-password-item")
  protected WebElement credentialPasswordItem;
  @FindBy(id="credentialSubmitButton")
  protected WebElement credentialSubmitButton;
  @FindBy(id="editCredentialButton")
  protected WebElement editCredentialButton;
  @FindBy(id="deleteCredentialButton")
  protected WebElement deleteCredentialButton;

  public Credential(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
  }

  public void clickNavCredentials(){
    navCredentialsTab.click();
  }
  public void clickShowCredentialModal(){
    showCredentialModal.click();
  }
  public void clickInputCredentialUrl(){
    inputCredentialUrl.click();
  }
  public void clickInputCredentialUsername(){
    inputCredentialUsername.click();
  }
  public void clickInputCredentialPassword(){
    inputCredentialPassword.click();
  }
  public void clickCredentialSubmitButton(){
    credentialSubmitButton.click();
  }
  public String getCredentialUrlText(){
    return credentialUrlItem.getAttribute("innerHTML");
  }
  public String getCredentialUsernameText(){
    return credentialUsernameItem.getAttribute("innerHTML");
  }
  public String getCredentialPasswordText(){
    return credentialPasswordItem.getAttribute("innerHTML");
  }
  public void clickEditCredentialButton(){
    editCredentialButton.click();
  }
  public void clickDeleteCredentialButton(){
    deleteCredentialButton.click();
  }

}
