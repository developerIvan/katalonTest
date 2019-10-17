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
import internal.GlobalVariable as GlobalVariable
import bminc.eu.exceptions.DisponibleException
//This is to write to the log file
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

//import com.kms.katalon.core.testobject.CoditionType as ConditionType
KeywordLogger log = new KeywordLogger()



def Url_Available = GlobalVariable.lobbyUrl

def UserId_Available = GlobalVariable.lobbyUser

def Password_Available = GlobalVariable.lobbyPassword

try{
//import com.kms.katalon.core.testobject.CoditionType as ConditionType
WebUI.openBrowser(Url_Available )

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_User_id_user'), UserId_Available)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_Password_id_password'), Password_Available) 

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

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_weekly'))

String disponibleValue = WebUI.getAttribute(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_weekly'), 
	'value')

if (disponibleValue== '') {
    throw new DisponibleException('El Disponible del Customer Maintenance no se cargo de forma correcta ','-006')
}

return disponibleValue

WebUI.delay(2)

//WebUI.closeBrowser()

}catch(Exception e){
//e.printStackTrace();
 throw new DisponibleException(e.getMessage(),e,'-005');

}
