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


def loginResult = ['errorMgs': '', 'userId':'', 'password': '']

WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_pin_or_email'), 2)

	WebUI.setText(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_pin_or_email'), userPin)

	WebUI.setText(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_password'), userPass)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/button_Enter'))

	String actualErrorMessage = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/Div_LoginError'), 'innerText')
	
	loginResult.errorMgs = actualErrorMessage;
	
	loginResult.userId = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_pin_or_email'), "value");
	
	loginResult.password = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_password'), "value");
	
	return loginResult;
