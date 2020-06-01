import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String testcaseId = 'C3783'

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(9)

boolean tomarInstantanea = false

String exceptionMessageDesc = ('Caso de prueba ' + testcaseId) + ' Fallido.'

//Guarda url o dirrecion del sitio según el ambiente
testResultData.put(0, url)

//Registro fecha incio de la prueba
testResultData.put(1, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(2, testStartHour)

TestObject loginButton = new TestObject('BotonInexistente')

try {
    //Valida que el site de pregame on se haya cargado ya en el navegador
WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/WEB-STE de new pregame se despliegue correctamente C3809'), 
    [('url') : GlobalVariable.pregameUrl], FailureHandling.STOP_ON_FAILURE)
    
    loginButton = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('login', 'css', '#logIn', 2)

    OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

    browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

    screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

    //Guarda Version del browser
    testResultData.put(5, browserVersion)

    //Guarda Version del sistema operativo
    testResultData.put(4, OsName)

    //Guarda Version del sistema operativo
    testResultData.put(6, screenResolution)

    assert !(loginButton.equals(CustomKeywords.'com.utils.AutomationUtils.getNullObject'()))

    GlobalVariable.pregameSiteEsVisible = true

    testStatus = 'Exitoso'

    testResultDescription = 'El botón login es visible'
}
catch (AssertionError asserError) {
    String errorCode = testcaseId.concat('-09 ')

    KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

    tomarInstantanea = true

    testResultDescription = 'El botón login debería ser visible, pero actualmente no lo es. lo cual indica que, o el botón desaparecio  o fue modificado, lo cual causa que la prueba automatizada no lo pueda encontrar. Favor buscar en el log de katalon'

    throw new StepFailedException(errorCode.concat(exceptionMessageDesc), asserError)
} /*
catch (Exception e) {
    String errorCode = testcaseId.concat('-99 ')

    tomarInstantanea = true

    KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

    testResultDescription = ('El botón login debería ser visible, pero actualmente no lo es debido a un comportanmiento anomalo.' + 
    CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode))

    throw new StepFailedException(errorCode.concat(exceptionMessageDesc), e)
} */
finally { 
    //Guarda resultado de la prueba
    testResultData.put(7, testStatus)

    //Guarda descricion del resultado de la prueba
    testResultData.put(8, testResultDescription)

    //Guarda hora final
    testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

    testResultData.put(3, testEndHour)

    if (GlobalVariable.QaIndividualLog) {
        //Guarda resultado de prueba
     /*   CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, 
            testResultData)*/
    }
    
    //toma screenshot en caso de error
    if (tomarInstantanea == true) {
        CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
    }
}

return loginButton



