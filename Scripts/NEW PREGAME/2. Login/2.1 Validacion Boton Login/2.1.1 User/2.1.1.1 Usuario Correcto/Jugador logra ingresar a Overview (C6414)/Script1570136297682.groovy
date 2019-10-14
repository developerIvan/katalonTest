import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import bminc.eu.exceptions.LoginException as LoginException
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'), 
    [('url') : url], FailureHandling.STOP_ON_FAILURE)

Capabilities caps = ((DriverFactory.getWebDriver()) as RemoteWebDriver).getCapabilities()

String plataform = caps.getPlatform()

ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

String XPATH_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getXPathSelectorId'()

try {
    WebUI.delay(2)

    TestObject loginButton = CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Login Button ', new TestObjectProperty(
        XPATH_SELECTOR, equalsCondType, 'id(\'logIn\')'), 2)

    TestObject userPinInput = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('input_Bienvenido_user ', new TestObjectProperty(
        XPATH_SELECTOR, equalsCondType, 'id(\'user\')'), 4)

    TestObject userPasswordInput = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('input_Bienvenido_password ', 
        new TestObjectProperty(XPATH_SELECTOR, equalsCondType, 'id(\'password\')'), 4)

    WebUI.waitForElementVisible(userPinInput, 4)

    WebUI.sendKeys(userPinInput, loginUser)

    WebUI.sendKeys(userPasswordInput, loginPassword)

    //click para entrar al sitio
    CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, ' Button Entrar ', new TestObjectProperty(CSS_SELECTOR, 
        equalsCondType, '#btnEntrar'), 4)

   String overviewButton =  CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/Overview Button'), "innerText", 2);
   
   assert null !=overviewButton
   

}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = '-01'

    KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + stepE.getMessage())

    throw new LoginException('Paso de la prueba login no completado', stepE, errorCode)
} 
catch (Exception e) {
    String errorCode = '-99'

    KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + e.getMessage())

    throw new LoginException('Login Test Case fallido', e, errorCode)
} 

WebUI.waitForElementPresent(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/Overview Button'), 0)

