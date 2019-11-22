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
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import  org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
import org.openqa.selenium.support.ui.Select as Select;
public class AutomationUtils {


	@Keyword
	def String getSelectedValueFromSelectCombobox(TestObject object){
		Select elementSelect = new Select(WebUI.findWebElement(object));
		return elementSelect.firstSelectedOption.getText();
	}

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
				//WebUI.click(object);

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


	/**
	 * Devulve un objecto  TestObjectProperty dependiento del selector 
	 * @param selectorType css, xpath , name or id
	 * @param searchLocator
	 * @return
	 */
	@Keyword
	def TestObjectProperty returnObjectPropretyType(String selectorType,String searchLocator){
		TestObjectProperty testOjbPropertyType = null;

		switch(selectorType.toString().toUpperCase()){
			case "CSS":
				testOjbPropertyType = new TestObjectProperty( "css", ConditionType.EQUALS, searchLocator);
				break;
			case "XPATH":
				testOjbPropertyType = new TestObjectProperty( "xpath", ConditionType.EQUALS, searchLocator);
				break;
			case "NAME":
				testOjbPropertyType = new TestObjectProperty( "name", ConditionType.EQUALS, searchLocator);
				break;
			case "NAME":
				testOjbPropertyType = new TestObjectProperty( "name", ConditionType.EQUALS, searchLocator);
				break;
			case "ID":
				testOjbPropertyType = new TestObjectProperty( "id", ConditionType.EQUALS, searchLocator);
			default:
				testOjbPropertyType = new TestObjectProperty("Empty Property "+selectorType+" "+searchLocator);
				break;
		}

		return testOjbPropertyType;
	}

	/**
	 * Devuleve un objeto o elemento de la página de forma dinamica
	 * @param testObjectId id o nombre del objeto
	 * @param selectorType tipo de selector:css, xpath,id etc
	 * @param selectorSearchCriteria selector del elemento
	 * @param waitTime tiempo de espera, en segundos
	 * @return
	 */
	@Keyword
	def TestObject findTestObject(String testObjectId,String selectorType,String selectorSearchCriteria,int waitTime){
		try{
			TestObject object = new TestObject(testObjectId);
			object.addProperty(returnObjectPropretyType(selectorType,selectorSearchCriteria));

			WebUI.waitForElementPresent(object,waitTime);
			WebUI.waitForElementVisible(object,waitTime);
			return object;
		}catch(com.kms.katalon.core.exception.StepFailedException step){
			KeywordUtil.markWarning("Elemento "+testObjectId+"  buscado con este tipo de selector "+selectorType+" y con el selector "+selectorSearchCriteria+" no es  visible");
			KeywordUtil.logger.logError(step.getMessage());
			return NullTestObject;
		}
	}

	/**
	 * Valida que un elemento de la pagina no sea visible
	 * @param testObjectId id o nombre del objeto
	 * @param selectorType tipo de selector : css, xpath o id
	 * @param selelctor : selector especifico del elemento: ver mas en https://www.swtestacademy.com/xpath-selenium/ y https://www.swtestacademy.com/css-selenium/
	 * @param waitTime tiempo de espera para el elemnto en caso de que este sea cargado dinamicamente, se mide en segundos
	 * @return true si el elemento no esta, false si el elemento esta
	 */
	@Keyword
	def boolean verifyTestObjectIsNotPresent(String testObjectId,String selectorType,String selelctor,int waitTime){
		TestObject object = new TestObject(testObjectId);
		object.addProperty(returnObjectPropretyType(selectorType,selelctor));

		return WebUI.verifyElementNotPresent(object, waitTime);
	}


	/**
	 * Funcion que traer una lista de elementos Web
	 * @param selectorType tipo de selector : ejemplo css,xpath, id
	 * @param selectorId selector a usar en contrao
	 * @return
	 */
	@Keyword
	def List<WebElement> returnElementsObjects(String selectorType,String selectorId,int waitTime){
		WebDriver driver = DriverFactory.getWebDriver();
		WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);

		List<WebElement> element =  new ArrayList<WebElement>();

		try{
			switch(selectorType.toString().toUpperCase()){
				case "CSS":
					element = webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(selectorId)) ));
					break;
				case "XPATH":
					element = webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(selectorId)) ));
					break;
				case "ID":
					element = webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id(selectorId)) ));
					break;
				default:
					break;
			}
		}catch(ElementNotVisibleException  | TimeoutException notVisibleE){
			KeywordUtil.markWarning("Elementos buscados con este tipo de selector "+selectorType+" y con el selector "+selectorId+" no son visibles");
			KeywordLogger.getInstance(this.class).logger.error("Excepcion de elemento no visible ", notVisibleE)
		}

		webDriverWait = null;
		driver = null;
		return element;
	}


	@Keyword
	def String createSnapshop(String folderLocation,String testCaseId){
		try{
			String screenshotname =testCaseId+"_"+LocalDateTime.now().toString().replace(":","-").replace(".","-");
			return WebUI.takeScreenshot(folderLocation+screenshotname+".png")
		}catch(Exception e){
			KeywordUtil.logger.logError("Error al generar el screenshot usando la ruta "+folderLocation+" y el test case id "+testCaseId);
		}
	}
}
