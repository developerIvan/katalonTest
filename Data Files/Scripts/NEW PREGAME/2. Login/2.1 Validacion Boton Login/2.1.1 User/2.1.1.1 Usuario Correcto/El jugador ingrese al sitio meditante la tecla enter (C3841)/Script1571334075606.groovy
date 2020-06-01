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
import bminc.eu.exceptions.LoginException as LoginException
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
import com.kms.katalon.core.exception.StepFailedException as StepFailedException

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C3841'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String exceptionMessageDesc = "Caso de prueba "+testcaseId+ " Fallido.";

ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

String XPATH_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getXPathSelectorId'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)


try {
	if (GlobalVariable.pregameSiteEsVisible == false) {
		WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/Boton login despliega el formulario C3787'),
				[:], FailureHandling.STOP_ON_FAILURE)
	}

	WebUI.maximizeWindow()

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7,browserVersion);

	//Guarda Version del sistema operativo
	testResultData.put(6,OsName);

	//Guarda resolucion de pantalla
	testResultData.put(8,screenResolution);

	
	TestObject userPinInput = findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/2.1 Login/input_user_pin_or_email');

	WebUI.waitForElementVisible(userPinInput, 3)
	

	TestObject userPasswordInput =	findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/2.1 Login/input_user_password')


	WebUI.sendKeys(userPinInput, loginUser)

	WebUI.sendKeys(userPasswordInput, loginPassword)

	//Debido a un bug en el navegador de firefox, la prueba usará click en lugar de la tecla enter
	if (!(browserVersion.toString().contains('Firefox'))) {
		WebUI.sendKeys(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/button_Enter'), Keys.chord(Keys.ENTER)
				)
	} else {
		WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/2.1 Login/button_Enter'))
	}

	// WebUI.delay(4)
	WebUI.waitForElementPresent(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/Overview Button'), 8)

	//valida que el usuario logró entrar al sitio
	assert WebUI.getUrl().toString().contains('sportbook')

	//Guara estado de la prueba
	testStatus =  'Exitoso';

	testResultDescription = 'El jugador logró ingresar al sitio de pregame con la tecla enter';

}
catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'El jugador no logró ingresar al sitio de pregame con la tecla enter, la pagina esperada debería tener el domino o sección sportbook pero actualmente es: ' +
			WebUI.getUrl().toString();

	throw new StepFailedException(errorCode.concat(exceptionMessageDesc),asserError);
}
finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0,url);

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1,loginUser);

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2,loginPassword);

	//Guarda hora final
	testEndHour =  CustomKeywords.'com.utils.ReportHelper.getHours'();
	testResultData.put(5,testEndHour);

	//Guarda Resultado de la prueba
	testResultData.put(9,testStatus);


	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10,testResultDescription);

	//Guarda resultado de prueba
	if(GlobalVariable.QaIndividualLog){
	 //  CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)
	}
	//toma screenshot en caso de error
	if(errorEnLaPrueba == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation,testcaseId)
	}

	//Cierra el navegador si la prueba se ejecuto de forma individual y en caso de que no haya errroes en la prueba
	if(!errorEnLaPrueba){
		if (GlobalVariable.individualTestCase == true) {
			WebUI.closeBrowser()
		}
	}
}

