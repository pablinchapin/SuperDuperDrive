package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private static final String FIRST_NAME = "Firstname";
	private static final String LAST_NAME = "Lastname";
	private static final String PASSWORD = "superduperpwd";
	//Note
	private static final String NOTE_TITLE = "Note title";
	private static final String NOTE_DESC = "Note description";
	private static final String NOTE_TITLE_UPDATED = "Note title updated";
	private static final String NOTE_DESC_UPDATED = "Note description updated";
	//Credential
	private static final String CREDENTIAL_URL = "http://www.gmail.com";
	private static final String CREDENTIAL_USERNAME = "pablo.vargas";
	private static final String CREDENTIAL_PASSWORD = "pablo.vargas";
	private static final String CREDENTIAL_USERNAME_UPDATED = "pablo.vargas.melgar";
	private static final String CREDENTIAL_PASSWORD_UPDATED = "pablo.vargas.melgar";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * <a href="https://review.udacity.com/#!/rubrics/2724/view">https://review.udacity.com/#!/rubrics/2724/view</a>
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT",PASSWORD);
		driver.get("http://localhost:" + this.port + "/login");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * <a href="https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page">https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page</a>
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT",PASSWORD);
		doLogIn("UT", PASSWORD);

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * <a href="https://spring.io/guides/gs/uploading-files/">https://spring.io/guides/gs/uploading-files/</a> under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT",PASSWORD);
		doLogIn("LFT", PASSWORD);

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	private void waitSetup(String id){
		WebDriverWait webDriverWait = new WebDriverWait(driver, 1);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	//Note Tests
	private void persistNote(Note note){
		//Persist note
		waitSetup("nav-notes-tab");
		note.clickNavNotes();

		waitSetup("showNoteModal");
		note.clickShowNoteModal();

		waitSetup("note-title");
		note.clickInputNoteTitle();
		note.inputNoteTitle.sendKeys(NOTE_TITLE);

		waitSetup("note-description");
		note.clickInputNoteDescription();
		note.inputNoteDescription.sendKeys(NOTE_DESC);

		waitSetup("noteSubmitButton");
		note.clickNoteSubmitButton();

		driver.get("http://localhost:" + this.port + "/home");

		waitSetup("nav-notes-tab");
		note.clickNavNotes();
	}

	private void updateNote(Note note){
		//Update note
		waitSetup("editNoteButton");
		note.clickEditNoteButton();

		waitSetup("note-title");
		note.clickInputNoteTitle();
		note.inputNoteTitle.clear();
		note.inputNoteTitle.sendKeys(NOTE_TITLE_UPDATED);

		waitSetup("note-description");
		note.clickInputNoteDescription();
		note.inputNoteDescription.clear();
		note.inputNoteDescription.sendKeys(NOTE_DESC_UPDATED);

		waitSetup("noteSubmitButton");
		note.clickNoteSubmitButton();

		driver.get("http://localhost:" + this.port + "/home");

		waitSetup("nav-notes-tab");
		note.clickNavNotes();
	}

	@Test
	public void notePersistTest(){
		// Create a test account
		doMockSignUp(FIRST_NAME, LAST_NAME,"npt",PASSWORD);
		doLogIn("npt", PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");

		Note note = new Note(driver);
		persistNote(note);

		Assertions.assertEquals(note.getNoteTitleText(), NOTE_TITLE);
		Assertions.assertEquals(note.getNoteDescriptionText(), NOTE_DESC);
	}

	@Test
	public void noteUpdateTest(){
		// Create a test account
		doMockSignUp(FIRST_NAME, LAST_NAME,"nut",PASSWORD);
		doLogIn("nut", PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");

		Note note = new Note(driver);
		persistNote(note);

		Assertions.assertEquals(note.getNoteTitleText(), NOTE_TITLE);
		Assertions.assertEquals(note.getNoteDescriptionText(), NOTE_DESC);

		updateNote(note);

		Assertions.assertEquals(note.getNoteTitleText(), NOTE_TITLE_UPDATED);
		Assertions.assertEquals(note.getNoteDescriptionText(), NOTE_DESC_UPDATED);
	}

	@Test
	public void noteDeleteTest(){
		// Create a test account
		doMockSignUp(FIRST_NAME, LAST_NAME,"ndt",PASSWORD);
		doLogIn("ndt", PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");

		Note note = new Note(driver);
		persistNote(note);

		Assertions.assertEquals(note.getNoteTitleText(), NOTE_TITLE);
		Assertions.assertEquals(note.getNoteDescriptionText(), NOTE_DESC);

		//Delete note
		waitSetup("deleteNoteButton");
		note.clickDeleteNoteButton();

		driver.get("http://localhost:" + this.port + "/home");

		waitSetup("nav-notes-tab");
		note.clickNavNotes();

		Assertions.assertThrows(NoSuchElementException.class, () ->{
			Assertions.assertNull(note.getNoteTitleText());
			Assertions.assertNull(note.getNoteDescriptionText());
		});
	}


	//Credential Tests
	private void persistCredential(Credential credential){
		//Persist credential
		waitSetup("nav-credentials-tab");
		credential.clickNavCredentials();

		waitSetup("showCredentialModal");
		credential.clickShowCredentialModal();

		waitSetup("credential-url");
		credential.clickInputCredentialUrl();
		credential.inputCredentialUrl.sendKeys(CREDENTIAL_URL);

		waitSetup("credential-username");
		credential.clickInputCredentialUsername();
		credential.inputCredentialUsername.sendKeys(CREDENTIAL_USERNAME);

		waitSetup("credential-password");
		credential.clickInputCredentialPassword();
		credential.inputCredentialPassword.sendKeys(CREDENTIAL_PASSWORD);


		waitSetup("credentialSubmitButton");
		credential.clickCredentialSubmitButton();

		driver.get("http://localhost:" + this.port + "/home");

		waitSetup("nav-credentials-tab");
		credential.clickNavCredentials();

	}


	private void updateCredential(Credential credential){
		//Update credential
		waitSetup("editCredentialButton");
		credential.clickEditCredentialButton();

		waitSetup("credential-username");
		credential.clickInputCredentialUsername();
		credential.inputCredentialUsername.clear();
		credential.inputCredentialUsername.sendKeys(CREDENTIAL_USERNAME_UPDATED);

		waitSetup("credential-password");
		credential.clickInputCredentialPassword();
		credential.inputCredentialPassword.clear();
		credential.inputCredentialPassword.sendKeys(CREDENTIAL_PASSWORD_UPDATED);

		waitSetup("credentialSubmitButton");
		credential.clickCredentialSubmitButton();

		driver.get("http://localhost:" + this.port + "/home");

		waitSetup("nav-credentials-tab");
		credential.clickNavCredentials();

	}



	@Test
	public void credentialPersistTest(){
		// Create a test account
		doMockSignUp(FIRST_NAME, LAST_NAME,"cpt",PASSWORD);
		doLogIn("cpt", PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");

		Credential credential = new Credential(driver);
		persistCredential(credential);

		Assertions.assertEquals(credential.getCredentialUrlText(), CREDENTIAL_URL);
		Assertions.assertEquals(credential.getCredentialUsernameText(), CREDENTIAL_USERNAME);
		Assertions.assertEquals(credential.getCredentialPasswordText(), CREDENTIAL_PASSWORD);

	}

	@Test
	public void credentialUpdateTest(){
		// Create a test account
		doMockSignUp(FIRST_NAME, LAST_NAME,"cut",PASSWORD);
		doLogIn("cut", PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");

		Credential credential = new Credential(driver);
		persistCredential(credential);

		Assertions.assertEquals(credential.getCredentialUrlText(), CREDENTIAL_URL);
		Assertions.assertEquals(credential.getCredentialUsernameText(), CREDENTIAL_USERNAME);
		Assertions.assertEquals(credential.getCredentialPasswordText(), CREDENTIAL_PASSWORD);

		updateCredential(credential);

		Assertions.assertEquals(credential.getCredentialUrlText(), CREDENTIAL_URL);
		Assertions.assertEquals(credential.getCredentialUsernameText(), CREDENTIAL_USERNAME_UPDATED);
		Assertions.assertEquals(credential.getCredentialPasswordText(), CREDENTIAL_PASSWORD_UPDATED);

	}

	@Test
	public void credentialDeleteTest(){
		// Create a test account
		doMockSignUp(FIRST_NAME, LAST_NAME,"cdt",PASSWORD);
		doLogIn("cdt", PASSWORD);

		driver.get("http://localhost:" + this.port + "/home");

		Credential credential = new Credential(driver);
		persistCredential(credential);

		Assertions.assertEquals(credential.getCredentialUrlText(), CREDENTIAL_URL);
		Assertions.assertEquals(credential.getCredentialUsernameText(), CREDENTIAL_USERNAME);
		Assertions.assertEquals(credential.getCredentialPasswordText(), CREDENTIAL_PASSWORD);

		//Delete credential
		waitSetup("deleteCredentialButton");
		credential.clickDeleteCredentialButton();

		driver.get("http://localhost:" + this.port + "/home");

		waitSetup("nav-credentials-tab");
		credential.clickNavCredentials();

		Assertions.assertThrows(NoSuchElementException.class, () ->{
			Assertions.assertNull(credential.getCredentialUrlText());
			Assertions.assertNull(credential.getCredentialUsernameText());
			Assertions.assertNull(credential.getCredentialPasswordText());
		});

	}

}
