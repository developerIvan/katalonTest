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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

String testDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String testcaseId = 'C3797'

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

boolean tomarInstantanea = false

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

//Guarda url o dirrecion del sitio según el ambiente
testResultData.put(0, url)

//Registro fecha incio de la prueba
testResultData.put(3, testDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String testFailedMessageDesc = "Caso de prueba "+testcaseId+ " Fallido.";

try {
 WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/Boton login despliega el formulario C3787'), 
    [('url') : url], FailureHandling.STOP_ON_FAILURE)

    OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

    browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

    screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_chkRememberCredentials'))

    WebUI.sendKeys(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_pin_or_email'), customerPin)

    WebUI.sendKeys(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_password'), password)

    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/button_Enter'))

    WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.1 Validacion Deportes En Menu Vs CM Restricciones/4.3.1.1 Validacion de acceso a pregame/InitModal'), 4)

    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/span_customerPin'), 
        8)

    WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/div_Salir'), 4, 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/div_Salir'))

	//Refesca la pagina con F5
	WebUI.refresh();
	
    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/a_Login'), 4)

    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/a_Login'))

    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_chkRememberCredentials'), 
        2)

    WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_chkRememberCredentials'), 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/span_rememberCredentials_Desc'), 
        2)

    WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/span_rememberCredentials_Desc'), 
        FailureHandling.STOP_ON_FAILURE)

    customerPin = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_pin_or_email'), 
        'value')

    password = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_user_password'), 
        'value')

    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/button_Enter'))

    WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.1 Validacion Deportes En Menu Vs CM Restricciones/4.3.1.1 Validacion de acceso a pregame/InitModal'), 4)

    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/span_customerPin'), 
        8)

    //valida que el usuario logró entrar al sitio
    assert WebUI.getUrl().toString().contains('sportbook')

    testStatus = 'Exitoso'

    testResultDescription = 'La función de recordar credenciales  funciona correctamente'
}
catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')
    tomarInstantanea = true

    testResultDescription = 'La función de recordar credenciales o "remeber credentials" no funciona correctamente debido a que el ingreso al sitio de sportbook fue fallido  '

    throw new StepFailedException(errorCode.concat(testFailedMessageDesc),asserError);
}/* 
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
   String errorCode = testcaseId.concat('-09 ')

    tomarInstantanea = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

    testResultDescription = ('La opción de "Remember Credentials" debería ser visible, pero actualmente no lo es. Lo cual indica que, la caja de selección o la descripción no aparecen, lo cual cuasa que la prueba automatizada no lo pueda encontrar' + 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode,'C3787'))

   throw new StepFailedException(errorCode.concat(testFailedMessageDesc),stepE);
} 
catch (Exception e) {
    String errorCode = testcaseId.concat('99 ')

    tomarInstantanea = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

    testResultDescription = ('La opción de "Remember Credentials" debería ser visible, pero actualmente no lo es debido a un comportanmiento anomalo' + 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode))

   throw new StepFailedException(errorCode.concat(testFailedMessageDesc),e);
}*/ 
finally { 
    if (tomarInstantanea == true) {
        CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
    }
    
    //sale de session para ir al sitiod e login y otras pruebas puedan usar el sitio
    if ((tomarInstantanea == false) && (GlobalVariable.individualTestCase == false)) {
        WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/2.1 Validacion Boton Login/div_Salir'))
    }
    
    //Guarda hora final
    testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

    testResultData.put(5, testEndHour)

    //Guarda usuario de logeo
    testResultData.put(1, customerPin)

    //Guarda 
    testResultData.put(2, password)

    //Guarda Version del browser
    testResultData.put(7, browserVersion)

    //Guarda Version del sistema operativo
    testResultData.put(6, OsName)

    //Guarda Version del sistema operativo
    testResultData.put(8, screenResolution)

    //Guarda resultado de la prueba
    testResultData.put(9, testStatus)

    //Guarda descricion del resultado de la prueba
    testResultData.put(10, testResultDescription)

    //Guarda resultado de prueba
	//Guarda resultado de prueba
	if(GlobalVariable.QaIndividualLog){
      // CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}
}



