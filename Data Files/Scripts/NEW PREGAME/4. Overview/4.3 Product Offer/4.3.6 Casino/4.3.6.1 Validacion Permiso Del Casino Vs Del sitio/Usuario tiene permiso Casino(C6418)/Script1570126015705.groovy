import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepFailedException

import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObjectProperty
import internal.GlobalVariable as GlobalVariable
import java.lang.AssertionError;
import com.kms.katalon.core.testobject.ConditionType

import com.kms.katalon.core.testobject.TestObject as TestObject

String testcaseId = "C6418";

KeywordLogger log = new KeywordLogger()

String url = GlobalVariable.pregameUrl;

String userName = GlobalVariable.customerPIN;

String userPassword = GlobalVariable.customerPassword;

String startHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = 'Permiso Casino es correctamente accesible para el usuario'

boolean errorEnLaPrueba = false

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String failedTestCaseMessage = ('Caso de prueba ' + testcaseId) + ' Fallido'


final String FAILED_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getFailedStatus'();

final String SUCCESS_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getsuccessStatus'();

final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'();

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()



TestObject enlaceCasinoObj = null;
try{
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

	String permiso = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'('Enloace casino Obj', textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType, "a.dropdown-toggle .mainMenuLi div"),4);

	assert permiso != null && !permiso.isEmpty();

	//Guarda permito otorgado
	testResultData.put(11, permiso)

	//Guara estado de la prueba
	testStatus = SUCCESS_STATUS;
	testResultData.put(12, testStatus)

	//Guara descripci?n de la prueba
	testResultData.put(13, testResultDescription)
}catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')
	
	errorEnLaPrueba = true
	
	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)
	
	testResultDescription = ((('El usuario debería poder ver el permiso de Casino pero el enlace no es visible ') ))
	
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
