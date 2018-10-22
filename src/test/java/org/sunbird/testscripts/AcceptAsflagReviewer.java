package org.sunbird.testscripts;

import org.testng.annotations.Test;
import org.sunbird.generic.GenericFunctions;
import org.sunbird.pageobjects.FlagReviewerPageObj;
import org.sunbird.pageobjects.SignUpPageObj;
import org.sunbird.startup.BaseTest;
import org.testng.annotations.Test;

public class AcceptAsflagReviewer extends BaseTest
{
	@Test
	public void acceptAsflagReviewer() throws Exception
	{
		//Step1: Step4: Login as Public user
		SignUpPageObj signUpPageObj = new SignUpPageObj();
		FlagReviewerPageObj flagReviewer = new FlagReviewerPageObj();
		
		//Step2: Login as Public user
		signUpPageObj.userLogin(PUBLICUSER1);
		
		//Step3: Search a course and Flag it
		flagReviewer.courseSearchAndFlagIt();
		
		//Step4: Logout as Public user
		signUpPageObj.userLogout();
		
		//Step5: Login as FlagReviewer
		signUpPageObj.userLogin(FLAGREVIEWER);
		
		//Step6: Navigate to workspace, click Flagged and accept the flagged course
		flagReviewer.acceptDiscardFlag(ACCEPT);
		GenericFunctions.refreshWebPage();
		GenericFunctions.waitWebDriver(2000);
		
		//Step7: Logout as Flag reviewer
		signUpPageObj.userLogout();
	}

}
