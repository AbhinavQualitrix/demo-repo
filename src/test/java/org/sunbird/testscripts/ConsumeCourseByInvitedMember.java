/**

* Created by Qualitrix Technologies Pvt Ltd.

* @author: Abhinav kumar singh

* Date: 07/17/2018

* Purpose: Create course as Mentor and try to create  invite only batch for it,invited member should consume it,if it is ongoing else not.

*/

package org.sunbird.testscripts;

import org.testng.annotations.Test;
import java.util.List;

import org.sunbird.generic.GenericFunctions;
import org.sunbird.generic.ReadTestDataFromExcel;
import org.sunbird.pageobjects.CreateMentorPageObj;
import org.sunbird.pageobjects.CreatorUserPageObj;
import org.sunbird.pageobjects.SignUpPageObj;
import org.sunbird.startup.BaseTest;
import org.sunbird.testdata.TestDataForSunbird;
import org.testng.annotations.Test;

public class ConsumeCourseByInvitedMember extends BaseTest
{
	@Test
	public void consumeCourseByInvitedMember() throws Exception
	{
		
		List <TestDataForSunbird> objListOFTestDataForSunbird= null ;
		objListOFTestDataForSunbird = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
		SignUpPageObj userLogin = new SignUpPageObj();
		CreatorUserPageObj creatorUserPageObj = new CreatorUserPageObj();
		CreateMentorPageObj createMentorPageObj=new CreateMentorPageObj();
		
		//Step1: Login as Mentor
		userLogin.userLogin(MENTOR);
		
		//Step2: Navigate to WorkSpace
		creatorUserPageObj.navigateToWorkspace(COURSE);
		
		//Step3: Create new Course
		creatorUserPageObj.createCourse(objListOFTestDataForSunbird);
		
		//Step4: Save and Send for Review
		creatorUserPageObj.saveAndSendCourseForReview(objListOFTestDataForSunbird);
		GenericFunctions.waitWebDriver(2000);
		GenericFunctions.refreshWebPage();
	
		//Step5: Check for course in review submissions 
		creatorUserPageObj.reviewInSubmissions(COURSE,objListOFTestDataForSunbird);
		GenericFunctions.waitWebDriver(3000);

		//Step6: Logout as Mentor
		userLogin.userLogout();
		GenericFunctions.waitWebDriver(3000);
		
		//Step7: Login as Reviewer
		userLogin.userLogin(REVIEWER);
		
		//Step8: Search the course which was submitted for review
		GenericFunctions.waitWebDriver(2000);
		creatorUserPageObj.searchInUpForReview(COURSE,objListOFTestDataForSunbird);
		
		//Step9:Publish the Course
		creatorUserPageObj.publishAndSearch(COURSE,objListOFTestDataForSunbird);
		
		//Step10: Logout as Reviewer		
		userLogin.userLogout();	
		
		//Step11:Login as Mentor
		userLogin.userLogin(MENTOR);
		
		//Step12:Search the course and create invite only batch
		String courseName=createMentorPageObj.createInviteOnlyBatch();
		
		//Step13:Logout as Mentor
		userLogin.userLogout();	
		
		//Step14:Login as Invited Member
		userLogin.userLogin(PUBLICUSER1);
		
		//Step15:Consume  the course if ongoing else not
		createMentorPageObj.navigateToCourseAndSearch(courseName);
		
		//Step16: Logout as mentor
		userLogin.userLogout();	
		
		//Step17: Login as Creator
		userLogin.userLogin(CREATOR);

		//Step18: Navigate to WorkSpace
		creatorUserPageObj.navigateToWorkspace(PUBLISHED);

		//Step19: Delete the Created item
		creatorUserPageObj.deleteCreatedItems();

		//Step20: Logout as Creator
		userLogin.userLogout();
		
		
		
		
	}
	
	
}
