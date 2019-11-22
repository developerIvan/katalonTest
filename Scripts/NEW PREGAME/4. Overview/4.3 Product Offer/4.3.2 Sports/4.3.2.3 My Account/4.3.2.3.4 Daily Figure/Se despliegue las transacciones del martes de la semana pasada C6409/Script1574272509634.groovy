import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.utils.TransactionDetail as TransactionDetail

import internal.GlobalVariable as GlobalVariable

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<TransactionDetail> rows = new ArrayList<TransactionDetail>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C6409'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String dailyFigureTransactionsDay = 'Tuesday'

//Selectores css para las transacciones en pregame
String transactionId = ''

String transactionContainerCSS = null

String descriptionLocatorCss = null

String wagerTypeLocatorCss = null

String wagerAmountLocatorCss = null

String CSS_SELECTOR_TYPE = 'CSS'

String INNER_TEXT_ATT = 'innerText'

List<TransactionDetail> customerTransacctionsFromCM = new ArrayList<TransactionDetail>()

try {
	//Registro fecha incio de la prueba
	testResultData.put(3, testStartDate)

	//Registro  hora  incio de la prueba
	testResultData.put(4, testStartHour)

   //Consulta las transacciones de pregame
	customerTransacctionsFromCM = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/CargarTransaccionesDeCustomerMaintenance'),
			[('customerId') : customerPIN, ('dayOfTheWeek') : dailyFigureTransactionsDay, ('CMIsCurrentUrl') : false, ('weekBefore') : 1
				, ('daysBefore') : 0], FailureHandling.STOP_ON_FAILURE)


	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	//Valida que el jugador tenga transacciones del día martes
	WebUI.verifyNotEqual(customerTransacctionsFromCM.size(), 0)

	//ir apregame y cargar la sección de daily figure
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Boton Daily Figure muestre los dias de la semana y Total C6398'),
			[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass], FailureHandling.STOP_ON_FAILURE)

	//Se selecciona la opción de last week del select de semanas
	WebUI.selectOptionByValue(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/select_DailyFigureWeeks'),
			'2', false)

	WebUI.verifyOptionSelectedByValue(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/select_DailyFigureWeeks'),
			'2', false, 30)

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/input_check_StartTuesdayDay'))

	WebUI.delay(1)

	//se presiona el monto que debe ser visible del día lunes
	TestObject tuesdayTdAmountData =findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_TuesdayTransactionAmount')
	
    String selector =  tuesdayTdAmountData.getSelectorCollection().get(SelectorMethod.CSS);

	tuesdayTdAmountData.setSelectorValue(SelectorMethod.CSS, selector.concat(" a"))
	
	WebUI.verifyElementClickable(tuesdayTdAmountData)

	WebUI.click(tuesdayTdAmountData)



	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/ValidarTransaccionesDeCMEnPregame'),
			[('transaccionesDeCustomerMaintenance') : customerTransacctionsFromCM], FailureHandling.STOP_ON_FAILURE)

	testStatus = 'Exitoso'

	testResultDescription = (('Las transacciones del jugador ' + customerPIN) + ' del día martes de la semana pasada aparecen exitosamente en pregame ')
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = '-09'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = ((('El jugador ' + customerPIN) + ' no tiene transacciones para el día martes de la semana pasada o su configuración de zero balance no es del martes o algón procedimiento previo como ingreso a pregame no se pudo completar ') +
			CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode,'C6406'))

	throw stepE
}
catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'Los datos de las transacciones de customer maintenace no cuadran con las transacciones que despliegua customer maintenace, favor revisar el log de katalon '

	throw asserError
}
catch (Exception e) {
	String errorCode = '-99'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = ('La prueba no se pudo realizar debido a un fallo anomalo en la prueba ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
			errorCode))

	throw e
}
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
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}


}
