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


String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3860'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

try {
	//login pregame
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.1 Validacion Deportes En Menu Vs CM Restricciones/4.3.1.1 Validacion de acceso a pregame/Usuario tiene permiso Sportbook(C3900)'),
			[('url') : url, ('userName') : customerPIN, ('userPassword') : customerPass],
			FailureHandling.STOP_ON_FAILURE)

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7,browserVersion);

	//Guarda Version del sistema operativo
	testResultData.put(6,OsName);

	//Guarda resolucion de pantalla
	testResultData.put(8,screenResolution);

	WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/div_MY ACCOUNT'), 4)

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/div_MY ACCOUNT'))


	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_REPORTS'), 2)

	String reportButtonName = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_REPORTS'), "innerText", FailureHandling.STOP_ON_FAILURE)


	assert null != reportButtonName;

	testStatus = 'Exitoso';

	testResultDescription = 'El botón de reportes de la sección "My Account" es visible';

}catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = '-09'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError('Error code: ' + errorCode + ' error message :' + stepE.getMessage())

	testResultDescription =  'El botón de reportes de la sección "My Account"  no es visiblea debido a que un paso de la prueba no se completo o un elemento de la página que no está visible. Favor revisar el log de katalon';

	throw new com.kms.katalon.core.exception.StepFailedException('Paso de la prueba  no completado', stepE)
}
catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError('Error code: ' + errorCode+ ' error message :' + asserError.getMessage())

	testResultDescription = 'El botón de reportes de la sección "My Account no es visible, revisar las tomas instantaneas';

	throw new AssertionError('Botón reporte no es visible', asserError, errorCode)
}
catch (Exception e) {
	String errorCode = '-99'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError('Error code: ' + errorCode + ' error message :' + e.getMessage())

	testResultDescription ='El botón de reportes de la sección "My Account no es visible debido a un error anomalo en la prueba. Favor revisar los log o bitacoras de katalon';

	throw e;
}
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0,url);

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1,customerPIN);

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2,customerPass);

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
}

