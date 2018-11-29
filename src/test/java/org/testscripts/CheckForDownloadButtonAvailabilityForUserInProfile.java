/**

* Created by Qualitrix Technologies Pvt Ltd.

* @author: Abhinav kumar singh

* Date: 07/06/2018

* Purpose: Check for download button availability for a non org user.

*/
package org.testscripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import java.util.List;

import org.generic.ExtentTestManager;
import org.generic.GenericFunctions;
import org.generic.ReadTestDataFromExcel;
import org.openqa.selenium.support.PageFactory;
import org.page.CreateMentorPage;
import org.page.CreatorUserPage;
import org.pageobjects.CreatorUserPageObj;
import org.pageobjects.SignUpPageObj;
import org.startup.BaseTest;
import org.testdata.TestDataForSunbird;
import org.testng.annotations.Test;

public class CheckForDownloadButtonAvailabilityForUserInProfile extends BaseTest {
	
	CreatorUserPage createUserPage1=PageFactory.initElements(driver, CreatorUserPage.class);

	@Test
	public void checkForDownloadButtonAvailabilityForUserInProfile() throws Exception
	{
		SignUpPageObj creatorLogin = new SignUpPageObj();
		List <TestDataForSunbird> objListOFTestDataForSunbird1=null;
		objListOFTestDataForSunbird1 = ReadTestDataFromExcel.getTestDataForSunbird("testdatasheetcourse");
		CreatorUserPageObj cretorUserPageObj =new CreatorUserPageObj();
		GenericFunctions generic = new GenericFunctions();
		CreateMentorPage createMentorPage=PageFactory.initElements(driver, CreateMentorPage.class);
		
		//Step1: Login as Creator
		creatorLogin.userLogin(ADMIN);

		//Step2: Navigate to profile and search for user and download button shouldn't be available
		cretorUserPageObj.navigateToProfileAndSearch(objListOFTestDataForSunbird1.get(8).getCourseName());
		boolean status=generic.isElementPresent(createMentorPage.downloadButton);
		if(status==false) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Download button was not available");
			System.out.println("Download button was not available");
		}
		
		//Step3: Logout as mentor
		creatorLogin.userLogout();

	}
}
