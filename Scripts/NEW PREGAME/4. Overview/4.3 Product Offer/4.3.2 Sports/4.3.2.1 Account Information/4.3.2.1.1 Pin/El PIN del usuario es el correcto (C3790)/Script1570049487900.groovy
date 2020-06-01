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
import sun.net.www.content.text.plain
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.lang.Exception as Exception
String url = GlobalVariable.pregameUrl

String userPIN = GlobalVariable.customerPIN

String userPassword = GlobalVariable.customerPassword

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C3790'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String pregameUserPin = "";

String failedTestCaseMessage = "Caso de prueba "+testcaseId+ " Fallido";

try{
//Si el usuario no ha ingresado al sitio llamará a la función de login
if (!(GlobalVariable.usuarioLogeado)) {
    WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'), 
        [('url') : url, ('loginUser') : userPIN, ('loginPassword') : userPassword], FailureHandling.STOP_ON_FAILURE)

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

//Valdiación de pin
 pregameUserPin = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'('User Pin', 'textContent', 'css', '.CustomerID', 
    2)

assert userPIN.equalsIgnoreCase(pregameUserPin)

testStatus = 'Exitoso'


testResultDescription = "El pin del jugador jugador "+userPIN+" se visualiza correctamente en pregame"
}/*catch (StepFailedException step) {
    //Guara descripci?n de la prueba
	
	String errorCode = testcaseId.concat('-09 ')
	
	errorEnLaPrueba = true
	
	KeywordLogger.getInstance(this.class).logger.error(errorCode, step)
		
    testResultDescription = 'Validación de pin del usuario fallida debido a que alguno de los pasos requeridos nos e cumplieron '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode,'C6414')

    throw new StepFailedException(errorCode.concat(failedTestCaseMessage),step);
}*/catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = "Se esperaba que pregame mostrara el siguiente pin en pregame:"+userPIN + " pero actualmente refleja el siguiente pin "+pregameUserPin;

	throw new StepFailedException(errorCode.concat(failedTestCaseMessage),asserError);
}/* catch (Exception ex) {
	String errorCode = testcaseId.concat('-99 ')
	
	KeywordLogger.getInstance(this.class).logger.error(errorCode, ex)
    //Guara descripci?n de la prueba
    testResultDescription = 'Validación de permiso fallida causado por comnportamiento anomalo en la prueba'+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(
			errorCode)

    throw new StepFailedException(errorCode.concat(failedTestCaseMessage),ex);
} */
finally { 
    //Guarda url o dirrecion del sitio según el ambiente
    testResultData.put(0, url)

    //Guarda pin del jugador que se usó para la prueba
    testResultData.put(1, userPIN)

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
	if(GlobalVariable.QaIndividualLog){
     //  CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}
    //toma screenshot en caso de error
    if (errorEnLaPrueba == true) {
        CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
    }
}
