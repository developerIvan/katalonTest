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
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
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

String testcaseId = 'C6407'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String dailyFigureTransactionsDay = 'Monday'

String INNER_TEXT_ATT = 'innerText'

Double expectedTotalAmount = 0.0

Double actualTotalAmount = 0.0

DailyFigureContainer dailyFigureContainer = null

String testFailedMessageDesc = ('Caso de prueba ' + testcaseId) + ' Fallido.'

String semanaSeleccioanda = "";
try {
	//Registro fecha incio de la prueba
	testResultData.put(3, testStartDate)

	//Registro  hora  incio de la prueba
	testResultData.put(4, testStartHour)

		WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Boton Daily Figure muestre los dias de la semana y Total C6398'),
				[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass, ('resultadoEsperado') : findTestData(
					'TestData/Datos de Entrada/4.3.2.3.4 Daily Figure').getValue(2, 1)], FailureHandling.STOP_ON_FAILURE)
		
	dailyFigureContainer = new DailyFigureContainer();

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	semanaSeleccioanda = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/SeleccionarSemana'),
			[('weekNumber') :Integer.parseInt(dailyFigureWeekNumber)], FailureHandling.STOP_ON_FAILURE)


	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Total'),
			2)

	String expectedtotalAmountText = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_TotalTransacctionAmount'),
			INNER_TEXT_ATT)

	expectedTotalAmount = Double.parseDouble(expectedtotalAmountText)

	actualTotalAmount = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/SumarElTotalDeTransaccionesDeLaSemana'),
			[:], FailureHandling.STOP_ON_FAILURE)

	assert expectedTotalAmount == actualTotalAmount

	dailyFigureContainer.setDailyFigureWeekTotalAmount(actualTotalAmount)

	//Se hace la comparación de cada transacción
	testStatus = 'Exitoso'

	testResultDescription = (('La sumatoria de los rubros de cada día del jugador ' + customerPIN) + ' es igual al monto total reflejado en la semana seleccionada '+semanaSeleccioanda)

	GlobalVariable.precondicionC6407Completa = true;

	return dailyFigureContainer;
}
/*catch (StepFailedException stepE) {
	String errorCode = testcaseId.concat('-09 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = (((' no se pudo comprobar la sumatoria que la sumatoria de los días de la semana  del jugador ' +
			customerPIN) + ' sean iguales al monto total debido a que no tiene rubros o algún paso previo no se cumplió. ') + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(
			errorCode, 'C6400'))

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc), stepE)
}*/
catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = ((((('La sumatoria de los rubros  diarios del jugador ' + customerPIN) + ' no es igual al monto total reflejado  Sumatoria esperada: ') +
			expectedTotalAmount) + ' Sumatrioa actual;') + actualTotalAmount)

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc), asserError)
}
/*catch (Exception e) {
	String errorCode = testcaseId.concat('-99 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = ('La prueba no se pudo realizar debido a un fallo anomalo en la prueba ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
			errorCode))

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc), e)
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
	//CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}


