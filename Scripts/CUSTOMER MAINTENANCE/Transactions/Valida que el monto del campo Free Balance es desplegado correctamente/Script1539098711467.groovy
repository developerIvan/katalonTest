import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import dinamicSelectors.MySelectors as MySelectors
import internal.GlobalVariable as GlobalVariable
//This is to write to the log file
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

//import com.kms.katalon.core.testobject.CoditionType as ConditionType
KeywordLogger log = new KeywordLogger()

def info = WebUI.callTestCase(findTestCase('CUSTOMER MAINTENANCE/PREPARE DATA LOGIN CM/PrepareDataLoginCM'), [:], FailureHandling.STOP_ON_FAILURE)

def Url_Free_Balance = info.url

def UserId_Free_Balance = info.userId

def Password_Free_Balance = info.password

String value = '';

//import com.kms.katalon.core.testobject.CoditionType as ConditionType
WebUI.openBrowser(Url_Free_Balance )

WebUI.maximizeWindow();

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_User_id_user'), UserId_Free_Balance)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_Password_id_password'), Password_Free_Balance)

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/input_Password_bt_login'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'),4)

WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'),2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'))

WebUI.delay(2)

WebUI.setText(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Save changes_globalcusto'), 
    PlayerPin)

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/button_Save changes_btn_search'))

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Transactions'))

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_7cat'))

value = WebUI.getAttribute(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_7cat'), 'value')

if (value == '') {
    throw new Exception('Empty value found')
}

return value;

WebUI.delay(2)

WebUI.closeBrowser()

