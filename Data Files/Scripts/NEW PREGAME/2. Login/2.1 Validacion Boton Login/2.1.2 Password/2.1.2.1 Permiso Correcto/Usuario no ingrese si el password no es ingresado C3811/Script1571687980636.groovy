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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
String testEndHour = '';

String browserVersion = '';

String screenResolution = '';

String OsName = '';

String testStatus = 'Fallido';

String testResultDescription = '';

ArrayList<Integer> rows = new ArrayList<Integer>(1);
rows.add(1);

HashMap<Integer,String> testResultData = new  HashMap(11);

String testcaseId = "C3811";

String actualErrorMessage = '';

//Indica si tomar un snapshot o instantanea en caso de error
boolean errorEnLaPrueba = false;
String expectedErrorMesage = GlobalVariable.mensajeUsuarioInvalido!=null?GlobalVariable.mensajeUsuarioInvalido:"";

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'();

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'();

//Registro fecha incio de la prueba
testResultData.put(3,testStartDate);

//Registro  hora  incio de la prueba
testResultData.put(4,testStartHour);

String testFailedMessageDesc = "Caso de prueba "+testcaseId+ " Fallido.";

try {
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

	def loginResult = WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/ScriptAuxiliares/CargarMensajdeDeErrordeIngreso'),
			[('userPin') : loginUser, ('userPass') : loginInvalidPassword], FailureHandling.STOP_ON_FAILURE)

	loginUser = loginResult.userId;

	loginPassword = loginResult.password;

	actualErrorMessage =loginResult.errorMgs;

	assert expectedErrorMesage.equalsIgnoreCase(actualErrorMessage)

	testStatus = 'Exitoso';

	testResultDescription = "El sistema no permitio que el usuario ingresará al sitio si la clave no es ingresada de forma exitosa. El esperado mensaje de error '"+expectedErrorMesage+"' es desplegado existosamente";

}catch(java.lang.AssertionError asserError){
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true;

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription= 'El mensaje de error esperado debería ser '+expectedErrorMesage+" pero actualmente es: "+actualErrorMessage

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),asserError);
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
	if(GlobalVariable.QaIndividualLog){
	//	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}

	//toma screenshot en caso de error
	if(errorEnLaPrueba == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation,testcaseId)
	}else{
		//Cierra el navegador si la prueba se ejecuto de forma individual
		if (GlobalVariable.individualTestCase == true) {
			WebUI.closeBrowser()
		}else{
			WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/2.1.1 User/2.1.1.1 Usuario Correcto/button_closeLoginPage'),2)
			WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/2.1.1 User/2.1.1.1 Usuario Correcto/button_closeLoginPage'));
		}
	}

}