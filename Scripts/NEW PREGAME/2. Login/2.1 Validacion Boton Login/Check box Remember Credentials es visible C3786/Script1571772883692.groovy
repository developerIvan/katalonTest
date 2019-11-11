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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;

String testDate = CustomKeywords.'com.utils.ReportHelper.getDate'();
String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'();
String testEndHour = '';
String browserVersion = '';
String screenResolution = '';
String testcaseId = 'C3786';
String OsName = '';
String testStatus = 'Fallido';
String testResultDescription = '';

boolean errorEnlaPrueba = true;

ArrayList<Integer> rows = new ArrayList<Integer>();
rows.add(1);

HashMap<Integer,String> testResultData = new  HashMap();

//Guarda url o dirrecion del sitio según el ambiente
testResultData.put(0,url);
//Registro fecha incio de la prueba
testResultData.put(1,testDate);


//Registro  hora  incio de la prueba
testResultData.put(2,testStartHour);

try {
	WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/Boton login despliega el formulario C3787'),
			[('url') : GlobalVariable.pregameUrl], FailureHandling.STOP_ON_FAILURE)

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()
	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'();
	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_Welcome_chkRemember'), 2)

	WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/input_Welcome_chkRemember'), FailureHandling.STOP_ON_FAILURE)

	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/span_RememberCredentials'), 2)

	WebUI.verifyElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/span_RememberCredentials'), FailureHandling.STOP_ON_FAILURE)

	testStatus = 'Exitoso';

	testResultDescription = 'La opción de recordar credenciales es visible correctamente';

} catch(com.kms.katalon.core.exception.StepFailedException stepE){
    errorCode = "-10";
	errorEnlaPrueba = true;
	 KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)
	testResultDescription = 'La opción de "Remember Credentials" debería ser visible, pero actualmente no lo es. Lo cual indica que, la caja de selección o la descripción no aparecen, lo cual cuasa que la prueba automatizada no lo pueda encontrar '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode);
	throw stepE;
}catch(Exception e){
     errorCode = "-99";
	errorEnlaPrueba = true;
     KeywordLogger.getInstance(this.class).logger.error(errorCode, e)
	testResultDescription = 'La opción de "Remember Credentials" debería ser visible, pero actualmente no lo es debido a un comportanmiento anomalo'+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode);
	throw e;
}finally{
	if(errorEnlaPrueba == true){
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