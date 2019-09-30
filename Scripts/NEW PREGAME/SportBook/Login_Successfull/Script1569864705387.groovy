import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import bminc.eu.exceptions.LoginException
import internal.GlobalVariable as GlobalVariable

if (!GlobalVariable.pregameSiteEsVisible) {
	WebUI.callTestCase(findTestCase('NEW PREGAME/Site/Abrir Pregame Site'), [('url') : findTestData('PreGameTestData').getValue(3, 1)],
	 FailureHandling.STOP_ON_FAILURE)
 }


Capabilities caps = ((RemoteWebDriver) DriverFactory.getWebDriver()).getCapabilities()


String plataform = caps.getPlatform();

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'();

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final String XPATH_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getXPathSelectorId'();



if(!plataform.equalsIgnoreCase("ANDROID")){
	WebUI.maximizeWindow()
}else{
	GlobalVariable.mobileBrowser = true;
}
try{

	WebUI.delay(2)

	TestObject loginButton = CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null,"Login Button ", new TestObjectProperty(XPATH_SELECTOR, equalsCondType, "id('logIn')"),20);

	TestObject userPinInput = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('input_Bienvenido_user ', new TestObjectProperty(XPATH_SELECTOR, equalsCondType, "id('user')"), 4);
	
	TestObject userPasswordInput = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('input_Bienvenido_password ', new TestObjectProperty(XPATH_SELECTOR, equalsCondType, "id('password')"), 4);

	WebUI.waitForElementVisible(userPinInput,4);
	
	WebUI.sendKeys(userPinInput, loginUser)

	WebUI.sendKeys(userPasswordInput,loginPassword)

	//click para entrar al sitio
   CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null," Button Entrar ", new TestObjectProperty( CSS_SELECTOR, equalsCondType, "#btnEntrar"),4);
	//getObjectAttribute  .CustomerID
   
 
 
  

}catch(com.kms.katalon.core.exception.StepFailedException stepE){
 	String errorCode = '-01'
	KeywordUtil.logger.logError("Error code: "+errorCode+" error message :"+stepE.getMessage())
	throw new LoginException("Paso de la prueba login no completado", stepE, errorCode)
}catch(Exception e){
	String errorCode = '-99'
	KeywordUtil.logger.logError("Error code: "+errorCode+" error message :"+e.getMessage())
	throw new LoginException("Login Test Case fallido", e, errorCode)
}
