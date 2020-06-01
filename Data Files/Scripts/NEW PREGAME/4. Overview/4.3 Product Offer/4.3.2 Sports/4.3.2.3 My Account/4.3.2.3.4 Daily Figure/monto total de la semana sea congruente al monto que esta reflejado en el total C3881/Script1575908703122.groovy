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

String testcaseId = 'C3881'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String dailyFigureTransactionsDay = 'Monday'

String INNER_TEXT_ATT = 'innerText'

Double expectedTotalAmount = 0.0

Double actualTotalAmount = 0.0

DailyFigureContainer dailyFigureContainer = null

String testFailedMessageDesc = ('Caso de prueba ' + testcaseId) + ' Fallido.'

String weekperiodDate = ''

try {
	//Registro fecha incio de la prueba
	testResultData.put(3, testStartDate)

	//Registro  hora  incio de la prueba
	testResultData.put(4, testStartHour)

	//Valida precondición
		dailyFigureContainer = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/La suma de las rubros de los dias de la semana sean iguales al monto total C6407'),
				[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass, ('dailyFigureDay') : dailyFigureDay
					, ('dailyFigureWeekNumber') : dailyFigureWeekNumber], FailureHandling.STOP_ON_FAILURE)
	
	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	//Defife el peridodo del weekly figure semanal
	weekperiodDate = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/GenerarFechaDailyfigure'),
			[('dayOfTheWeek') : '', ('dailyFigureWeekNumber') : dailyFigureWeekNumber], FailureHandling.STOP_ON_FAILURE)

	weekperiodDate = weekperiodDate.substring(0,weekperiodDate.indexOf(" ")).trim();


	//Voy a Customer Maitneance a comparar el total de la semanans eleccioanda con el total de Weekly Figure
	WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/IraPerfomance'), [('customerId') : customerPIN],
	FailureHandling.STOP_ON_FAILURE)

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/PERFOMANCE/DAILY/DailyFigureDetail/Page_Customer Maintenance/Page_Customer Maintenance/input_Weekly_bt'))

	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/PERFOMANCE/DAILY/DailyFigureDetail/Page_Customer Maintenance/Page_Customer Maintenance/th_ReportDailyFigureNet'),
			2)

	actualTotalAmount = dailyFigureContainer.getDailyFigureWeekTotalAmount();

	List<WebElement>  dailyFigureWeeklyPeriods = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'("css", "table#tlbReportDetail tr", 2)

	for(int pos=dailyFigureWeeklyPeriods.size()-1;pos>0;pos--){
		WebElement dailyFigureReportTableRow=dailyFigureWeeklyPeriods.get(pos);
		WebElement dailyFigureWeeklyRecord = dailyFigureReportTableRow.findElement(By.cssSelector("td:nth-child(1)"));

		String weekPeriod = dailyFigureWeeklyRecord.getAttribute(INNER_TEXT_ATT);

		if(weekPeriod.contains(weekperiodDate)){
			expectedTotalAmount = Double.parseDouble(dailyFigureReportTableRow.findElement(By.cssSelector("td:nth-child(4)")).getAttribute(INNER_TEXT_ATT));
			break;
		}
	}

	assert actualTotalAmount == expectedTotalAmount;
	testStatus = 'Exitoso'

	testResultDescription = (('La sumatoria de los rubros de cada día del jugador ' + customerPIN) + ' es igual al monto total reflejado ')

	CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'();
}
/*catch (StepFailedException stepE) {
	String errorCode = testcaseId.concat('-09 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = (((' no se pudo comprobar la sumatoria de las transacciones diarias de la semana pasada  del jugador ' +
			customerPIN) + ' debido a que un paso en la prueba no se cumplió ') + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(
			errorCode, 'C6407'))

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