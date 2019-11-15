import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
String testEndHour = '';

String browserVersion = '';

String screenResolution = '';

String OsName = '';

String testStatus = 'Fallido';

String testResultDescription = '';

ArrayList<Integer> rows = new ArrayList<Integer>();
rows.add(1);

HashMap<Integer,String> testResultData = new  HashMap();

String testcaseId = "C6414";

boolean tomarInstantanea = false;

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'();

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'();

ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

String XPATH_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getXPathSelectorId'()

//Registro fecha de la prueba
testResultData.put(3,testStartDate);

//Registro  hora  incio de la prueba
testResultData.put(4,testStartHour);

if(GlobalVariable.pregameSiteEsVisible == false){
	WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'),
			[('url') : url], FailureHandling.STOP_ON_FAILURE)
}


WebUI.maximizeWindow();

OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

//Guarda Version del browser
testResultData.put(7,browserVersion);

//Guarda Version del sistema operativo
testResultData.put(6,OsName);

//Guarda resolucion de pantalla
testResultData.put(8,screenResolution);



try {
	WebUI.delay(2)

	WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/a_Login'), 2)
	
	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/a_Login'))
	
	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_Welcome Back_user'), 2)
	
	WebUI.sendKeys(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_Welcome Back_user'), loginUser)
	
	WebUI.sendKeys(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_Welcome Back_password'), loginPassword)
	
	//click para entrar al sitio
	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/button_Enter'))

	WebUI.waitForElementPresent(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/Overview Button'), 2)

	String overviewButton = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/Overview Button'), "innerText")

	assert null != overviewButton

	//Result passed
	//Guara estado de la prueba
	testStatus = 'Exitoso';

	testResultDescription ="El jugador logró ingresar al Overwiew";

}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
	String errorCode = testcaseId.concat('-01')

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)

	testResultDescription = "El jugador no logró ingresar al Overwiew debido a un paso de la prueba o elmento de la página que no está visible."+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode);;

	throw stepE;
}catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = "El jugador no logra ingresar al overview debido auqe el boton Overview no es visible, favor revisar el log de katalon "

	throw asserError
}catch (Exception e) {
	String errorCode = testcaseId.concat('-99')

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = "El jugador no logró ingresar al Overwiew debido a un error anomalo en la prueba."+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode);

	throw e;
}finally{
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0,url);

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1,loginUser);

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2,loginPassword);

	//Guarda hora final de la prueba
	testEndHour =  CustomKeywords.'com.utils.ReportHelper.getHours'();
	testResultData.put(5,testEndHour);

	//Guarda Resultado de la prueba
	testResultData.put(9,testStatus);

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10,testResultDescription);

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if(tomarInstantanea == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation,testcaseId)
	}

}