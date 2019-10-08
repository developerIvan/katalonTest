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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable

public class AutomationUtils {

	@Keyword
	public TestObject loadTestObject(String objectName,TestObjectProperty testObjProperty){
		TestObject object = new TestObject(objectName!=null && !objectName.isEmpty()?objectName:" Object");
		object.addProperty(testObjProperty );
		return object;
	}

	private static TestObject NullTestObject = new TestObject('Elemento Vacio');

	@Keyword
	def TestObject getNullObject(){
		return NullTestObject;
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
	def String getObjectAttribute(String objectName,String htmlAttibute,String type,String searchCriteria, int waitTime){

		TestObject object = new TestObject(objectName!=null && !objectName.isEmpty()?objectName:" Object");

		object.addProperty(returnObjectPropretyType(type,searchCriteria) );
		WebUI.waitForElementVisible(object , waitTime);
		WebUI.waitForElementClickable(object , waitTime);
		return WebUI.getAttribute(object, htmlAttibute, FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	def String getObjectAttribute(TestObject object,String htmlAttibute,int waitTime){
		int retryCount = 1;
		String objectAttribute = "";
		while(retryCount < 3){
			try{
				WebUI.waitForElementVisible(object , waitTime)

				WebUI.waitForElementClickable(object , waitTime)
				WebUI.click(object);

				objectAttribute = WebUI.getAttribute(object, htmlAttibute, FailureHandling.STOP_ON_FAILURE);
				retryCount = 3;
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
		return objectAttribute;
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


	@Keyword
	def TestObjectProperty returnObjectPropretyType(String selectorType,String searchLocator){
		TestObjectProperty testOjbPropertyType = null;

		switch(selectorType.toString().toUpperCase()){
			case "CSS":
				testOjbPropertyType = new TestObjectProperty( "css", ConditionType.EQUALS, searchLocator);
				break;
			case "XPATH":
				testOjbPropertyType = new TestObjectProperty( "xpath", ConditionType.CONTAINS, searchLocator);
				break;
			case "NAME":
				testOjbPropertyType = new TestObjectProperty( "name", ConditionType.CONTAINS, searchLocator);
				break;
			default:
				testOjbPropertyType = new TestObjectProperty("Empty Property "+selectorType+" "+searchLocator);
				break;
		}

		return testOjbPropertyType;
	}

	@Keyword
	def TestObject findTestObject(String testObjectId,String type,String searchCriteria,int waitTime){
		try{
			TestObject object = new TestObject(testObjectId);
			object.addProperty(returnObjectPropretyType(type,searchCriteria));

			WebUI.waitForElementPresent(object,waitTime);
			WebUI.waitForElementVisible(object,waitTime);
			return object;
		}catch(com.kms.katalon.core.exception.StepFailedException step){
			KeywordUtil.logger.logError(step.getMessage());
			return NullTestObject;
		}
	}

	
	@Keyword
	def TestObject findTestObject(String testObjectId,TestObjectProperty objProperty,int waitTime){
		try{
			TestObject object = new TestObject(testObjectId);
			object.addProperty(objProperty);

			WebUI.waitForElementPresent(object,waitTime);
			WebUI.waitForElementVisible(object,waitTime);
			return object;
		}catch(com.kms.katalon.core.exception.StepFailedException step){
			KeywordUtil.logger.logError(step.getMessage());
			return NullTestObject;
		}
	}
	@Keyword
	def void clickMultiElements(By bySelector,int waitTime){
		WebDriver driver = DriverFactory.getWebDriver();

		List<WebElement> elements = driver.findElements(bySelector);

		if(elements != null){
			for(WebElement element:elements){
				CharSequence classElement =element.getAttribute("class");
				if(!'collapsed'.contains(classElement)){
					element.click();
				}
			}
		}
	}
}
