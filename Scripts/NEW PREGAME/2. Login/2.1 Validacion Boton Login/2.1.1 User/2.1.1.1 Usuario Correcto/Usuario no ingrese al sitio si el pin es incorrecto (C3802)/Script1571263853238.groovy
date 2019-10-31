import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import java.util.Calendar as Calendar
import java.util.Date as Date
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3802'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String expectedErrorMesage = GlobalVariable.mensajeUsuarioInvalido != null ? GlobalVariable.mensajeUsuarioInvalido : ''

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String pinInvalido = invalidPin


//Registro fecha incio de la prueba
testResultData.put(3,testStartDate);

//Registro  hora  incio de la prueba
testResultData.put(4,testStartHour);

WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/Boton login despliega el formulario C3787'),
		[:], FailureHandling.STOP_ON_FAILURE)

WebUI.maximizeWindow()

OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

//Guarda Version del browser
testResultData.put(7,browserVersion);

//Guarda Version del sistema operativo
testResultData.put(6,OsName);

//Guarda resolucion de pantalla
testResultData.put(8,screenResolution);

try {

	def loginResult = WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/ScriptAuxiliares/CargarMensajdeDeErrordeIngreso'),
			[('userPin') : pinInvalido, ('userPass') : loginPassword], FailureHandling.STOP_ON_FAILURE)

	loginUser = loginResult.userId

	loginPassword = loginResult.password

	actualErrorMessage = loginResult.errorMgs

	assert expectedErrorMesage.equalsIgnoreCase(actualErrorMessage)

	testStatus = 'Exitoso';

	testResultDescription = "El sistema no permitio que el usuario ingresará al sitio sin definir el pin de forma exitosa. El esperado mensaje de error '"+expectedErrorMesage+"' es desplegado existosamente";
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = '-01'

	errorEnLaPrueba = true

     KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)
	testResultDescription = 'El sistema no pudo validar que el usuario no pueda entrar al sitio si su pin no es ingresado  debido a algún elemento de la pagina que no esta presente o fue modificado '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode);

	throw stepE;
}
catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)
	testResultDescription ='El mensaje de error esperado debería ser ' + expectedErrorMesage;

	throw  asserError
}
catch (Exception e) {
	String errorCode = '-99'

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)
	testResultDescription ='El sistema no pudo validar que el usuario no pueda rentrar al sitio si su pin no es ingresado  debido a un error anomalo en la prueba. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode);

	errorEnLaPrueba = true

	throw e;
}
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0,url);

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1,loginUser);

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2,loginPassword);

	//Guarda hora final
	testEndHour =  CustomKeywords.'com.utils.ReportHelper.getHours'();
	testResultData.put(5,testEndHour);

	//Guarda Resultado de la prueba
	testResultData.put(9,testStatus);

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10,testResultDescription);

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if(errorEnLaPrueba == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation,testcaseId)
	}
	
	if(!errorEnLaPrueba){  //Cierra el navegador si la prueba se ejecuto de forma individual
		if (GlobalVariable.individualTestCase == true) {
			WebUI.closeBrowser()
		}else{
			WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/button_closeLoginPage'),2)
			WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/button_closeLoginPage'));
		}
	}

}

