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
import java.lang.AssertionError as AssertionError
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C6391'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String testEndHour = '';

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String pregameUserPin = "";

String failedTestCaseMessage = "Caso de prueba "+testcaseId+ " Fallido";

String pregamePendiente  = ''

String cmPendiente = ''
try{
		if (!(GlobalVariable.usuarioLogeado)) {
			WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'),
				[('url') : url, ('loginUser') : userPIN, ('loginPassword') : userPassword], FailureHandling.STOP_ON_FAILURE)

			GlobalVariable.usuarioLogeado = true
		}
		
	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()
	
	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()
	
	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()
	
	WebUI.waitForElementNotPresent(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.1 Validacion Deportes En Menu Vs CM Restricciones/4.3.1.1 Validacion de acceso a pregame/InitModal'), 6);

    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/a_ApuestasPendientes'), 2)
		
	pregamePendiente = WebUI.getText(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/a_ApuestasPendientes'))

	if (pregamePendiente.contains('PENDING')) {
		pregamePendiente = pregamePendiente.replace('PENDING:', '')
	} else {
		pregamePendiente = pregamePendiente.replace('PENDIENTES:', '')
	}

	if (pregamePendiente == '') {
		pregamePendiente = pregamePendiente.replace('', '0')
	}

	pregamePendiente = pregamePendiente.trim()

	cmPendiente = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.1.4 Pending/4.3.2.1.4.2 Validacion Pending Vs CM Transactions/Consultar Pending de CM'),
			[('PlayerPin'): userPIN], FailureHandling.STOP_ON_FAILURE)

	cmPendiente =  cmPendiente!=null&&!cmPendiente.isEmpty()?cmPendiente.trim():'';

	assert cmPendiente.equals(pregamePendiente);
	
	testStatus = 'Exitoso'
	
	testResultDescription = "El monto pendiente del jugador "+userPIN+" de pregame corresponde con el pendiente de Customer Maintenance"

}catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = "Se esperaba que el monto  pendiente del jugador "+userPIN + "  fuera: "+cmPendiente +" pero actualmente es: "+pregamePendiente;

	throw new StepFailedException(errorCode.concat(failedTestCaseMessage),asserError);
}
finally {
	GlobalVariable.usuarioLogeado = false;
	GlobalVariable.pregameSiteEsVisible = false
	
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, userPIN)

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2, userPassword)

	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(5, testEndHour)

	//Guarda Version del SO
	testResultData.put(6,OsName);
	
	//Guarda Version del browser
	testResultData.put(7,browserVersion);

	//Guarda Version del sistema operativo
	testResultData.put(8,screenResolution);
	
	//Guarda Resultado de la prueba
	testResultData.put(9, testStatus)

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10, testResultDescription)

	//Guarda resultado de prueba
	if(GlobalVariable.QaIndividualLog){
	  // CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}
	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
	
		CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'();
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
	
	if(GlobalVariable.individualTestCase){
		WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)	
	}
}
