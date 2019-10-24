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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

String testDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String testcaseId = 'C3797'

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

boolean tomarInstantanea = false

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

//Guarda url o dirrecion del sitio según el ambiente
testResultData.put(0, url)

//Registro fecha incio de la prueba
testResultData.put(3, testDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

try {
	WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/Check box Remember Credentials es visible C3786'),
			[('url') : url], FailureHandling.STOP_ON_FAILURE)

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()



	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/input_Welcome_chkRemember'))

	WebUI.sendKeys(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/input_Welcome Back_user'),customerPin)

	WebUI.sendKeys(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/input_Welcome Back_password'),password)

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/button_Enter'))

	WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/InitModal'), 4);
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/Page_Sportbook/span_customerPin'), 8)

	WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/div_Salir'), 4, FailureHandling.STOP_ON_FAILURE)
	
	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/div_Salir'));
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/a_Login'),4)

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/a_Login'));

	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/input_Welcome_chkRemember'), 2)

	WebUI.verifyElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/input_Welcome_chkRemember'), FailureHandling.STOP_ON_FAILURE)

	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/span_RememberCredentials'), 2)

	WebUI.verifyElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/span_RememberCredentials'), FailureHandling.STOP_ON_FAILURE)

	customerPin = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), "value");
	
	password = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_password'), "value");
	
	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/button_Enter'))
	
	WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/InitModal'), 4);
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/Page_Sportbook/span_customerPin'), 8)

	//valida que el usuario logró entrar al sitio
	assert WebUI.getUrl().toString().contains('sportbook')

	testStatus = 'Exitoso'

	testResultDescription = 'La función de recordar credenciales  funciona correctamente'
}catch(java.lang.AssertionError asserError){
	tomarInstantanea = true;
	KeywordUtil.logger.logError('Error code: -10 error message :' + asserError.getMessage())
	testResultDescription = 'La función de recordar credenciales o "remeber credentials" no funciona correctamente debido a que el ingreso al sitio de sportbook fue fallido  ';
	throw asserError;
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	tomarInstantanea = true

	KeywordUtil.logger.logError('Error code: -10 error message :' + stepE.getMessage())

	testResultDescription = 'La opción de "Remember Credentials" debería ser visible, pero actualmente no lo es. Lo cual indica que, la caja de selección o la descripción no aparecen, lo cual cuasa que la prueba automatizada no lo pueda encontrar'

	throw stepE
}
catch (Exception e) {
	tomarInstantanea = true

	KeywordUtil.logger.logError('Error code: -99 boton login :' + e.getMessage())

	KeywordUtil.logger.logError('Error code: -99, error message :' + e.getMessage())

	testResultDescription = 'La opción de "Remember Credentials" debería ser visible, pero actualmente no lo es debido a un comportanmiento anomalo, favor revisar el log de katalon'

	throw e
}
finally {
	if (tomarInstantanea == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}

	
	//sale de session para ir al sitiod e login y otras pruebas puedan usar el sitio
	if (tomarInstantanea == false && GlobalVariable.individualTestCase == false) {
			WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/div_Salir'));
	}
	
	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(5, testEndHour)
	
	//Guarda usuario de logeo
	testResultData.put(1, customerPin)

	//Guarda 
	testResultData.put(2, password)
	
	//Guarda Version del browser
	testResultData.put(7, browserVersion)
	
	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda Version del sistema operativo
	testResultData.put(8, screenResolution)

	//Guarda resultado de la prueba
	testResultData.put(9, testStatus)

	//Guarda descricion del resultado de la prueba
	testResultData.put(10, testResultDescription)

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
}

