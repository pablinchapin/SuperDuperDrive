package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Note {
  @FindBy(id="nav-notes-tab")
  private WebElement navNotesTab;
  @FindBy(id="showNoteModal")
  private WebElement showNoteModal;
  @FindBy(id="note-title")
  protected WebElement inputNoteTitle;
  @FindBy(id="note-description")
  protected WebElement inputNoteDescription;
  @FindBy(id="note-title-item")
  protected WebElement noteTitleItem;
  @FindBy(id="note-desc-item")
  protected WebElement noteDescriptionItem;
  @FindBy(id="noteSubmitButton")
  protected WebElement noteSubmitButton;
  @FindBy(id="editNoteButton")
  protected WebElement editNoteButton;
  @FindBy(id="deleteNoteButton")
  protected WebElement deleteNoteButton;


  public Note(WebDriver webDriver){
    PageFactory.initElements(webDriver, this);
  }

  public void clickNavNotes(){
    navNotesTab.click();
  }
  public void clickShowNoteModal(){
    showNoteModal.click();
  }
  public void clickInputNoteTitle(){
    inputNoteTitle.click();
  }
  public void clickInputNoteDescription(){
    inputNoteDescription.click();
  }
  public void clickNoteSubmitButton(){
    noteSubmitButton.click();
  }
  public String getNoteTitleText(){
    return noteTitleItem.getAttribute("innerHTML");
  }
  public String getNoteDescriptionText(){
    return noteDescriptionItem.getAttribute("innerHTML");
  }
  public void clickEditNoteButton(){
    editNoteButton.click();
  }
  public void clickDeleteNoteButton(){
    deleteNoteButton.click();
  }

}
