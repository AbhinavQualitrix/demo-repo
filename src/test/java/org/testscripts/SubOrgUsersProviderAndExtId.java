/**
* Created by Qualitrix Technologies Pvt Ltd.
* @author: Ajith Manjunath
* Date: 05/18/2018
* Purpose: Upload users for sub orgs with provider and external id
*/

package org.testscripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.pageobjects.SignUpPageObj;
import org.pageobjects.UploadOrgObj;
import org.startup.BaseTest;
import org.testng.annotations.Test;

public class SubOrgUsersProviderAndExtId extends BaseTest
{
	@Test(priority=6)
	public void subOrgUsersProviderAndExtId() throws Exception
	{
		
		SignUpPageObj adminLogin = new SignUpPageObj();
		UploadOrgObj adminUpload= new UploadOrgObj();
		
		//Step1: Admin Login
		adminLogin.userLogin(ADMIN);
		
		//Step 2: Go to profile
		//and Upload users for Sub org with provider and external ID
		adminUpload.rootAndSubOrgUserExternalAndProvider(UPLOAD_USERS_SUB_ORG);
		
		//Step 3:Logout as Admin
		adminLogin.userLogout();
	}


}
