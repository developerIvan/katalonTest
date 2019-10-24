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
import bminc.eu.exceptions.LoginException as LoginException
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3840'

String actualErrorMessage = ''

//Indica si tomar un snapshot o instantanea en caso de error
boolean errorEnLaPrueba = false

String expectedErrorMesage = GlobalVariable.mensajeUsuarioInvalido != null ? GlobalVariable.mensajeUsuarioInvalido : ''

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/Boton login despliega el formulario C3787'),
		[:], FailureHandling.STOP_ON_FAILURE)

WebUI.maximizeWindow()

OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

//Guarda Version del browser
testResultData.put(7, browserVersion)

//Guarda Version del sistema operativo
testResultData.put(6, OsName)

//Guarda resolucion de pantalla
testResultData.put(8, screenResolution)

try {

	def loginResult =	WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/ScriptAuxiliares/LoginIvalidoConEnter'), [('browser') :browserVersion, ('loginUser') : loginUser
		, ('loginPassword') : loginInvalidPassword], FailureHandling.STOP_ON_FAILURE)

	loginUser = loginResult.userId

	loginPassword = loginResult.password

	actualErrorMessage = loginResult.errorMgs

	assert expectedErrorMesage.equalsIgnoreCase(actualErrorMessage)

	testStatus = 'Exitoso'

	testResultDescription = ('El sistema no permitio que el usuario ingresará al sitio con la tecla Enter si la clave es incorrecta de forma exitosa. El esperado mensaje de error \'' +
			expectedErrorMesage + '\' es desplegado existosamente')


}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = '-01'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + stepE.getMessage())

	testResultDescription = 'El sistema no pudo validar que el usuario no pueda rentrar al sitio con la tecla Enter si su contraseña es erronea  debido a que el mesaje de error no es el esperado o algún  elmento esperado  de la página no está visible. Favor revisar el log de katalon'

	throw new LoginException('Error al ejecutar la prueba por un paso no completado', stepE, errorCode)
}
catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + asserError.getMessage())

	testResultDescription = ((('El mensaje de error esperado debería ser ' + expectedErrorMesage) + ' pero actualmente es: ') +
			actualErrorMessage)

	throw new LoginException('Validación de clave incorrecta  fallida', asserError, errorCode)
}
catch (Exception e) {
	String errorCode = '-99'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + e.getMessage())

	testResultDescription = 'El sistema no pudo validar que el usuario no pueda entrar con la tecla Enter al sitio si su contraseña está erronea debido a un error anomalo en la prueba. Favor revisar los logs o bitacoras de katalon'

	throw new LoginException('Login Test Case fallido', e, errorCode)
}
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, loginUser)

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2, loginPassword)

	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(5, testEndHour)

	//Guarda Resultado de la prueba
	testResultData.put(9, testStatus)

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10, testResultDescription)

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}else{

		if (GlobalVariable.individualTestCase == true) {
			WebUI.closeBrowser()
			//Cierra el navegador si la prueba se ejecuto de forma individual
		} else {
			//Cierra el formulario de login para las demas pruebas dentro del test suite
			WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/button_closeLoginPage'),
					2)

			WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/button_closeLoginPage'))
		}

	}
}

