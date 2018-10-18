package org.sunbird.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sunbird.generic.ExtentTestManager;
import org.sunbird.generic.GenericFunctions;
import org.sunbird.generic.GetExcelFileData;
import org.sunbird.generic.ReadTestDataFromExcel;
import org.sunbird.page.CreatorUserPage;
import org.sunbird.page.FlagReviewerPage;
import org.sunbird.page.PublicUserPage;
import org.sunbird.page.UploadOrgPage;
import org.sunbird.startup.BaseTest;
import org.sunbird.testdata.TestDataForSunbird;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;


public class PublicUserPageObj extends BaseTest
{
	WebDriverWait wait = new WebDriverWait(driver,20);
	UploadOrgPage OrgUploadPage=PageFactory.initElements(driver, UploadOrgPage.class);
	PublicUserPage publicUserPage = PageFactory.initElements(driver, PublicUserPage.class);
	CreatorUserPage createUserPage= PageFactory.initElements(driver, CreatorUserPage.class);
	FlagReviewerPage flagReviewerPage=PageFactory.initElements(driver, FlagReviewerPage.class);
	static Logger log = Logger.getLogger(UploadOrgObj.class.getName());
	List <TestDataForSunbird> objListOFTestDataForSunbird= null ;
	GenericFunctions genereicFunction = new GenericFunctions();
	Actions action = new Actions(driver);
	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor)driver;


	public void changePassword() throws Exception
	{
		String aTitle=driver.getTitle();
		String eTitle="Update Password";

		try
		{
			if(aTitle.equalsIgnoreCase(eTitle))
			{	
				log.info("Page title is matching");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying the update password page is displayed");
				if(publicUserPage.newPassword.isDisplayed()&&publicUserPage.confirmPassword.isDisplayed()&&publicUserPage.passwordMessage.isDisplayed())
				{
					GenericFunctions.waitWebDriver(2000);
					Assert.assertTrue(true);
					log.info("Update password Page is displayed");
					System.out.println("Update password Page is displayed");
					GenericFunctions.waitWebDriver(3000);
					System.out.println(publicUserPage.passwordMessage.getText()+" message confirms Update password page is displayed");
				}
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on verifying Login and changing the password");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Page not matched");
			Assert.fail("Failed on verify Login and changing the Password");
		}
	}

	public void forgotPassword() throws Exception 
	{
		try
		{
			GenericFunctions.waitForPageToLoad(OrgUploadPage.loginButton);
			//Click login button on Home Page
			OrgUploadPage.loginButton.click();
			GenericFunctions.waitWebDriver(2000);
			//Click Forgot password link
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying the forgot password functionality");
			publicUserPage.forgotPasswordLink.click();
			GenericFunctions.waitWebDriver(3000);

			//Enter the Username, email or registered phone number
			publicUserPage.enterUsername.sendKeys(TEST_MAIL_ID);
			GenericFunctions.waitWebDriver(2000);
			publicUserPage.forgotSubmit.click();
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.waitForPageToLoad(publicUserPage.getMailConfirmation);
			if(publicUserPage.getMailConfirmation.isDisplayed())
			{
				String getMessage=publicUserPage.getMailConfirmation.getText();
				log.info(getMessage);
				System.out.println(getMessage);
				Assert.assertTrue(true);
				GenericFunctions.waitWebDriver(2000);
				publicUserPage.backToApplicationLink.click();
				GenericFunctions.waitWebDriver(2000);

			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on verifying Forgot password functionality");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Problem in Resetting Password");
			Assert.fail("Failed on verifying forgot password");
		}
	}
	public void homePageTodo() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "To verify home page TO-DO section");
			GenericFunctions.waitForElementToAppear(publicUserPage.headerHome);
			System.out.println(driver.getCurrentUrl());
			Assert.assertEquals(A_HOME_URL,driver.getCurrentUrl(),"Home page title is not matching");
			GenericFunctions.waitWebDriver(2000);
			if(publicUserPage.toDo.isDisplayed())
			{
				System.out.println("TO DO :"+publicUserPage.toDoCount.getText());
				GenericFunctions.waitWebDriver(2000);
				Assert.assertTrue(true);
				log.info("Verified To Do Section in Home page");
				System.out.println("Verified To Do Section in Home page");
			}
			else
			{
				System.out.println("To Do section is not dsiaplyed");
			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on verifying TO DO section in home page");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Failed to verify To Do Section");
			Assert.fail("Failed on verifying TO DO section on Home page");
		}
	}

	public void courseSearch() throws Exception
	{
		List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
		objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying the course search");
			GenericFunctions.waitForElementToAppear(createUserPage.headerCourse);
			createUserPage.headerCourse.click();
			GenericFunctions.waitWebDriver(2000);
			createUserPage.searchInput.sendKeys(SEARCH_COURSE);
			createUserPage.searchIcon.click();
			GenericFunctions.waitWebDriver(3000);
			if(publicUserPage.showResults.isDisplayed())
			{
				Assert.assertTrue(true);
				System.out.println("Course :"+publicUserPage.searchedCourses1.getText());
				System.out.println(publicUserPage.showResults.getText()+" for "+SEARCH_COURSE);
				js.executeScript("scroll(0, 250);");
				GenericFunctions.waitWebDriver(2000);
				js.executeScript("scroll(0, -250);");
				GenericFunctions.waitWebDriver(5000);

			}
		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching the course");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not search the course");
			Assert.fail("Failed on searching the courses");
		}
	}

	public void librarySearch() throws Exception
	{
		Robot robot = new Robot();
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying the library search");
			GenericFunctions.waitForElementToAppear(createUserPage.headerLibrary);
			createUserPage.headerLibrary.click();

			for(int i=0;i<searchInput.length;i++)
			{	
				GenericFunctions.waitWebDriver(6000);
				createUserPage.searchInput.clear();
				createUserPage.searchInput.sendKeys(searchInput[i]);
				createUserPage.searchIcon.click();
				GenericFunctions.waitWebDriver(4000);
				if(publicUserPage.showResults.isDisplayed())
				{
					Assert.assertTrue(true);
					System.out.println("Searched result :"+publicUserPage.searchedCourses1.getText());
					System.out.println(publicUserPage.showResults.getText()+" for "+searchInput[i]);
					js.executeScript("scroll(0, 250);");
					GenericFunctions.waitWebDriver(2000);
					js.executeScript("scroll(0, -250);");
					GenericFunctions.waitWebDriver(5000);
					Assert.assertTrue(true);

				}
				else
				{
					System.out.println("Results are not displayed for the inputs");
				}
			}

		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching in library and getting the results");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not search and get the results in the library");
			Assert.fail("Failed on searching in the library");
		}

	}
	public void courseSearchFilter() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying the course search by applying filters");
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			GenericFunctions.waitForElementToAppear(createUserPage.headerCourse);
			createUserPage.headerCourse.click();
			GenericFunctions.waitForElementToAppear(createUserPage.searchInput);
			createUserPage.searchInput.sendKeys(objListOFTestDataForSunbird.get(0).getCourseName());
			publicUserPage.filterIcon.click();
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitForElementToAppear(publicUserPage.clickFilterSubject);
			publicUserPage.clickFilterBoard.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterBoard);
			publicUserPage.selectFilterBoard.click();
			publicUserPage.clickFilterClass.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterClass);
			publicUserPage.selectFilterClass.click();
			publicUserPage.clickFilterSubject.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterSubject);
			publicUserPage.selectFilterSubject.click();
			GenericFunctions.waitWebDriver(1500);
			GenericFunctions.waitWebDriver(1500);
			publicUserPage.clickFilterMedium.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterMedium);
			publicUserPage.selectFilterMedium.click();
			GenericFunctions.waitWebDriver(2000);
			publicUserPage.clickContentTypes.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectContentType);
			publicUserPage.selectContentType.click();
			publicUserPage.clickApply.click();
			try
			{
				WebElement result = GenericFunctions.waitForElementToAppear(publicUserPage.courseToBeClicked);

				if(result.isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Filters are applied and "+publicUserPage.courseToBeClicked.getText()+" is the resultant course");

					js.executeScript("scroll(0, 250);"); 	
					GenericFunctions.waitWebDriver(2000);
					js.executeScript("scroll(0, -250);");
					GenericFunctions.waitWebDriver(3000);
					Assert.assertTrue(true);
					System.out.println("Filter are applied ");
				}
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Filters are applied but could not get the resultant course");
				ExtentTestManager.getTest().log(LogStatus.INFO,"Exception Message: "+e.getLocalizedMessage());
			}



		}
		catch(Exception e)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching courses by applying filters ");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not apply filters and search the courses");
			Assert.fail("Failed on searching courses by applying filters");
		}


	}

	public void librarysearchFilter() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying the library search by applying filters");
			List <TestDataForSunbird> objListOFTestDataForSunbird=null;
			objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
			wait.until(ExpectedConditions.visibilityOf(createUserPage.headerLibrary));
			String randomContent = (CONTENT_TYPE[new Random().nextInt(CONTENT_TYPE.length)]);

			GenericFunctions.waitForElementToAppear(createUserPage.headerLibrary);
			createUserPage.headerLibrary.click();
			GenericFunctions.waitWebDriver(1600);
			publicUserPage.filterIcon.click();
			GenericFunctions.waitWebDriver(2000);
			publicUserPage.clickFilterBoard.click();
			GenericFunctions.waitWebDriver(3000);
			publicUserPage.selectFilterBoard.click();
			GenericFunctions.waitWebDriver(2000);
			publicUserPage.clickFilterClass.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterClass);
			publicUserPage.selectFilterClass.click();
			GenericFunctions.waitWebDriver(1500);
			publicUserPage.clickFilterSubject.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterSubject);
			publicUserPage.selectFilterSubject.click();
			GenericFunctions.waitWebDriver(1500);
			publicUserPage.clickFilterMedium.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.selectFilterMedium);
			publicUserPage.selectFilterMedium.click();
			GenericFunctions.waitWebDriver(1500);
			publicUserPage.clickContentTypes.click();
			GenericFunctions.waitWebDriver(2000);
			publicUserPage.selectContentType.click();
			publicUserPage.clickApply.click();

			try
			{
				WebElement result = GenericFunctions.waitForElementToAppear(publicUserPage.courseToBeClicked);

				if(result.isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Filters are applied and "+publicUserPage.courseToBeClicked.getText()+" is the resultant Content");

					js.executeScript("scroll(0, 250);"); 	
					GenericFunctions.waitWebDriver(2000);
					js.executeScript("scroll(0, -250);");
					GenericFunctions.waitWebDriver(3000);
					Assert.assertTrue(true);
					System.out.println("Filter are applied ");
				}
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Filters are applied but could not get the resultant Content");
				ExtentTestManager.getTest().log(LogStatus.INFO,"Exception Message: "+e.getLocalizedMessage());
			}
			
		}
		catch(Exception e)	
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching in library by applying filters ");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not apply filters and search in the library");
			Assert.fail("Failed on searching library by applying filters");
		}

	}

	public void updateProfileImage() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying the update profile image");
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(3000);
			action.moveToElement(publicUserPage.imageEditIcon).click().perform();
			String path = System.getProperty("user.dir")+"/uploadingDocuments/upload Document Contents\\"+UPLOAD_PROFILE_PIC;
			System.out.println("Uploaded file name: "+path);
			log.info("Uploaded file name: "+path);
			GenericFunctions.waitWebDriver(3000);
			GenericFunctions.uploadFile(path);
			GenericFunctions.waitWebDriver(3000);
			Assert.assertTrue(true,"Profile picture updated succesfully");
		}
		catch(Exception e )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on updating profile image");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not upload profile image");
			Assert.fail("Failed on updating profile image");
		}
		
	}

	public void userSearch() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying the user search in the profile page");
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(3000);
			for(int i=0;i<SEARCH_USERS.length;i++)
			{	
				GenericFunctions.waitForElementToAppear(createUserPage.searchInput);
				createUserPage.searchInput.sendKeys(SEARCH_USERS[i]);
				GenericFunctions.waitWebDriver(1000);
				createUserPage.searchIcon.click();
				GenericFunctions.waitWebDriver(4000);
				if(publicUserPage.searchedInputResult.isDisplayed())
				{	
					js.executeScript("scroll(0, 450);");
					GenericFunctions.waitWebDriver(2000);
					js.executeScript("scroll(0, -200);");
					js.executeScript("scroll(0, -250);");
					Assert.assertTrue(true);
					System.out.println("Getting results : "+publicUserPage.searchedInputResult.getText()+ " are the input :" +SEARCH_USERS[i]);
					GenericFunctions.waitWebDriver(3000);
					createUserPage.searchInput.clear();

				}
				else
				{
					System.out.println(publicUserPage.searchedInputResult+"element is not displayed");
				}


			}
		}
		catch(Exception e )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching the users");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not search users");
			Assert.fail("Failed on searching Users in profile page");
		}

	}

	public void searchOrgs() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying search Organizations search in Profile page");
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(3000);
			for(int i=0;i<SEARCH_ORG.length;i++)
			{	

				GenericFunctions.waitForElementToAppear(createUserPage.searchInput);
				createUserPage.searchInput.sendKeys(SEARCH_ORG[i]);
				GenericFunctions.waitWebDriver(1000);
				createUserPage.searchIcon.click();
				GenericFunctions.waitForElementToAppear(publicUserPage.searchedInputResult);
				if(publicUserPage.searchedInputResult.isDisplayed())
				{
					GenericFunctions.waitWebDriver(2000);
					js.executeScript("scroll(0, 450);");
					GenericFunctions.waitWebDriver(2000);
					js.executeScript("scroll(0, -200);");
					js.executeScript("scroll(0, -250);");
					Assert.assertTrue(true);
					System.out.println("Getting results "+publicUserPage.searchedInputResult.getText()+ " are the input " +SEARCH_ORG[i]);
					GenericFunctions.waitWebDriver(3000);
					createUserPage.searchInput.clear();
				}
				else
				{
					System.out.println(publicUserPage.searchedInputResult+"element is not displayed");
				}

			}
		}
		catch(Exception e )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on searching the Organizations");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not search Organizations");
			Assert.fail("Failed on searching Organizations in profile page");
		}

	}

	public void profileInformationUpdate() throws Exception
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying profile information update");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetprofileaddress");
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			GenericFunctions.waitForElementToAppear(publicUserPage.summaryEditButton);
			if(publicUserPage.summaryEditButton.isDisplayed())
			{

				//Edit profile summary
				ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to update profile summary");
				publicUserPage.summaryEditButton.click();
				//publicUserPage.summaryEditButton.clear();
				publicUserPage.summaryText.clear();
				publicUserPage.summaryText.sendKeys(SUMMARY_EDIT);
				publicUserPage.summarySave.click();

				GenericFunctions.waitWebDriver(2000);
				GenericFunctions.waitForElementToAppear(publicUserPage.experienceAddButton);
				//Add or edit experience
				GenericFunctions.waitForElementToAppear(publicUserPage.experienceDelete);
				publicUserPage.experienceDelete.click();
				GenericFunctions.waitWebDriver(2000);
				publicUserPage.experienceAddButton.click();
				publicUserPage.experienceOccupationField.sendKeys(EXP_OCCUPATION);
				GenericFunctions.waitWebDriver(1500);
				publicUserPage.experienceDesignationField.sendKeys(EXP_DESIGNATION);
				publicUserPage.experienceOrganizationField.sendKeys(EXP_ORG);
				publicUserPage.experienceClickSubjectDropdown.click();
				GenericFunctions.waitForElementToAppear(publicUserPage.experienceSelectSubjectDropdown);
				publicUserPage.experienceSelectSubjectDropdown.click();
				GenericFunctions.waitWebDriver(2000);
				publicUserPage.experienceSelectNoRadioButton.click();
				GenericFunctions.waitWebDriver(2500);
				publicUserPage.summarySave.click();



				//Add or Edit address
				GenericFunctions.waitWebDriver(3000);
				GenericFunctions.waitForElementToAppear(publicUserPage.checkAddressStatus);
				String address=publicUserPage.checkAddressStatus.getText();
				if(address!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to update profile address");
					GenericFunctions.waitForElementToAppear(publicUserPage.addressEditButton);
					//GenericFunctions.scrollToElement(publicUserPage.addressEditButton);
					GenericFunctions.waitWebDriver(1500);
					publicUserPage.addressEditButton.click();
					publicUserPage.addressLine1.clear();
					publicUserPage.addressLine1.sendKeys(objListOFTestDataForSunbird1.get(1).getAddressLane1());
					publicUserPage.addressLine2.clear();
					publicUserPage.addressLine2.sendKeys(objListOFTestDataForSunbird1.get(1).getAddressLane2());
					publicUserPage.addressCity.clear();
					publicUserPage.addressCity.sendKeys(objListOFTestDataForSunbird1.get(1).getCity());
					publicUserPage.addressState.clear();
					publicUserPage.addressState.sendKeys(objListOFTestDataForSunbird1.get(1).getState());
					publicUserPage.addressCountry.clear();
					publicUserPage.addressCountry.sendKeys(objListOFTestDataForSunbird1.get(1).getCountry());
					publicUserPage.addressZipcode.clear();
					publicUserPage.addressZipcode.sendKeys(objListOFTestDataForSunbird1.get(1).getPincode());
					publicUserPage.summarySave.click();

					
					GenericFunctions.waitWebDriver(2000);
					
					//Edit educational Details
					ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to update profile educational details");
					GenericFunctions.waitForElementToAppear(publicUserPage.educationEditButton);
					GenericFunctions.waitForElementToAppear(publicUserPage.educationDelete);
					publicUserPage.educationDelete.click();
					GenericFunctions.waitWebDriver(2000);
					publicUserPage.educationEditButton.click();
					publicUserPage.educationDegree.clear();
					publicUserPage.educationDegree.sendKeys(objListOFTestDataForSunbird1.get(1).getDegree());
					GenericFunctions.waitWebDriver(1000);
					publicUserPage.educationPercentage.clear();
					publicUserPage.educationPercentage.sendKeys(objListOFTestDataForSunbird1.get(1).getPercentage());
					GenericFunctions.waitWebDriver(1000);
					publicUserPage.educationGrade.clear();
					publicUserPage.educationGrade.sendKeys(objListOFTestDataForSunbird1.get(1).getGrade());
					GenericFunctions.waitWebDriver(1000);
					publicUserPage.educationInstitution.clear();
					publicUserPage.educationInstitution.sendKeys(objListOFTestDataForSunbird1.get(1).getInstitution());
					GenericFunctions.waitWebDriver(1000);
					publicUserPage.educationBoard.clear();
					publicUserPage.educationBoard.sendKeys(objListOFTestDataForSunbird1.get(1).getBoard());
					GenericFunctions.waitWebDriver(1000);

					//YOP is not handled
					GenericFunctions.waitWebDriver(1500);
					publicUserPage.summarySave.click();

					GenericFunctions.waitWebDriver(3500);
					GenericFunctions.waitForElementToAppear(publicUserPage.skillEditButton);
			
					//Add Skills
					ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to update profile skill set");
					publicUserPage.skillEditButton.click();
					//GenericFunctions.waitForElementToAppear(publicUserPage.addSkillsHeader);
					GenericFunctions.waitWebDriver(2200);
					//GenericFunctions.waitForElementToAppear(publicUserPage.deleteSkill);
					action.moveToElement(publicUserPage.deleteSkill).click().build().perform();
					publicUserPage.deleteSkill.click();
					action.moveToElement(publicUserPage.addSkills);	
					GenericFunctions.waitWebDriver(1500);
					action.click();
					action.sendKeys(objListOFTestDataForSunbird1.get(1).getSkills());
					action.build().perform();
					GenericFunctions.waitWebDriver(1000);
					publicUserPage.addSkillItem.click();
					GenericFunctions.waitWebDriver(2000);
					publicUserPage.addSkillLockUnlockIcon.click();
					GenericFunctions.waitWebDriver(500);
					publicUserPage.summarySave.click();
					GenericFunctions.waitWebDriver(3000);
					GenericFunctions.waitForElementToAppear(publicUserPage.additionalInfoEdit);
					GenericFunctions.waitWebDriver(2000);

					//Edit additional Information
					ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to update profile additional information");
					publicUserPage.additionalInfoEdit.click();
					publicUserPage.additionalFirstName.clear();
					publicUserPage.additionalFirstName.sendKeys(objListOFTestDataForSunbird1.get(1).getAfirstName());
					publicUserPage.additionalLastName.clear();
					publicUserPage.additionalLastName.sendKeys(objListOFTestDataForSunbird1.get(1).getAlastName());
					publicUserPage.additionalLocation.clear();
					publicUserPage.additionalLocation.sendKeys(objListOFTestDataForSunbird1.get(1).getCurrentlocation());
					GenericFunctions.scrollToElement(publicUserPage.knownLanguages);
					publicUserPage.knownLanguages.click();
					GenericFunctions.waitWebDriver(500);
					publicUserPage.selectLanguage.click();
					publicUserPage.summarySave.click();

					GenericFunctions.waitWebDriver(3000);
					Assert.assertTrue(true);
					System.out.println("Profile information updated sucesfully");
				}
				else
				{
					System.out.println("cannot update profile information");
				}



				/*catch(Exception e)
			{
				System.out.println("exception occured during updating profile"+e);
			}*/
			}
		}
		catch(Exception e )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on updating profile information");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not update profile information");
			Assert.fail("Failed on updating profile information");
		}

	}



	public void skillEndorsement(String username)
	{
		try
		{
			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to endorse the skill set");
			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			GenericFunctions.waitWebDriver(1000);
			createUserPage.searchInput.sendKeys(username);
			GenericFunctions.waitWebDriver(1000);
			createUserPage.searchIcon.click();
			GenericFunctions.waitWebDriver(3000);
			String endorseUser=publicUserPage.getResultsUsername.getText();
			if(username.equalsIgnoreCase(endorseUser))
			{
				GenericFunctions.waitForElementToAppear(publicUserPage.getResultsUsername);
				publicUserPage.getResultsUsername.click();
				GenericFunctions.waitWebDriver(2500);
				/*
				GenericFunctions.scrollToElement(publicUserPage.viewMoreSkills);
				GenericFunctions.waitWebDriver(1500);
				publicUserPage.viewMoreSkills.click();
				GenericFunctions.waitWebDriver(3000);
				 */
				GenericFunctions.scrollToElement(publicUserPage.endorsementIcon);
				publicUserPage.endorsementIcon.click();
				GenericFunctions.waitWebDriver(2500);
				String endorseSize=publicUserPage.endorsementCount.getText();
				int size = Integer.parseInt(endorseSize);
				System.out.println(size);
				if(size!=0)
				{
					GenericFunctions.waitWebDriver(1500);
					Assert.assertTrue(true);
					GenericFunctions.refreshWebPage();
					GenericFunctions.waitWebDriver(1500);
					System.out.println("Skills endorsed sucessfully");
				}
				else 
				{
					System.out.println("Skills are not endorsed");
				}

			}
			else
			{
				System.out.println("Username did not match to endorse the skill");
			}
		}
		catch(Exception e )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on endorsing skill set");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not endorse skill");
			Assert.fail("Failed on endorsing skill set");
		}

	}

	//Need not be added as the Flag Icon feature is removed from the application
	public void contentFlag()
	{

		List <TestDataForSunbird> objListOFTestDataForSunbird=null;
		objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
		GenericFunctions.waitForElementToAppearOnScreen(createUserPage.headerCourse);
		//Enter course name in the search bar
		createUserPage.headerCourse.click();
		createUserPage.searchInput.sendKeys(objListOFTestDataForSunbird.get(0).getCourseName());
		createUserPage.searchIcon.click();
		GenericFunctions.waitWebDriver(3000);

		//get and print the list size of all the courses
		System.out.println(publicUserPage.searchedCourses.size());

		//Converting the list type to String [] type
		String [] eleAray= new String[publicUserPage.searchedCourses.size()];
		int i=0;
		for(WebElement text:publicUserPage.searchedCourses)
		{
			System.out.println(eleAray[i]=text.getText());
			i++;
		}

		String randomCourseSearch = (eleAray[new Random().nextInt(eleAray.length)]);
		GenericFunctions.waitWebDriver(3000);
		createUserPage.searchInput.clear();
		createUserPage.searchInput.sendKeys(randomCourseSearch);
		createUserPage.searchIcon.click();
		GenericFunctions.waitWebDriver(3000);
		/*if(randomCourseSearch.equalsIgnoreCase(publicUserPage.courseToBeClicked.getText()))
		{
			System.out.println("Clicked course: "+randomCourseSearch);
			publicUserPage.courseToBeClicked.click();

		}*/

		if(publicUserPage.courseResumeButton.isDisplayed())
		{
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.scrollToElement(publicUserPage.courseResumeButton);
			publicUserPage.courseResumeButton.click();
		}

		/*for(WebElement listEle:publicUserPage.searchedCourses)
		{
			String listEleTextFormat = listEle.getText();
			System.out.println(listEleTextFormat);
		}
		 */		

		//String[] objectArray=(String[]) publicUserPage.searchedCourses.toArray();
		//System.out.println(objectArray);
		//System.out.println(objectArray[rand.nextInt(objectArray.length)]);


	}

	public String addSkill()
	{
		String username="";
		try
		{

			ExtentTestManager.getTest().log(LogStatus.INFO, "User is trying to add the skill set");
			List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
			objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetprofileaddress");

			GenericFunctions.waitForElementToAppear(createUserPage.headerProfile);
			createUserPage.headerProfile.click();
			//GenericFunctions.waitForElementToAppear(createUserPage.workSpace);
			GenericFunctions.waitWebDriver(3500);
			GenericFunctions.scrollToElement(publicUserPage.skillEditButton);

			//Add Skills
			publicUserPage.skillEditButton.click();
			GenericFunctions.waitWebDriver(1000);
			//GenericFunctions.waitForElementToAppear(publicUserPage.addSkillsHeader);
			publicUserPage.deleteSkill.click();
			action.moveToElement(publicUserPage.addSkills);
			action.click();
			GenericFunctions.waitWebDriver(2000);
			action.sendKeys(objListOFTestDataForSunbird1.get(1).getSkills()+GenericFunctions.testDataIncrementer("./testData/testIds.txt"));
			action.build().perform();
			publicUserPage.addSkill.click();
			GenericFunctions.waitWebDriver(2000);
			//publicUserPage.addSkillItem.click();
			publicUserPage.summarySave.click();
			GenericFunctions.waitWebDriver(2000);
			GenericFunctions.scrollToElement(publicUserPage.getUsername);
			username=publicUserPage.getUsername.getText();

		}
		catch(Exception e )
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed on adding the skill set");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception Message: "+e.getLocalizedMessage());
			System.out.println("Could not add skill");
			Assert.fail("Failed on adding skill set");
		}
		return username;
	}

}

