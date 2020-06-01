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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.lang.String as String
import java.lang.AssertionError as AssertionError
import java.lang.Exception as Exception
String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C6387'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String pregameUserPin = "";

String failedTestCaseMessage = "Caso de prueba "+testcaseId+ " Fallido";

String pregameDisponible = ''

String cmDisponible = ''
try{
	 //Si el usuario no ha ingresado al sitio llamará a la función de login
		if (!(GlobalVariable.usuarioLogeado)) {
			WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'),
				[('url') : url, ('loginUser') : userPIN, ('loginPassword') : userPassword], FailureHandling.STOP_ON_FAILURE)

			GlobalVariable.usuarioLogeado = true
		}

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()
	
	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()
	
	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()
	
	 WebUI.waitForPageLoad(6)

	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/li_Available_Amount'), 2)
	
	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/li_Available_Amount'))

	
	pregameDisponible = WebUI.getText(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/li_Available_Amount'))


	if (pregameDisponible.contains('Available')) {
		pregameDisponible = pregameDisponible.replace('Available:', '')
	} else {
		pregameDisponible = pregameDisponible.replace('Disponible:', '')
	}

	if (pregameDisponible == '') {
		pregameDisponible = pregameDisponible.replace('', '0')
	}

	pregameDisponible = pregameDisponible.trim().replace(',', '')


	cmDisponible = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.1.3 Available/4.3.2.1.3.2 Validacion Available Vs CM Transactions/Consultar Disponible de CM'),
				   [('PlayerPin'): userPIN], FailureHandling.STOP_ON_FAILURE)
	
	cmDisponible=  cmDisponible!=null&&!cmDisponible.isEmpty()?cmDisponible.trim():'';
	
	assert cmDisponible.equals(pregameDisponible);

	//Result passed
	testStatus = "Exitoso"

	//Descripcion Exito en test case de validar Disponible
	testResultDescription = "El valor disponible del jugador "+userPIN+" de pregame corresponde con el Disponible de Customer Maintenance"
}catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = "Se esperaba que el monto del disponible del jugador "+userPIN + "  fuera: "+cmDisponible +" pero actualmente es: "+pregameDisponible;

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
	//   CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}
	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'();
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
	
}
