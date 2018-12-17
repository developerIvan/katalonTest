import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.Capabilities
WebUI.openBrowser('')

Capabilities caps = ((RemoteWebDriver) DriverFactory.getWebDriver()).getCapabilities()


String plataform = caps.getPlatform();


WebUI.navigateToUrl(url)


if(!plataform.equalsIgnoreCase("ANDROID")){
	WebUI.maximizeWindow()
}else{
GlobalVariable.mobileBrowser = true;
}


WebUI.delay(2)

WebUI.waitForElementPresent(findTestObject('Repositorio Apuestas deportivas juegos/a_Ingresar'), 20)

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Apuestas deportivas juegos/a_Ingresar'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Repositorio Apuestas deportivas juegos/input_Bienvenido_user'), 20)

WebUI.sendKeys(findTestObject('Repositorio Apuestas deportivas juegos/input_Bienvenido_user'), loginUser)

WebUI.sendKeys(findTestObject('Repositorio Apuestas deportivas juegos/input_Bienvenido_password'),loginPassword)

WebUI.click(findTestObject('Repositorio Apuestas deportivas juegos/button_Entrar'))

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/li_PIN10075T'), 3)

WebUI.verifyElementPresent(findTestObject('Repositorio Sportbook/div_Logout'), 2)

if(!GlobalVariable.mobileBrowser){
   WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/div_Logout'))
}