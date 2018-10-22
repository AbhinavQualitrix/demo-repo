/**
* Created by Qualitrix Technologies Pvt Ltd.
* @author: Ajith Manjunath
* Date: 05/15/2018
* Purpose: Admin dashboard - creation and CSV download
*/

package org.sunbird.testscripts;

import org.testng.annotations.Test;
import org.sunbird.pageobjects.SignUpPageObj;
import org.sunbird.pageobjects.UploadOrgObj;
import org.sunbird.startup.BaseTest;
import org.testng.annotations.Test;

public class AdminCreationAndDownloadCsv extends BaseTest
{
	@Test(priority=8)
	public void adminCreationAndDownloadCsv() throws Exception
	{	
		SignUpPageObj adminLogin = new SignUpPageObj();
		UploadOrgObj adminUpload= new UploadOrgObj();
		
		//Step1: Login as Admin
		adminLogin.userLogin(ADMIN);

		//Step 2: Click on admin dashboard.
		//Apply filter, check stats 
		//Download the CSV
		adminUpload.adminCreationConsumption(FILTER_CREATION);
	
		//Step3: Logout as Admin
		adminLogin.userLogout();
	}
	


}
