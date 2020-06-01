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
import java.lang.AssertionError;
KeywordLogger log = new KeywordLogger()
import com.kms.katalon.core.exception.StepFailedException;
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty


//--------------Variables de registro ---------------------------------//
String url = GlobalVariable.pregameUrl;

String userName = GlobalVariable.customerPIN;

String userPassword = GlobalVariable.customerPassword;

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testcaseId = 'C6419'

String testStatus = 'Fallido'

String testResultDescription = 'Permiso Horses es correctamente accesible para el usuario'

final String FAILED_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getFailedStatus'();

final String SUCCESS_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getsuccessStatus'();

final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'();

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

final String startHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

boolean errorEnLaPrueba = false

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String failedTestCaseMessage = ('Caso de prueba ' + testcaseId) + ' Fallido'

try{
// -----------------  Precondiciones ----------------------------------------------//
	if (!(GlobalVariable.usuarioLogeado)) {
		WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'), [('url') : url, ('loginUser') : userName, ('loginPassword') : userPassword],
		FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.usuarioLogeado = true;
	}

		//Guarda Sistema operativo
	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	String permisoHorses =  CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'('Enlace horses Obj', textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType, "a[data-lnk='horseStatus'] .mainMenuLi div"),4);

	assert permisoHorses != null && !permisoHorses.isEmpty();

	testStatus ='Exitoso';
}catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')
	
	errorEnLaPrueba = true
	
	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)
	
	testResultDescription = ((('El usuario debería poder ver el permiso de Horses pero el enlace no es visible ') ))
	
	throw new StepFailedException(errorCode.concat(failedTestCaseMessage), asserError)

}finally{
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

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'()

		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}