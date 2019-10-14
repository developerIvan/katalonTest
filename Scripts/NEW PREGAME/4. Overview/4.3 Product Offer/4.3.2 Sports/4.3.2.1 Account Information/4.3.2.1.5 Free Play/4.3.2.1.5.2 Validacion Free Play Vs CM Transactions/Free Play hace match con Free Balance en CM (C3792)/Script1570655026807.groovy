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
import com.google.common.cache.CacheBuilderSpec.IntegerParser
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
import bminc.eu.exceptions.FreePlayException as FreePlayException
import java.lang.AssertionError as AssertionError

KeywordLogger log = new KeywordLogger()

String OsName = CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'()

SimpleDateFormat format = new SimpleDateFormat('dd/MM/YYYY')

SimpleDateFormat timeFormat = new SimpleDateFormat('H:mm:ss')

Calendar cal = Calendar.getInstance()

Date DateTimeToday = cal.getTime()

def Url_FreePlay = GlobalVariable.pregameUrl

def UserId_FreePlay = GlobalVariable.customerPIN

def Password_FreePlay = GlobalVariable.customerPassword

WebUI.openBrowser(Url_FreePlay)

CharSequence BrowserNameVersion = CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'()

String ScreenResolution = CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'()


WebUI.maximizeWindow();

def String rutaExcel = 'C:/PROYECTOS/QAPregameNuevo/Data Files/TestData/Free Play hace match con Free Balance en CM (C3792).xlsx';

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



String pregameFreeBalance = ''

String cmFreBalance = ''
try {
	WebDriver driver = DriverFactory.getWebDriver()

	//WebDriver driver = new FirefoxDriver()
	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/a_Login'), 4)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/a_Login'))

	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), 2)

	WebUI.setText(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), UserId_FreePlay)

	WebUI.delay(2)

	WebUI.setText(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_password'), Password_FreePlay)

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

	WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/InitModal'), 4);

	WebUI.waitForElementNotPresent(findTestObject('Repositorio Objetos Proyecto Premium/InitModal'), 4);

	WebUI.delay(2)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/li_Jugada Gratis0'))

	WebUI.delay(2)

	//'Get text of Free play'

	CharSequence pregameFreeBalanceAtt = WebUI.getText(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/li_Jugada Gratis0'))


	WebUI.delay(2)

	if (pregameFreeBalanceAtt.contains('Free Play')) {
		pregameFreeBalance = pregameFreeBalanceAtt.toString().replace('Free play:', '')
	} else {
		pregameFreeBalance = pregameFreeBalanceAtt.toString().replace('Jugada Gratis:', '')
	}

	if (pregameFreeBalance == '') {
		pregameFreeBalance = pregameFreeBalance.replace('', '0')
	}

	pregameFreeBalance = pregameFreeBalance.trim()

	WebUI.delay(2)

	cmFreBalance = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.1.5 Free Play/4.3.2.1.5.2 Validacion Free Play Vs CM Transactions/Campo Free Balance es desplegado correctamente(C3791)'),
			       [('PlayerPin'): UserId_FreePlay], FailureHandling.STOP_ON_FAILURE)

	cmFreBalance =  cmFreBalance!=null&&!cmFreBalance.isEmpty()?cmFreBalance.trim():'';

	assert cmFreBalance.equals(pregameFreeBalance);
	
	//WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/div_Balance -358.72'))
	WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)

	//Result passed
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Exitoso")

	
	//Descripcion Exito en test case de validar Disponible
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El valor del campo Free Play de Pregame Si es igual al valor del campo Free Balance de Customer Maintenance")
}
catch (FreePlayException free) {

	//Result fallido
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")

	//Descripcion error en test case de validar balance
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, 'El valor del campo Free Play de pregame no es igual al del campo Free Balance en Customer Maintenance')

	throw new AssertionError('Error en la prueba por excepcion en el FreeBalance', free)
}
catch (com.kms.katalon.core.exception.StepFailedException stepFailedEx) {
	//Result fallido
     CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")


	//Descripcion error en test case de validar balance
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, 'El valor del campo Free Play de pregame no es igual al del campo Free Balance en Customer Maintenance')

	throw new AssertionError('Error en la prueba', stepFailedEx)
}
catch (Exception e) {

	//Result fallido
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")

	//Descripcion error en test case de validar balance
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, 'Error inesperado, revisar en log o bitacoras')

	throw new AssertionError('Error en la prueba', e)
}
finally {

	//Guarda url o dirrecion del sitio según el ambiente
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,0,Url_FreePlay);

	//Guarda pin del jugador que se usó para la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,1,UserId_FreePlay);

	//Guarda password del jugador que se usó para la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,2,Password_FreePlay);
	
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



