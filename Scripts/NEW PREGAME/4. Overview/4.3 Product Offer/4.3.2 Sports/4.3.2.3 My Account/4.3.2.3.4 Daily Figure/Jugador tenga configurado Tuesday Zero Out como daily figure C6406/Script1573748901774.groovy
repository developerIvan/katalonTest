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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C6408'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(2, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(3, testStartHour)

String tuesdayZeroOutBalance = ''

try {
	//Va a customer maintenace para consultar zero balance
	 tuesdayZeroOutBalance = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Precondiciones/Validar Jugador es de tipo zero balance'),
		[('userId') : customerPIN], FailureHandling.STOP_ON_FAILURE)
	
	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(6, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(5, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(7, screenResolution)

	//Valida que el jugador tenga la configuracion de monday zero out
	assert  expectedTuesdayConfiguration.equals(tuesdayZeroOutBalance)

	testStatus = 'Exitoso'

	testResultDescription = (('El jugador "' + customerPIN) + '" tiene la configración correcta de '+expectedTuesdayConfiguration)
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = testcaseId+'-09'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = ('No se pudo valida que el jugador '+customerPIN+' tenga la configuración de Zero balance debido a que no tiene dicha configuración o algun paso de la prueba no se completo o un elemento de la página que no está visible. ' +
	CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode))

	throw stepE
}
catch (AssertionError asserError) {
	String errorCode = testcaseId+'-10'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'El jugador '+customerPIN+ ' debería tener la configuración de zero balance '+expectedTuesdayConfiguration + ' pero tiene la siguiente configuración: ' +tuesdayZeroOutBalance + ' en su lugar '

	throw asserError
}
catch (Exception e) {
	String errorCode = testcaseId+'-99'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = ('No se pudó validar si el jugador '+customerPIN+' debido a un error anomalo en la prueba. ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
		errorCode))

	throw e
}
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, GlobalVariable.lobbyUrl)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, customerPIN)

	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(4, testEndHour)

	//Guarda Resultado de la prueba
	testResultData.put(8, testStatus)

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(9, testResultDescription)

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}