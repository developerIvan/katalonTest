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
import com.utils.AutomationUtils

import internal.GlobalVariable as GlobalVariable

String userZeroBalanceDay="";

WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/Ir a CustomerMaintenance'), [('customerId') : userId], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Customer Maintenance/Config/a_Config'), 2)

WebUI.waitForPageLoad(4);

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/Config/a_Config'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Config/label_Zero Balance'), 4);

WebUI.verifyElementVisible(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Config/select_Zero_Out_Day'))

userZeroBalanceDay = CustomKeywords.'com.utils.AutomationUtils.getSelectedValueFromSelectCombobox'(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Config/select_Zero_Out_Day'))

WebUI.delay(3)

//WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/a_Personal'));

return userZeroBalanceDay
