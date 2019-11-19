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
import java.util.List as List
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import java.lang.StringBuilder as StringBuilder
String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C6399'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

StringBuilder expectedDaysOrder = new StringBuilder();

String strDaysCheckboxDesc = "";

StringBuilder actualDaysOrder = new StringBuilder();
List<WebElement> webEReOrderedDaysOfWeek;
try {
     //Valida Precondicion de los días de la semana
    WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Boton Daily Figure muestre los dias de la semana y Total C6398'), 
        [('url') : GlobalVariable.pregameUrl, ('customerPIN') : GlobalVariable.customerPIN, ('customerPass') : GlobalVariable.customerPassword], 
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

	//Consulta los dias de la semana
	Map<String, String> daysOfWeekMap = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/Validar visibilidad de dias de Daily Figure'),
	[:], FailureHandling.STOP_ON_FAILURE)

	expectedDaysOrder.append(daysOfWeekMap.get("tuesday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("wendesday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("thursday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("friday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("saturday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("sunday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("monday")+",")
	expectedDaysOrder.append(daysOfWeekMap.get("total"))
	
	
    //Presiona el check de "Start from tuesday"
    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/input_check_StartTuesdayDay'))

	strDaysCheckboxDesc = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/byStartTuesdayDescription'), 'value')	
	
	//Consulta los dias reordenados
    webEReOrderedDaysOfWeek = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'( "cSS", "tr.trReportDetail.show-data th", 2);
	
	WebUI.verifyNotEqual(webEReOrderedDaysOfWeek.size(), 0)
   
	for(WebElement webOrderedDayofWeek: webEReOrderedDaysOfWeek){
		actualDaysOrder.append(webOrderedDayofWeek.getAttribute("innerText")+",")
	}

   String actualDaysOrderResult = actualDaysOrder.toString().substring(0, actualDaysOrder.toString().length()-1);
	
	
	assert expectedDaysOrder.toString().equals(actualDaysOrderResult);
	
    testStatus = 'Exitoso'

    testResultDescription = 'El checkbox "'+strDaysCheckboxDesc+'" muestra correctamente los días de la semana con el siguiente orden: '+actualDaysOrderResult
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = '-09'

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

    testResultDescription = ('El cehckbox de "Start From Tuesday" no funciona correctamente debido un elemento de la página que no está visible. ' + 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode))

    throw new com.kms.katalon.core.exception.StepFailedException('Paso de la prueba  no completado', stepE)
} 
catch (AssertionError asserError) {
    String errorCode = '-10'

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

    testResultDescription = 'El orden esperado de días que el checkbox de "'+strDaysCheckboxDesc+'" debería desplegar es:  '+expectedDaysOrder.toString() + ' pero actualmente es:'+actualDaysOrder.toString()

    throw asserError
} 
catch (Exception e) {
    String errorCode = '-99'

    errorEnLaPrueba = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

    testResultDescription = ('El combobox de semanas no funciona correctamente debido a un error anomalo en la prueba. ' + CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
        errorCode))

    throw e
} 
finally { 
	expectedDaysOrder = null;
	actualDaysOrder = null;
	
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



