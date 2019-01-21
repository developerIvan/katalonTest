package com.utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType as ConditionType

import internal.GlobalVariable

public class AutomationUtils {

	public TestObject loadTestObject(String objectName,TestObjectProperty testObjProperty){
		TestObject object = new TestObject(objectName!=null && !objectName.isEmpty()?objectName:" Object");
		object.addProperty(testObjProperty );
		return object;
	}

	@Keyword
	def String getObjectAttribute(String objectName,String htmlAttibute,TestObjectProperty testObjProperty,int waitTime){
		TestObject object = new TestObject(objectName!=null && !objectName.isEmpty()?objectName:" Object");
		object.addProperty(testObjProperty );

		WebUI.waitForElementVisible(object, waitTime, FailureHandling.STOP_ON_FAILURE)
		return WebUI.getAttribute(object, htmlAttibute, FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	def String getObjectAttribute(String objectName,String htmlAttibute,TestObjectProperty testObjProperty){
		TestObject object = new TestObject(objectName!=null && !objectName.isEmpty()?objectName:" Object");
		object.addProperty(testObjProperty );
		return WebUI.getAttribute(object, htmlAttibute, FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	def TestObject clickAndReturnObject(TestObject object,String testObjectName,TestObjectProperty testObjectproperty,int waitTime){
		boolean bottonIsClicked = false;
        int retryCount = 1;
		if(object == null){
			object = new TestObject(testObjectName);
			object.addProperty(testObjectproperty);
		}

		while(retryCount < 3){
			try{
				WebUI.waitForElementVisible(object , waitTime)

				WebUI.waitForElementClickable(object , waitTime)
				WebUI.click(object);
				retryCount = 3;
				return object;
			}catch(org.openqa.selenium.ElementNotVisibleException ex){
			    retryCount++;
				KeywordUtil.logInfo("elemento no visible "+ex.getMessage())
			}catch(org.openqa.selenium.ElementNotInteractableException interactiveEx){
			     retryCount++;
				 KeywordUtil.logInfo("elemento no interactivo "+interactiveEx.getMessage())
			}catch(com.kms.katalon.core.exception.StepFailedException stepEx){
			retryCount++;
			KeywordUtil.logInfo("paso no clumplido en click  "+stepEx.getMessage())
			}
		}
	}
}
