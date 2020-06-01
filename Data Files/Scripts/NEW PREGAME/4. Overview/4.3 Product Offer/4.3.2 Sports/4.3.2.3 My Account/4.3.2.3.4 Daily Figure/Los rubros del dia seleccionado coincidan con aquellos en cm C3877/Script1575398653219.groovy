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

String testcaseId = 'C3877'

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

String dailyFigureDate = '';

DailyFigureContainer dailyFigureContainer = GlobalVariable.dailyFigureContainer;

String testFailedMessageDesc = ('Caso de prueba ' + testcaseId) + ' Fallido.'
try {
    //Registro fecha incio de la prueba
    testResultData.put(3, testStartDate)

    //Registro  hora  incio de la prueba
    testResultData.put(4, testStartHour)

    //Valida precondición
//	if(dailyFigureContainer == null || GlobalVariable.precondicionC6401Completa == false){
		  dailyFigureContainer =  WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Total de los rubros de un dia en daily figure concuerde con el monto neto CM C6401'), 
		        [('customerPIN') : customerPIN, ('url') : url, ('customerPass') : customerPass, ('dailyFigureWeekNumber') : dailyFigureWeekNumber
		            , ('dailyFigureDay') : dailyFigureDay], FailureHandling.STOP_ON_FAILURE)
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

    //Genera fecha compuesta de daily figue , la cuals e compone de la fehca y el dia: ejemplo 12/6/2019 Friday
    dailyFigureDate = dailyFigureContainer.getDailyFigureDate();

	
	dailyFigureContainer.setDailyFigureDate(dailyFigureDate);
	
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/ValidarRubrosEnCustomerMaintenance'),
		[('customerId') : customerPIN, ('dailyFigureContainer') : dailyFigureContainer,('currentUrlIsCm'):true], FailureHandling.STOP_ON_FAILURE)
    //Va a customer maitnenance a comparar los montos 
    testStatus = 'Exitoso'

    testResultDescription = (('La información de los rubros (Monto,tipo y derscripción) del jugador ' + customerPIN) + ' en Daily Fgiure de la fecha  '+dailyFigureDate +' correponde con la informaicón de los rubros de la misma fecha en custoemr maintenance')
	
	CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'()
	
}
/*catch (StepFailedException stepE) {
    String errorCode = testcaseId.concat(' -09')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

    testResultDescription ='La comparación de la información de los rubros (Monto,tipo y derscripción) del jugador ' + customerPIN+ ' no s epudo realizar por el imcumplimento de algún paso previo o precondición ncesaria.'+
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode,'C6401')

       throw new StepFailedException(errorCode.concat(testFailedMessageDesc),stepE);
} */
catch (AssertionError asserError) {
    String errorCode = testcaseId.concat(' -10')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

    testResultDescription = 'La información de los rubros del jugador ' + customerPIN + ' de la fecha seleccionada '+dailyFigureDate+ ' no correponden a los rubros de la misma fecha en custoemr maintenance. Favor ver el log o bitacora de katalon.';

       throw new StepFailedException(errorCode.concat(testFailedMessageDesc),asserError);
} 
/*catch (Exception e) {
    String errorCode = testcaseId.concat(' -99')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

    testResultDescription = ('La prueba no se pudo realizar debido a un fallo anomalo en la prueba ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
        errorCode))

     throw new StepFailedException(errorCode.concat(testFailedMessageDesc),e);
} */
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
	///   CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}

    //toma screenshot en caso de error
    if (errorEnLaPrueba == true) {
        CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
    }
	
	CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'()
}