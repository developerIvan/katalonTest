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
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C6401'

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

DailyFigureContainer dailyFigureContainer = GlobalVariable.dailyFigureContainer;

String testFailedMessageDesc = ('Caso de prueba ' + testcaseId) + ' Fallido.'

String dailyFigureDate = '';

Double expectedDailyFigureNetAmount = 0.00
Double actualDailyFigureDayAmount = 0.00

try {
	//Registro fecha incio de la prueba
	testResultData.put(3, testStartDate)

	//Registro  hora  incio de la prueba
	testResultData.put(4, testStartHour)

	//Validación de precondicion
//	if(dailyFigureContainer == null || GlobalVariable.precondicionC3878Completa == false ){
		dailyFigureContainer = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Suma de los rubros de un dia selecionado sean iguales al monto reflejado C3878'),
				[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass, ('dailyFigureDay') : dailyFigureDay
					, ('dailyFigureWeekNumber') : dailyFigureWeekNumber], FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.dailyFigureContainer = dailyFigureContainer;
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

	//Genera fecha de daily figure
	dailyFigureDate = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/GenerarFechaDailyfigure'),
			[('dayOfTheWeek') : dailyFigureDay, ('dailyFigureWeekNumber') : dailyFigureWeekNumber], FailureHandling.STOP_ON_FAILURE)

	dailyFigureContainer.setDailyFigureDate(dailyFigureDate)
	//Va a customer maintenance a comparar el monto del día en la sección de perfomance
	WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/IraPerfomance'), [('customerId') : customerPIN],
	FailureHandling.STOP_ON_FAILURE)

	WebUI.waitForElementVisible(	findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/PERFOMANCE/DAILY/div_Report Daily Figure'), 3)


	List<WebElement> reportDailyFigureDaysData = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'(CSS_SELECTOR_TYPE,
			'tbody#daily tr', 3)

	//Valida qure el reprote de daily figure tenga información para comparar
	WebUI.verifyNotEqual(reportDailyFigureDaysData.size(), 0)


	for(WebElement reportDailyFigureDayData:reportDailyFigureDaysData ){

		String cmDailyFigureDate = reportDailyFigureDayData.findElement(By.cssSelector("td:nth-child(1)")).getAttribute(INNER_TEXT_ATT);

		//Compara la fecha de daily figure pregame con la de customer maintenance en el reporte
		if(dailyFigureDate.equalsIgnoreCase(cmDailyFigureDate)){
			String cmDailyFigureNetAmount = reportDailyFigureDayData.findElement(By.cssSelector("td:nth-child(4)")).getAttribute(INNER_TEXT_ATT);
			expectedDailyFigureNetAmount = Double.parseDouble(cmDailyFigureNetAmount);
			break;
		}
	}

	actualDailyFigureDayAmount =dailyFigureContainer.getDailyFigureDayTotalAmount() != null?dailyFigureContainer.getDailyFigureDayTotalAmount() :0;
	assert   expectedDailyFigureNetAmount == actualDailyFigureDayAmount;
	testStatus = 'Exitoso'

	testResultDescription = (('El rubro total de la fecha '+dailyFigureDate+'  del jugador: ' + customerPIN) + ' es igual a rubro neto de la respecitva semana en customer maintenance. Dicho rubro es: '+expectedDailyFigureNetAmount)

   GlobalVariable.precondicionC6401Completa = true;
   
	
   GlobalVariable.dailyFigureContainer = dailyFigureContainer;
	return dailyFigureContainer;
	
	
}
/*catch (StepFailedException stepE) {
	String errorCode =testcaseId.concat('-09 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = ('El rubro total de la fecha '+dailyFigureDate+'  del jugador' + customerPIN+' no se pudo verificar debido  a algón procedimiento previo como ingreso a pregame no se pudo completar ') +
			CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode,'C3878')

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),stepE);
}*/
catch (AssertionError asserError) {
	String errorCode =testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'El rubro esperado de la fecha '+dailyFigureDate+'  del jugador '+customerPIN+' debería ser:  '+expectedDailyFigureNetAmount+ ' actualmente es: '+actualDailyFigureDayAmount

	throw new StepFailedException(errorCode.concat(testFailedMessageDesc),asserError);
}
/*catch (Exception e) {
	String errorCode = testcaseId.concat(' -99')

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
/*	if(GlobalVariable.QaIndividualLog){
		CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}*/


	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}

}



