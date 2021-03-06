/**
 * Created by Qualitrix Technologies Pvt Ltd.
 * @author: Richa Prakash
 * Date: 28/08/2018
 * Purpose:  Verify that the uploaded content pop up is displaying 
 * when clicking on the upload content 
 */

package org.testscripts.regression;

import org.pageobjects.ContentCreateUploadPageObj;
import org.pageobjects.CreatorUserPageObj;
import org.pageobjects.SignUpPageObj;
import org.startup.BaseTest;
import org.testng.annotations.Test;

public class SeeTheContentBasedOnAppliedFilter extends BaseTest
{
	//Files from RIcha yet to update
	@Test

	public void seeTheContentBasedOnAppliedFilter() throws Exception
	{
		SignUpPageObj signUpPageObj = new SignUpPageObj();
		CreatorUserPageObj createUserPageObj = new CreatorUserPageObj();
		ContentCreateUploadPageObj contentCreateUploadPageObj = new ContentCreateUploadPageObj(); 

		//Step no.1 :Login as reviewer
		signUpPageObj.userLogin(CREATOR);

		//Step no.2 :Navigate to workspace and show filter
		contentCreateUploadPageObj.showFilter();

	}	
}
