import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import bminc.eu.exceptions.PendingException
//import com.kms.katalon.core.testobject.CoditionType as ConditionType
KeywordLogger log = new KeywordLogger()


def Url_Pending = GlobalVariable.lobbyUrl

def UserId_Pending = GlobalVariable.lobbyUser

def Password_Pending = GlobalVariable.lobbyPassword

try{
//import com.kms.katalon.core.testobject.CoditionType as ConditionType
WebUI.openBrowser(Url_Pending)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_User_id_user'), UserId_Pending)

WebUI.setText(findTestObject('Repositorio Objetos Customer Maintenance/input_Password_id_password'), Password_Pending)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Password_bt_login'))

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Customer Maintenance'))

WebUI.delay(2)

WebUI.setText(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/input_Save changes_globalcusto'), 
    PlayerPin)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/button_Save changes_btn_search'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/a_Transactions'))

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_maxparlayb'))

String pendingValue = WebUI.getAttribute(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_maxparlayb'), 
	'value')

if (pendingValue== '') {
    throw new PendingException('El  Pending del customer no se cargo de forma correcta ','-006')
}



return pendingValue

}catch(Exception e){
 //e.printStackTrace();
  throw new PendingException(e.getMessage(),e,'-005');
 
}
