/**
* Created by Qualitrix Technologies Pvt Ltd.
* @author: Ajith Manjunath
* Date: 05/23/2018
* Purpose: Search for Orgs
*/

package org.sunbird.testscripts;

import org.testng.annotations.Test;
import org.sunbird.pageobjects.PublicUserPageObj;
import org.sunbird.pageobjects.SignUpPageObj;
import org.sunbird.startup.BaseTest;
import org.testng.annotations.Test;

public class SearchOrgsAsAdmin extends BaseTest
{
	@Test
	public void searchOrgsAsAdmin() throws Exception
	{
		
		SignUpPageObj userLogin = new SignUpPageObj();
		PublicUserPageObj publicUser = new PublicUserPageObj();
		
		//Step 1 :Login as any user(MENTOR)
		userLogin.userLogin(ADMIN);

		//Step 2:Search for a particular Organization	
		publicUser.searchOrgs();
		
		//Step 3:Logout as Public user
		userLogin.userLogout();

	}

}