/**
* Created by Qualitrix Technologies Pvt Ltd.
* @author: Ajith Manjunath
* Date: 06/25/2018
* Purpose : Content send for Review shown in "Review Submission"
*/

package org.sunbird.testscripts;

import org.testng.annotations.Test;
import java.util.List;

import org.sunbird.generic.GenericFunctions;
import org.sunbird.generic.ReadTestDataFromExcel;
import org.sunbird.pageobjects.CreatorUserPageObj;
import org.sunbird.pageobjects.SignUpPageObj;
import org.sunbird.startup.BaseTest;
import org.sunbird.testdata.TestDataForSunbird;
import org.testng.annotations.Test;

public class CheckInReviewSubmission extends BaseTest
{
	@Test(priority=15, groups={"Creator Group"})
	public void checkInReviewSubmission() throws Exception
	{
		List <TestDataForSunbird> objListOFTestDataForSunbird= null ;
		objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
		SignUpPageObj signupObj = new SignUpPageObj();
		CreatorUserPageObj creatorUserPageObj = new CreatorUserPageObj();
		
		//Step 1:Login as Creator
		signupObj.userLogin(CREATOR);
		
		//Step 2:Navigate to workspace to Create a course
		creatorUserPageObj.navigateToWorkspace(COURSE);
		
		//Step 3:Create a Course
		creatorUserPageObj.createCourse(objListOFTestDataForSunbird);
		GenericFunctions.waitWebDriver(2000);
		
		//Step 4:Save and submit the coursed for Review
		creatorUserPageObj.saveAndSendCourseForReview(objListOFTestDataForSunbird);
		GenericFunctions.waitWebDriver(2000);
		GenericFunctions.refreshWebPage();
		
		//Step 5:Check the course in review submissions
		creatorUserPageObj.reviewInSubmissions(COURSE,objListOFTestDataForSunbird);
		
		//Step 6:Click the course and check for Non editable layout
		creatorUserPageObj.clickInReviewSubmission();
		GenericFunctions.waitWebDriver(2000);
		
		//Step 7: Logout as creator
		signupObj.userLogout();
	}

}
