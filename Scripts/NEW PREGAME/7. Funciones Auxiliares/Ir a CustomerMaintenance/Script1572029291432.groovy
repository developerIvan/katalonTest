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

WebUI.openBrowser(GlobalVariable.lobbyUrl)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_User_id_user'),GlobalVariable.lobbyUser )

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_Password_id_password'), GlobalVariable.lobbyPassword)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Password_bt_login'))

WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'), 3)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'))

WebUI.setText(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Save changes_globalcusto'),
	GlobalVariable.customerPIN)

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/button_Save changes_btn_search'))

WebUI.delay(2)