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
//import newpackage.newKeyword
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.message_return as message_return
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.value_return as value_return
import org.openqa.selenium.Keys as Keys
import org.stringtemplate.v4.compiler.STParser.element_return as element_return
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.annotation.Keyword as Keyword
//This section is needed to call the Selenium WebDriver
import org.openqa.selenium.WebDriver as WebDriver
//This allows for the use of MarkFailed and MarkError
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
//This is for SendKeys
//This is to write to the log file
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.util.Calendar as Calendar
import java.util.Date as Date
import java.text.SimpleDateFormat as SimpleDateFormat
import java.io.FileInputStream as FileInputStream
import java.io.FileNotFoundException as FileNotFoundException
import java.io.IOException as IOException
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.lang.String as String
import bminc.eu.exceptions.DisponibleException as DisponibleException
import java.lang.AssertionError as AssertionError

KeywordLogger log = new KeywordLogger()

String OsName = CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'()

SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY")

SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss")

Calendar cal = Calendar.getInstance()

Date DateTimeToday = cal.getTime()

def info = WebUI.callTestCase(findTestCase('NEW PREGAME/PREPARE DATA LOGIN PREGAME/PrepareDataLoginPreGameAvailable'),
	[:], FailureHandling.STOP_ON_FAILURE)

def Url_Available = info.url

def UserId_Available = info.userId

def Password_Available = info.password

//import com.kms.katalon.core.testobject.CoditionType as ConditionType
WebUI.openBrowser(Url_Available)

CharSequence BrowserNameVersion = CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'()

String ScreenResolution = CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'()


WebUI.maximizeWindow();
def String rutaExcel = 'C:/PROYECTOS/QAPregameNuevo/Data Files/TestData/DataLoginPregameAvailable.xlsx';


//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(rutaExcel)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'(0)

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 3, CustomKeywords.'com.utils.DateUtil.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.DateUtil.getHours'())

//Guarda Version del browser
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())

//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7,OsName)

//Guarda resolucion de la ventana
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, ScreenResolution)

String pregameDisponible = ''

String cmDisponible = ''
try{
	WebDriver driver = DriverFactory.getWebDriver()

	//WebDriver driver = new FirefoxDriver()
	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/a_Login'))

	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), 2)

	WebUI.setText(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), UserId_Available)

	WebUI.delay(2)

	WebUI.setText(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_password'), Password_Available)

	WebUI.delay(2)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/button_Enter'))

	WebUI.waitForPageLoad(5)

	switch (WebUI.verifyElementNotPresent(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/a_Enter'),
		10, FailureHandling.CONTINUE_ON_FAILURE)) {
		case 'true':
			break
		case 'false':
			WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/a_Enter'))

			break
	}

	WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Proyecto Premium/div_MY ACCOUNT'), 4)
	
	WebUI.delay(2)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/div_MY ACCOUNT'))

	WebUI.delay(2)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/li_Disponible 641.28'))

	WebUI.delay(2)

	//'Get text of span_641.28'
	pregameDisponible = WebUI.getText(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/li_Disponible 641.28'))

	//WebUI.delay(2)

	if (pregameDisponible.contains('Available')) {
		pregameDisponible = pregameDisponible.replace('Available:', '')
	} else {
		pregameDisponible = pregameDisponible.replace('Disponible:', '')
	}

	if (pregameDisponible == '') {
		pregameDisponible = pregameDisponible.replace('', '0')
	}

	pregameDisponible = pregameDisponible.trim().replace(',', '')

	WebUI.delay(2)

	cmDisponible = WebUI.callTestCase(findTestCase('CUSTOMER MAINTENANCE/Transactions/Valida que el monto del campo Available es desplegado correctamente'),
				   [('PlayerPin'): UserId_Available], FailureHandling.STOP_ON_FAILURE)
	
	cmDisponible=  cmDisponible!=null&&!cmDisponible.isEmpty()?cmDisponible.trim():'';
	
	assert cmDisponible.equals(pregameDisponible);
		
	//WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/div_Balance -358.72'))
	WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)


	//Result passed
		CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Exitoso")

	//Descripcion Exito en test case de validar Disponible
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El valor del campo Disponible de pregame Si es igual al valor del campo Disponible de Customer Maintenance")
}catch(DisponibleException dis){
	
	//Result fallido
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")

	//Descripcion error en test case de validar balance
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El valor del Disponible de pregame no es igual al valor Disponible de Customer Maintenance")
	throw new AssertionError('Error en la prueba por excepcion en el Disponible',dis);
	
}catch(com.kms.katalon.core.exception.StepFailedException stepFailedEx){
	//Result fallido
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")

	//Descripcion error en test case de validar balance
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "Error debido a que el valor disponible de pregame es diferente al valor disponible en Customer Maintenace")
	throw new AssertionError('Error en la prueba',stepFailedEx);

}catch(Exception e){

	//Result fallido
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")

	//Descripcion error en test case de validar balance
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "Error inesperado, revisar en log o bitacoras")
	throw new AssertionError('Error en la prueba',e);
}
finally{
//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.DateUtil.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 5, CustomKeywords.'com.utils.DateUtil.getDate'())

	
	//Cierra archivo de lectura para permitir la escritura
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
	
	//Abre  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.loadFileOutputStream'(rutaExcel)

	
	//escribe informacion en la hoja del exec
	CustomKeywords.'com.utils.ExcelsUtils.writeOutputExcelSheet'()

	//Cierra  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
}

