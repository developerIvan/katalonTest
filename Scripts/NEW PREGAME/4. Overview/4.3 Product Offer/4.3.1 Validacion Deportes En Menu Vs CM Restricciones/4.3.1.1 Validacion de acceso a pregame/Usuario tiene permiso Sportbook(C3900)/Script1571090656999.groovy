import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import internal.GlobalVariable as GlobalVariable
import java.lang.AssertionError as AssertionError
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

KeywordLogger log = new KeywordLogger()

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3900'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

try {
    if (!(GlobalVariable.usuarioLogeado)) {
        WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'), 
            [('url') : url, ('loginUser') : userName, ('loginPassword') : userPassword], FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.usuarioLogeado = true
    }
    
    //Guarda Sistema operativo
    OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

    testResultData.put(6, OsName)

    //Guarda Resoluci?n
    screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

    testResultData.put(8, screenResolution)

    //Guarda Version del browser
    browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

    testResultData.put(7, browserVersion)

    WebUI.waitForElementNotPresent(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/InitModal'), 6)
	
    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/div_Sports'), 2)

	
String permisoLive =	WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/div_Sports'), 'textContent', FailureHandling.STOP_ON_FAILURE)


    assert !(permisoLive.equals(null)) && !(permisoLive.isEmpty())

    //Guara estado de la prueba
    testStatus = 'Exitoso'

    //Guara descripci?n de la prueba
    testResultDescription = (('Permiso de Sportbook ' + permisoLive) + ' es correctamente accesible para el usuario')
}
catch (StepFailedException step) {
    //Guara descripci?n de la prueba
    testResultDescription = 'Validación de permiso Sportbook fallida por incumplimiento de verifaicación de elementos en la página'

    throw new AssertionError('Error en la prueba Sportbook debido a que hay un paso que no se cumplio', step)
} 
catch (Exception ex) {
    //Guara descripci?n de la prueba
    testResultDescription = 'Validación de permiso fallida causado por comnportamiento anomalo en la prueba'

    throw new AssertionError('Error en la prueba Sporbbok ', ex)
} 
finally { 
    //Guarda url o dirrecion del sitio según el ambiente
    testResultData.put(0, url)

    //Guarda pin del jugador que se usó para la prueba
    testResultData.put(1, userName)

    //Guarda password del jugador que se usó para la prueba
    testResultData.put(2, userPassword)

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

