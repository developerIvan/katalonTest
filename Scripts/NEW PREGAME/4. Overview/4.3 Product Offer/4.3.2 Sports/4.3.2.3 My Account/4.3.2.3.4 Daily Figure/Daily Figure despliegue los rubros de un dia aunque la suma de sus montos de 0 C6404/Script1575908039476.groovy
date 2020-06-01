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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.utils.DailyFigureItemDetail as DailyFigureItemDetail

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C6410'

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String dailyFigureWeek = ''

String testFailedMessageDesc = ('Caso de prueba ' + testcaseId) + ' Fallido.'

String dayOfWeek = ''

String zeroDayAmount = '';

String expectedZeroDayAmount = '0';

double expectedZeroAmount = 0.0;
double actualZeroAmount = 0.0;
try {
    //Registro fecha incio de la prueba
    testResultData.put(3, testStartDate)

    //Registro  hora  incio de la prueba
    testResultData.put(4, testStartHour)

    WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Boton Daily Figure muestre los dias de la semana y Total C6398'), 
        [('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass], FailureHandling.STOP_ON_FAILURE)
 
    WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Boton Daily Figure muestre el combobox de semanas C3864'), 
        [('url') : url, ('customerPIN') : customerPIN, ('customerPass') : customerPass], FailureHandling.STOP_ON_FAILURE)

    OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

    browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

    screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

    //Guarda Version del browser
    testResultData.put(7, browserVersion)

    //Guarda Version del sistema operativo
    testResultData.put(6, OsName)

    //Guarda resolucion de pantalla
    testResultData.put(8, screenResolution)

	dayOfWeek = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/SeleccionarDia'),
		[('day') : dailyFigureDay], FailureHandling.STOP_ON_FAILURE)
	
    //Selecciona la semana 
    dailyFigureWeek = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/SeleccionarSemana'), 
        [('weekNumber') :Integer.parseInt(dailyFigureWeekNumber)], FailureHandling.STOP_ON_FAILURE)

    //Selecciona el día
    TestObject dayTdAmountData = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Daily Figure Day Amount', 'css', ('tr.trReportDetail.show-data td[data-th="' + 
        dayOfWeek) + '"] a', 2)

	WebUI.verifyElementVisible(dayTdAmountData)
	
	zeroDayAmount = WebUI.getAttribute(dayTdAmountData, "innerText");
	
	WebUI.verifyEqual(zeroDayAmount, expectedZeroDayAmount)
	
    WebUI.verifyElementClickable(dayTdAmountData)

    WebUI.click(dayTdAmountData)

    //Agrupa los rubors de daily figure
    DailyFigureContainer container = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/CargarRubrosEnPregame'), 
        [:], FailureHandling.STOP_ON_FAILURE)

	assert (container.getDailyFigureItemDetails() != null) && (container.getDailyFigureItemDetails().size() > 0)
	
	for(DailyFigureItemDetail dailyFigure:container.getDailyFigureItemDetails()){
		actualZeroAmount += dailyFigure.itemLostWonAmount;
		
	}

	assert actualZeroAmount == expectedZeroAmount;
	testStatus = 'Exitoso'

    testResultDescription = ((((('El reporte de Daily Figure muestra satisfactoriamente los rubros jugador ' + customerPIN) + 
    ' del dia   ') +dayOfWeek) + 'aunque el resultado de estas sume zero'))

    return container
}
/*catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = testcaseId.concat('-09 ')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

    testResultDescription =  'No se pudo validar que daily figure despliegue los rubros aunque sla suma de estos sea zero por los siguientes motivos: El jugador ' + customerPIN +  ' no tiene rubros  para el día ' + dailyFigureDay + ' , dcho jguador tiene rubros pero la suma de estos da como resultado 0 o  o algún procedimiento previo  no se pudo completar'+
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode, 'C6398', 'C3864')

    throw new StepFailedException(errorCode.concat(testFailedMessageDesc), stepE)
} */
catch (AssertionError asserError) {
    String errorCode = testcaseId.concat('-10 ')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

    testResultDescription = (('El jugador ' + customerPIN) + ' no tiene rubros para el día seleccionado o la suma de estos es diferente de cero, favor  revisar el log de katalon ')

    throw new StepFailedException(errorCode.concat(testFailedMessageDesc), asserError)
} 
/*catch (Exception e) {
    String errorCode = testcaseId.concat('-99 ')

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

    testResultDescription = ('La prueba no se pudo realizar debido a un fallo anomalo en la prueba ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
        errorCode))

    throw new StepFailedException(errorCode.concat(testFailedMessageDesc), e)
} */
finally { 
    //Guarda url o dirrecion del sitio según el ambiente
    testResultData.put(0, url)

    //Guarda pin del jugador que se  para la prueba
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
    if (GlobalVariable.QaIndividualLog) {
      /*  CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, 
            testResultData)*/
    }
    
    //toma screenshot en caso de error
    if (errorEnLaPrueba == true) {
        CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
    }
}