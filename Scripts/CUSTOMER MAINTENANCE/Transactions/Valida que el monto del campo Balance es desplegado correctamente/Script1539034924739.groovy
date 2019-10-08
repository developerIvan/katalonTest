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
import bminc.eu.exceptions.BalanceException
//This is to write to the log file
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

//import com.kms.katalon.core.testobject.CoditionType as ConditionType
KeywordLogger log = new KeywordLogger()

def info = WebUI.callTestCase(findTestCase('CUSTOMER MAINTENANCE/PREPARE DATA LOGIN CM/PrepareDataLoginCM'), [:], FailureHandling.STOP_ON_FAILURE)

def Url_Balance = info.url

def UserId_Balance = info.userId

def Password_Balance = info.password
try{
//import com.kms.katalon.core.testobject.CoditionType as ConditionType
WebUI.openBrowser(Url_Balance)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_User_id_user'), UserId_Balance)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_Password_id_password'), Password_Balance)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Password_bt_login'))

WebUI.waitForPageLoad(4)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'))

WebUI.delay(2)

WebUI.setText(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Save changes_globalcusto'), 
    PlayerPin)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/button_Save changes_btn_search'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/a_Transactions'))

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/span_-1.00'))

String balanceValue = WebUI.getText(findTestObject('Repositorio Objetos Customer Maintenance/span_-1.00'))

if (balanceValue == '') {
    throw new BalanceException('El Balance del Customer Maintenance no se cargo de forma correcta ','-006')
}

return balanceValue

WebUI.delay(2)

//WebUI.closeBrowser()

}catch(Exception e){
//e.printStackTrace();
 throw new BalanceException(e.getMessage(),e,'-005');

}

