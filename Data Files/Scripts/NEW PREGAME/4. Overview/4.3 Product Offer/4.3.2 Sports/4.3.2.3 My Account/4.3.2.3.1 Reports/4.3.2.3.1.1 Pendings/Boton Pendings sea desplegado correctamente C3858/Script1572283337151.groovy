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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C3858'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String testFailedMessageDesc = "Caso de prueba "+testcaseId+ " Fallido.";

try{
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/boton Reportes muestre el boton pending seleccionado de forma predeterminada C3861'),
			[('url') : url, ('customerPIN') : customerLogin, ('customerPass') : customerPass],
			FailureHandling.STOP_ON_FAILURE)

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.3 MY ACCOUNT/4.3.2.3.1 Reports/button_Pendings'), 2)

	WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.3 MY ACCOUNT/4.3.2.3.1 Reports/button_Pendings'))


	testStatus = 'Exitoso'

	testResultDescription = 'El boton de pending se ve correctamente';
}catch (StepFailedException stepE) {
	String errorCode = testcaseId.concat('-09 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)


	testResultDescription = 'El botón de apuestas pendiente no es  visible. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode);

	 throw new StepFailedException(errorCode.concat(testFailedMessageDesc),stepE);
}/*catch (Exception e) {
	String errorCode = testcaseId.concat('-99 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = 'El botón de apuestas pendientes no es visible por algún comportamiento atipico. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode);

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),e);
}*/
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, customerLogin)

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2, customerPass)

	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(5, testEndHour)

	//Guarda Resultado de la prueba
	testResultData.put(9, testStatus)

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10, testResultDescription)

	//Guarda resultado de prueba
	if(GlobalVariable.QaIndividualLog){
	 //    CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}
	
	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}




