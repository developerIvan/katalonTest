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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.io.FileInputStream as FileInputStream
import java.io.FileNotFoundException as FileNotFoundException
import java.io.IOException as IOException
import java.util.Date as Date
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook

KeywordLogger log = new KeywordLogger()

TestObject custMaintenancePassword = null

String actualPassword = ''

newPassword = findTestData('ChangePasswordTestCaseData').getValue(2, 1)

oldPassword = findTestData('ChangePasswordTestCaseData').getValue(1, 1)

pregameSite = findTestData('Perfiles/DatosDePruebaSoporte').getValue(1, 1);
WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.1 User/2.1.1.1 Usuario Correcto/Jugador logra ingresar a Overview (C6414)'), 
    [('url') : pregameSite, ('loginUser') : findTestData('ChangePasswordTestCaseData').getValue(
            3, 1), ('loginPassword') : findTestData('ChangePasswordTestCaseData').getValue(1, 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForPageLoad(4)

WebUI.waitForElementNotVisible(findTestObject('Repositorio Sportbook/Init_Modal'), 4)

WebUI.verifyElementClickable(findTestObject('Repositorio Sportbook/div_MY ACCOUNT'))

WebUI.click(findTestObject('Repositorio Sportbook/div_MY ACCOUNT'))

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/a_Change my Password'), 2)

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Sportbook/a_Change my Password'))

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/input__curPasswordEntry'), 4)

WebUI.sendKeys(findTestObject('Repositorio Sportbook/input__curPasswordEntry'), oldPassword)

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Repositorio Sportbook/input__passwordEntry'), newPassword)

WebUI.click(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_Save changes'))

WebUI.delay(2)

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/div_Attention'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/p_Success. The password change'), 2)

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Repositorio Sportbook/p_Success. The password change'), 'Success. The password change made.')

WebUI.click(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_Close'))

WebUI.navigateToUrl(findTestData('LobbyTestData').getValue(1, 1))

WebUI.waitForElementVisible(findTestObject('Repositorio Objectos Pagina Lobby/input_User_id_user'), 4)

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Lobby/input_User_id_user'), findTestData('LobbyTestData').getValue(
        2, 1))

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Lobby/input_Password_id_password'), findTestData('LobbyTestData').getValue(
        3, 1))

WebUI.click(findTestObject('Repositorio Objectos Pagina Lobby/input_Password_bt_login'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Repositorio Objectos Pagina Lobby/a_Customer Maintenance Pre-Pro'), 4)

WebUI.click(findTestObject('Repositorio Objectos Pagina Lobby/a_Customer Maintenance Pre-Pro'))

WebUI.waitForElementVisible(findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Save changes_globalcusto'), 
    2)

WebUI.delay(2)

WebUI.setText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Save changes_globalcusto'), findTestData(
        'ChangePasswordTestCaseData').getValue(3, 1))

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objectos Pagina Customer Maintenance/button_Save changes_btn_search'))

WebUI.delay(2)

WebUI.waitForElementAttributeValue(findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Password_pass'), 
    'value', findTestData('ChangePasswordTestCaseData').getValue(2, 1), 2)

custMaintenancePassword = findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Password_pass')

actualPassword = WebUI.getAttribute(custMaintenancePassword, 'value', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(actualPassword.toString(), newPassword)

WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.1 User/2.1.1.1 Usuario Correcto/Jugador logra ingresar a Overview (C6414)'), 
    [('url') : pregameSite, ('loginUser') : findTestData('ChangePasswordTestCaseData').getValue(
            3, 1), ('loginPassword') : actualPassword], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

