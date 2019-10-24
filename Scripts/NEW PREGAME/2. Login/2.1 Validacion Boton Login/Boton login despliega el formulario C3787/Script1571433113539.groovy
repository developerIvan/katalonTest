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
import com.kms.katalon.core.util.KeywordUtil

String testDate = CustomKeywords.'com.utils.ReportHelper.getDate'();
String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'();

String testEndHour = '';
String browserVersion = '';
String screenResolution = '';
String testcaseId = 'C3787';
String OsName = '';
String testStatus = 'Fallido';
String testResultDescription = '';

ArrayList<Integer> rows = new ArrayList<Integer>();
rows.add(1);

HashMap<Integer,String> testResultData = new  HashMap();




boolean tomarInstantanea=false;

String expectedUserInputBackgroundText = findTestData('TestData/Datos de Entrada/1.1 Validacion del tipode sitio Classic Premium').getValue(
		1, 1)

String actualUserInputBackGroundTxt = '';

//Guarda url o dirrecion del sitio según el ambiente
testResultData.put(0,url);
//Registro fecha incio de la prueba
testResultData.put(1,testDate);


//Registro  hora  incio de la prueba
testResultData.put(2,testStartHour);




TestObject loginButton =null;
try {
	

		loginButton = WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'),
				[('url') : url], FailureHandling.STOP_ON_FAILURE)
	

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()
	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'();
	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	WebUI.click(loginButton)

	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), 2)

	actualUserInputBackGroundTxt = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'),
			'placeholder')

	assert expectedUserInputBackgroundText.equals(actualUserInputBackGroundTxt)

	testStatus = 'Exitoso';

	testResultDescription = 'el formulario de ingreso es visible';
} catch(java.lang.AssertionError asserError){
	tomarInstantanea = true;
	KeywordUtil.logger.logError('Error code: -10 error message :' + asserError.getMessage())
	testResultDescription = 'El formulario debería ser visible, pero actualmente no lo es. lo cual indica que, o el  o fue modificado, lo cual cuasa que la prueba automatizada no lo pueda encontrar';
	throw asserError;
}catch(Exception e){
	tomarInstantanea = true;
	KeywordUtil.logger.logError('Error code: -99 boton login :' + e.getMessage())
	KeywordUtil.logger.logError('Error code: -99, error message :'+ e.getMessage())
	testResultDescription = 'El formaulrio de ingreso deberia ser visible, pero actualmente no lo es debido a un comportanmiento anomalo';
	throw e;
}finally{

	if(tomarInstantanea == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation,testcaseId)
	}


	//Guarda Version del sistema operativo
	testResultData.put(4,OsName);

	//Guarda Version del browser
	testResultData.put(5,browserVersion);

	//Guarda Version del sistema operativo
	testResultData.put(6,screenResolution);

	//Guarda hora final
	testEndHour =CustomKeywords.'com.utils.ReportHelper.getHours'();
	testResultData.put(3,testEndHour);

	//Guarda resultado de la prueba
	testResultData.put(7,testStatus);

	//Guarda descricion del resultado de la prueba
	testResultData.put(8,testResultDescription);

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
}
