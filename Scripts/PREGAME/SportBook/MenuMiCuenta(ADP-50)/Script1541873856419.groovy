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
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import com.kms.katalon.core.testobject.ConditionType as ConditionType

// Creating a Workbook from an Excel file (.xls or .xlsx)
File excelFile = new File(excelLabelFileLoc)

FileInputStream fis = new FileInputStream(excelFile)

XSSFWorkbook workbook = new XSSFWorkbook(fis)

String languageKey = ''

String balanceDiarioDes = null

String reportesDes = null

String transaccionesDesc = null

String rollOverDesc = null

String validateRollOverFlag = null

WebUI.callTestCase(findTestCase('PREGAME/SportBook/Login_Successfull'), [('url') : findTestData('PreGameTestData').getValue(
            3, 1), ('loginUser') : findTestData('PreGameTestData').getValue(1, 1), ('loginPassword') : findTestData('PreGameTestData').getValue(
            2, 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForPageLoad(4)

WebUI.waitForElementNotVisible(findTestObject('Repositorio Sportbook/Init_Modal'), 4)
//#accountMobileHeder span.glyphicon.glyphicon-user
if (GlobalVariable.siteType == 'Premium') {
	TestObject myAccountObj = findTestObject('Repositorio Sportbook/div_MY ACCOUNT')
	if(GlobalVariable.mobileBrowser == true){
		myAccountObj.addProperty('css', ConditionType.EQUALS, '#accountMobileHeder span.glyphicon.glyphicon-user')
	}
	WebUI.verifyElementClickable(myAccountObj)
	
    WebUI.click(myAccountObj)
} else {
    TestObject reportObject = new TestObject('ReportMenu')

    reportObject.addProperty('css', ConditionType.EQUALS, '#sportsMenu .panel.panel-default.showAccount div.panel-heading.pannel-heading-1 div div.optionMenu')

    WebUI.click(reportObject)
}

String url = WebUI.getUrl()

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_BALANCE DIARIO'), 2)

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_REPORTES'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_TRANSACCIONES'))

WebUI.delay(2)

balanceDiarioDes = WebUI.getAttribute(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_BALANCE DIARIO'), 'textContent', 
    FailureHandling.STOP_ON_FAILURE)

reportesDes = WebUI.getAttribute(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_REPORTES'), 'textContent', 
    FailureHandling.STOP_ON_FAILURE)

transaccionesDesc = WebUI.getAttribute(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_TRANSACCIONES'), 'textContent', 
    FailureHandling.STOP_ON_FAILURE)

languageKey = url.replace(findTestData('PreGameTestData').getValue(3, 1), '')

languageKey = languageKey.substring(0, 3)

XSSFSheet sheet = workbook.getSheet(languageKey)

validateRollOverFlag = findTestData('PreGameTestData').getValue(5, 1)

assert reportesDes == sheet.getRow(1).getCell(0).toString()

assert balanceDiarioDes == sheet.getRow(1).getCell(1).toString()

assert transaccionesDesc == sheet.getRow(1).getCell(2).toString()

//Carga de elementos de traducci�n para las demas secciones de la pagina
GlobalVariable.transWagerAmountLoc = sheet.getRow(9).getCell(0).toString()

GlobalVariable.wagerStatusLabel = sheet.getRow(9).getCell(1).toString()

if (validateRollOverFlag == 'Si') {
    WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_ROLL OVER'))

    rollOverDesc = WebUI.getAttribute(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_ROLL OVER'), 'textContent', 
        FailureHandling.STOP_ON_FAILURE)

    assert rollOverDesc == sheet.getRow(1).getCell(3).toString()
}

