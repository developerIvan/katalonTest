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
import com.utils.TransactionDetail as TransactionDetail
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.util.List as List
import java.util.ArrayList as ArrayList
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<TransactionDetail> rows = new ArrayList<TransactionDetail>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C6410'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String dailyFigureTransactionsDay = 'Monday'

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

	customerTransacctionsFromCM = GlobalVariable.mondayDailyFigureTransactions
	
    //Carga las transacciones del dia lunes de esta semana
  if(null == customerTransacctionsFromCM || customerTransacctionsFromCM.isEmpty()){	
      GlobalVariable.mondayDailyFigureTransactions = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/CargarTransaccionesDeCustomerMaintenance'), 
        [('customerId') : customerPIN, ('dayOfTheWeek') : 'Monday', ('CMIsCurrentUrl') : false, ('daysBefore') : 0, ('weekBefore') : 0], 
        FailureHandling.STOP_ON_FAILURE)
	  
	  customerTransacctionsFromCM = GlobalVariable.mondayDailyFigureTransactions
 
  }
  
  
  
    OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

    browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

    screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

    //Guarda Version del browser
    testResultData.put(7, browserVersion)

    //Guarda Version del sistema operativo
    testResultData.put(6, OsName)

    //Guarda resolucion de pantalla
    testResultData.put(8, screenResolution)

    //Voy a pregame a daily figure y validoq ue el monto de esta semana se refleje en la semana pasada
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Monto Transaccion lunes de esta semana aparesca como ajuste de semana pasada C6403'),
		[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass],
		FailureHandling.STOP_ON_FAILURE)
	
	
  //se presiona el monto que debe ser visible del día lunes
	TestObject mondayTdAmountData = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Monday Amount', 'css', ('tr.trReportDetail.show-data td[data-th="' +
			dailyFigureTransactionsDay) + '"] a', 3)

	WebUI.verifyElementClickable(mondayTdAmountData)

	WebUI.click(mondayTdAmountData)

	WebUI.delay(1)
	//Se hace la comparación de cada transacción
	for (TransactionDetail transaction : customerTransacctionsFromCM) {
		transactionId = transaction.getTicketId()

		//Clik en icono + para abrir la transacción
		TestObject transactionOpenDetailIcon = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Monday Amount',
				'css', ('i[id^="' + transactionId) + '"]', 2)

		WebUI.click(transactionOpenDetailIcon)

		transactionContainerCSS = (('div#wpr_contentWagerDiv_' + transactionId) + ' ')

		descriptionLocatorCss = transactionContainerCSS.concat('div.rightFloatDiv.table-cell')

		wagerTypeLocatorCss = transactionContainerCSS.concat('div.leftFloatDiv.table-cell span:nth-child(1)')

		wagerAmountLocatorCss = transactionContainerCSS.concat('div.leftFloatDiv.table-cell span:nth-child(3)')

		TestObject transactionDescriptionObj = CustomKeywords.'com.utils.AutomationUtils.findTestObject'(('Transaction ' +
				transactionId) + ' description', CSS_SELECTOR_TYPE, descriptionLocatorCss, 2)

		TestObject transactionAmountObj = CustomKeywords.'com.utils.AutomationUtils.findTestObject'(('Transaction ' + transactionId) +
				' lost/won amount', CSS_SELECTOR_TYPE, wagerAmountLocatorCss, 2)

		TestObject transactionWagerType = CustomKeywords.'com.utils.AutomationUtils.findTestObject'(('Transaction ' + transactionId) +
				' type', CSS_SELECTOR_TYPE, wagerTypeLocatorCss, 2)

		String actualDescription = WebUI.getAttribute(transactionDescriptionObj, INNER_TEXT_ATT)

		String actualAmount = WebUI.getAttribute(transactionAmountObj, INNER_TEXT_ATT)

		String actualTransactionType = WebUI.getAttribute(transactionWagerType, INNER_TEXT_ATT)

		//Validación de que los datos de las transacciones de customer maintenance sean visibles en pregame
		assert actualAmount.contains(Double.toString(transaction.getTransacctionLostWonAmount()))

		assert actualTransactionType.contains(transaction.getTransacctionType())
	}


   	testStatus = 'Exitoso'

	testResultDescription = (('Las transacciones del jugador ' + customerPIN) + ' del día lunes de customer maintenance de la semana pasada aparecen exitosamente en pregame ')
}catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = '-09'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = ((('El jugador ' + customerPIN) + ' no tiene transacciones para el día lunes de la semana pasada  ') +
			CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode,'C6403'))

	throw stepE
}
catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'Los datos de las transacciones de customer maintenace no cuadran con las transacciones que se desplieguan en pregame , favor revisar el log de katalon '

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


