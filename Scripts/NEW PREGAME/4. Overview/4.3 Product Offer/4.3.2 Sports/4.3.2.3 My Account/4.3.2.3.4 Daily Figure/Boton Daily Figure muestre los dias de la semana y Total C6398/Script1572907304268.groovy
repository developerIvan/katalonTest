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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.util.Map as Map

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C6398'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

try {
    //Precondicion botón daily figure es visible
    WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Boton Daily Figure sea correctamente visible en Pregame C6397'), 
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

    //Se hace click en el botón de Daily Figure
    if (!(GlobalVariable.botonDailyFigureEsPresionado)) {
        WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/Presionar boton Daily Figure'), 
            [:], FailureHandling.STOP_ON_FAILURE)
    }
    
    Map<String,String> daysOfWeekMap = 	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/Validar visibilidad de dias de Daily Figure'),
		[:], FailureHandling.STOP_ON_FAILURE)
	
    String mondayDayDFTableHeader = daysOfWeekMap.get("monday");

    String thursdayDFTableHeader =  daysOfWeekMap.get("thursday");

    String wendesdayDFTableHeader = daysOfWeekMap.get("wendesday");

    String tuesdayDFTableHeader = daysOfWeekMap.get("tuesday");

    String fridayDFTableHeader = daysOfWeekMap.get("friday");

    String saturdayDFTableHeader = daysOfWeekMap.get("saturday");

    String sundayDFTableHeader = daysOfWeekMap.get("sunday");

    String totalDFTableHeader = daysOfWeekMap.get("total");

    assert mondayDayDFTableHeader != null

    assert thursdayDFTableHeader != null

    assert wendesdayDFTableHeader != null

    assert tuesdayDFTableHeader != null

    assert fridayDFTableHeader != null

    assert saturdayDFTableHeader != null

    assert sundayDFTableHeader != null

    assert totalDFTableHeader != null

    testStatus = 'Exitoso'

    testResultDescription = (((((((((((((((('Los días de la semana ' + mondayDayDFTableHeader) + ',') + tuesdayDFTableHeader) + 
    ',') + wendesdayDFTableHeader) + ',') + thursdayDFTableHeader) + ',') + fridayDFTableHeader) + ',') + saturdayDFTableHeader) + 
    ',') + sundayDFTableHeader) + ' y  encabezado de sumatorial ') + totalDFTableHeader) + ' se ven correctamente en la tabla de días de la semana ')
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = '-09'

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

    testResultDescription = ('La tabla de días de la semana no muestra los días de la semana  debido a que un paso de la prueba no se completo o un elemento de la página que no está visible. ' + 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode))

    throw stepE
} 
catch (AssertionError asserError) {
    String errorCode = '-C6398_10'

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

    testResultDescription = (('Alguno o ningún día de la semana es visible, favor usar el siguiente código: ' + errorCode) + 
    ' para buscar la causa del error de aseguramiento de los días de la semanan')

    throw asserError
} 
catch (Exception e) {
    String errorCode = '-99'

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

    testResultDescription = ('La tabla de días de la semana no muestra los días de la semana  debido a un error anomalo en la prueba. ' + 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode))

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



