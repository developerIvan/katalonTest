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
import java.util.Map as Map
import java.util.HashMap as HashMap
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.util.List as List
import java.util.ArrayList as ArrayList
import com.utils.DailyFigureContainer as DailyFigureContainer
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

String testcaseId = 'C3878'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

Double expectedDailyFigureDayAmount = 0.00;

Double actualDailyFigureDayAmount = 0.00;

DailyFigureContainer dailyFigureContainer = GlobalVariable.dailyFigureContainer;

String testFailedMessageDesc = "Caso de prueba "+testcaseId+ " Fallido.";

String dayOfWeek = ''
try {
	//Registro fecha incio de la prueba
	testResultData.put(3, testStartDate)

	//Registro  hora  incio de la prueba
	testResultData.put(4, testStartHour)

	//Valida precondición
//if(dailyFigureContainer == null || GlobalVariable.precondicionC6400Completa ==  false){
		dailyFigureContainer =	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Daily figure despliegue los rubros de un dia en especifico C6400'),
				[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass
					, ('dailyFigureDay') : dailyFigureDay, ('dailyFigureWeekNumber') : dailyFigureWeekNumber], FailureHandling.STOP_ON_FAILURE)
	//}
	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()


	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	dayOfWeek = dailyFigureContainer.getDailyFgureDay();

	TestObject dayTdAmountData = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Daiy Amonunt', 'css', ('tr.trReportDetail.show-data td[data-th="' +
			dayOfWeek) + '"] a', 3)

	String expectedDailyFigureDayTotalAmStrg = WebUI.getAttribute(dayTdAmountData, 'innerText')

	expectedDailyFigureDayAmount = Double.parseDouble(expectedDailyFigureDayTotalAmStrg);

	assert String.format("%,f.##",expectedDailyFigureDayAmount).equals(String.format("%,f.##", dailyFigureContainer.getDailyFigureDayTotalAmount()))

	testStatus = 'Exitoso'

	testResultDescription = 'El monto sumado  de los rubros del día '+dailyFigureDay+', el cual es:  '+expectedDailyFigureDayAmount+' es igual al monto que refleja dicho día el cual es: '+dailyFigureContainer.getDailyFigureDayTotalAmount()
	dailyFigureContainer.setDailyFigureDayTotalAmount(expectedDailyFigureDayAmount)
	//Activa bandera de validación
	GlobalVariable.precondicionC3878Completa = true;

	GlobalVariable.dailyFigureContainer = dailyFigureContainer;
	return dailyFigureContainer;
}
/*catch (StepFailedException stepE) {
	String errorCode = testcaseId.concat('-09 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = 'La validación de los montos del día seleccionado '+dailyFigureDay+' no se pudo realizar debido que algún procedimiento o la precondición de este caso de prueba no se cumplió '+
			CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode, 'C6400')

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),stepE);
}*/
catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'El monto que refleja el día  '+dailyFigureDay+', el cual es: '+expectedDailyFigureDayAmount+" no es igual al monto total de la sumatoria de sus rubros, el cual es: "+actualDailyFigureDayAmount

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),asserError);
}
/*catch (Exception e) {
	String errorCode = testcaseId.concat('-99 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = ('La prueba no se pudo realizar debido a un fallo anomalo en la prueba ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
			errorCode))

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),e);
}*/
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, customerPIN)

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
	//	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}


	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		//CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}



