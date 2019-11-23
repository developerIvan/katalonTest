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
import  com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C6402'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String dailyFigureTransactionsDay = 'Monday'

String INNER_TEXT_ATT = 'innerText'

List<TransactionDetail> customerTransacctionsFromCM = new ArrayList<Integer>()

try {
    //Registro fecha incio de la prueba
    testResultData.put(3, testStartDate)

    //Registro  hora  incio de la prueba
    testResultData.put(4, testStartHour)

	//Revisa que el jugador tenga transacciones para el día lunes
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Daily figure despliegue las transacciones del lunes de esta semana C6400'),
		[('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass],
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

	//Se presiona el botón de "Start Tuesday"	
	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/input_check_StartTuesdayDay'))
	
	findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/byStartTuesdayDescription')
	
	
	String startTuesdayLabel = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/byStartTuesdayDescription'), "value")
	
	//Se valida que el monto o enlace del día lunes no sea visible
	TestObject mondayTdAmountData = new TestObject();
	mondayTdAmountData.setSelectorMethod(SelectorMethod.CSS);
	mondayTdAmountData.setSelectorValue(SelectorMethod.CSS, 'tr.trReportDetail.show-data td[data-th="Monday"] a');
	
	WebUI.verifyElementNotPresent(mondayTdAmountData, 4)
	
    testStatus = 'Exitoso'

    testResultDescription = (('El botón de  ' +  startTuesdayLabel) + ' remueve las transacciones del día lunes del jugador '+customerPIN+' existosamente  ')
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = testcaseId.concat('-09')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

    testResultDescription = 'Las transacción (o transacciones) del lunes  del jugador '+customerPIN+' no desaprecen con el botón "Start from tuesday"'+ 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode)

    throw stepE
}catch (Exception e) {
    String errorCode = testcaseId.concat('-99')

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



