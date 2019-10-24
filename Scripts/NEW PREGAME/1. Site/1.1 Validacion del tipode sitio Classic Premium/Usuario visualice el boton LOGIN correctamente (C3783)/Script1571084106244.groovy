import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.ArrayList;
import java.util.HashMap;

import internal.GlobalVariable as GlobalVariable

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'();
String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'();

String testEndHour = '';
String browserVersion = '';
String screenResolution = '';
String testcaseId = 'C3783';
String OsName = '';
String testStatus = 'Fallido';
String testResultDescription = '';
ArrayList<Integer> rows = new ArrayList<Integer>();
rows.add(1);

HashMap<Integer,String> testResultData = new  HashMap();

boolean tomarInstantanea=false;

//Guarda url o dirrecion del sitio según el ambiente
testResultData.put(0,url);
//Registro fecha incio de la prueba
testResultData.put(1,testStartDate);

//Registro  hora  incio de la prueba
testResultData.put(2,testStartHour);

TestObject loginButton = new TestObject("BotonInexistente");

try {
	//Valida que el site de pregame on se haya cargado ya en el navegador
	if(GlobalVariable.pregameSiteEsVisible == false){
		WebUI.openBrowser('')

		WebUI.navigateToUrl(url)

		WebUI.maximizeWindow()
	}
	
	loginButton = CustomKeywords.'com.utils.AutomationUtils.findTestObject'("login", "css", "#logIn", 2);


	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()
	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'();
	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()


	//Guarda Version del browser
	testResultData.put(5,browserVersion);


	//Guarda Version del sistema operativo
	testResultData.put(4,OsName);

	//Guarda Version del sistema operativo
	testResultData.put(6,screenResolution);

	assert !loginButton.equals(CustomKeywords.'com.utils.AutomationUtils.getNullObject'());

	GlobalVariable.pregameSiteEsVisible = true;

	testStatus = "Exitoso";

	testResultDescription = 'El botón login debería es visible';

} catch(java.lang.AssertionError asserError){
	tomarInstantanea = true;
	KeywordUtil.logger.logError((('Error code: -10') + ' error message :') + asserError.getMessage())
	testResultDescription = 'El botón login debería ser visible, pero actualmente no lo es. lo cual indica que, o el botón desaparecio  o fue modificado, lo cual cuasa que la prueba automatizada no lo pueda encontrar';

	throw asserError;
}  catch(Exception e){
	tomarInstantanea = true;
	KeywordUtil.logger.logError((('Error code: -99') + ' error message :') + e.getMessage())

	//	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, )
	testResultDescription = 'El botón login debería ser visible, pero actualmente no lo es debido a un comportanmiento anomalo';

	throw e;
}finally{
	//Guarda resultado de la prueba
	testResultData.put(7,testStatus);

	//Guarda descricion del resultado de la prueba
	testResultData.put(8,testResultDescription);

	//Guarda hora final
	testEndHour =CustomKeywords.'com.utils.ReportHelper.getHours'();
	testResultData.put(3,testEndHour);

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)


	//toma screenshot en caso de error
	if(tomarInstantanea == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation,testcaseId)
	}
}

return loginButton;