package org.sunbird.testscripts;

import org.testng.annotations.Test;
import java.util.List;

import org.sunbird.generic.GenericFunctions;
import org.sunbird.generic.ReadTestDataFromExcel;
import org.sunbird.pageobjects.CreatorUserPageObj;
import org.sunbird.pageobjects.FlagReviewerPageObj;
import org.sunbird.pageobjects.SignUpPageObj;
import org.sunbird.startup.BaseTest;
import org.sunbird.testdata.TestDataForSunbird;
import org.testng.annotations.Test;

public class CheckTheflaggedContentINDraft extends BaseTest
{
	@Test
	public void checkTheflaggedContentINDraft() throws Exception
	{
		List <TestDataForSunbird> objListOFTestDataForSunbird= null ;
		objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
		
		SignUpPageObj signUpPageObj = new SignUpPageObj();
		FlagReviewerPageObj flagReviewer = new FlagReviewerPageObj();
		CreatorUserPageObj creatorUserPageObj = new CreatorUserPageObj();
		
		//Step 1: Login as content creator
		signUpPageObj.userLogin(CREATOR);
		
		//Navigate to workspace
		creatorUserPageObj.navigateToWorkspace(RESOURCE);
		
		//Create new Course
		creatorUserPageObj.createResource(objListOFTestDataForSunbird);
		
		//Step 4:Save and send resource for review
		creatorUserPageObj.saveAndSendResouceForReview();
		GenericFunctions.waitWebDriver(2000);
		GenericFunctions.refreshWebPage();
		
		//Step 4a:Check for course in review submissions 
		creatorUserPageObj.reviewInSubmissions(RESOURCE,objListOFTestDataForSunbird);
		
		GenericFunctions.waitWebDriver(3000);
		
		//Logout as Creator
		signUpPageObj.userLogout();
		
		
		//Step 5:Login as Reviewer
		signUpPageObj.userLogin(REVIEWER);
		
		//Step 6:Search the course which was submitted for review
		GenericFunctions.waitWebDriver(2000);
		String resourceToSearch=creatorUserPageObj.searchInUpForReview(RESOURCE,objListOFTestDataForSunbird);
		
		//Step 7:publish the resource and search it
		creatorUserPageObj.resourcePublishAndSearch(objListOFTestDataForSunbird);
		
		//creatorUserPageObj.resourcePublishAndSearch(objListOFTestDataForSunbird);
		
		//logout as Reviewer
		signUpPageObj.userLogout();	
		
		
		//Step 8:	String resourceToSearch ="Automation ResourceR0024";
		//Now Login As Public user
		signUpPageObj.userLogin(PUBLICUSER1);
		
		//Step 8b:Search the resource and flag it
		flagReviewer.searchAndFlagResource(resourceToSearch);
		
		//Logout as public user
		signUpPageObj.userLogout();
		
		
		//Step 9:Login as Flag reviewer
		signUpPageObj.userLogin(FLAGREVIEWER);
		
		//Step 10:Open the content - Accept the flag
		flagReviewer.acceptDiscardBlue(ACCEPT);
		GenericFunctions.waitWebDriver(2000);
		GenericFunctions.refreshWebPage();
		
		//Logout as Flag reviewer
		signUpPageObj.userLogout();
		
		//STEP 11:Login as Content creator
		signUpPageObj.userLogin(CREATOR);
		
		//Step 12:Go to the Drafts,open the content, 
		//Step 13:Edit the content and again submit for review 
		flagReviewer.editAndSubmitContent();
		GenericFunctions.waitWebDriver(3000);	
		
		//Logout as Content Creator
		signUpPageObj.userLogout();
		GenericFunctions.waitWebDriver(3000);
		
		//STEP 14:Login as Flag reviewer
		signUpPageObj.userLogin(FLAGREVIEWER);
		
		//STEP 14a and 15:Go to Up for review and reject the content
		String contentToCheckDrafts=flagReviewer.publishRequestChanges(REQUESTCHANGES);
		GenericFunctions.waitWebDriver(3000);
		GenericFunctions.refreshWebPage();
		
		//Logout as FlagReviewer
		signUpPageObj.userLogout();
		
		
		//STEP 16:Login As Content Creator to again in Drafts-STEP 16
		signUpPageObj.userLogin(CREATOR);
		
		//STEP 16A:Go to drafts and check for content
		flagReviewer.recheckInDrafts(contentToCheckDrafts);
		
		//Logout as Creator
		signUpPageObj.userLogout();
	}

}
