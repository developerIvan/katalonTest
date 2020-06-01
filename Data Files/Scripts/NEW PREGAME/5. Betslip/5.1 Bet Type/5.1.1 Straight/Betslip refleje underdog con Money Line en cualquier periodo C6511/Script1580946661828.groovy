import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>(1)

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>(11)

String testcaseId = 'C6511'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

String failedTestCaseMessage = ('Caso de prueba ' + testcaseId) + ' Fallido'

String expectedMatch = "";

String expectedTeam = ''

String expectedBetDesc = ''

String expectedLineDesc = ''

String actualTeam = ''

String actualBetDesc = ''

String actualLineDesc = ''
String betslipExpectedResult = "";

String betslipActualResult = "";

String period = periodNumber
try {
	//login pregame
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.1 Validacion Deportes En Menu Vs CM Restricciones/4.3.1.1 Validacion de acceso a pregame/Usuario tiene permiso Sportbook(C3900)'),
		[('url') : url, ('userName') : customerPIN, ('userPassword') : customerPass], FailureHandling.STOP_ON_FAILURE)

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	//Seleciona el tipo de apuesta
	WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/SeleccionarBotonDeTipoDeApuesta'),
		[('wagerType') : 'Straight'], FailureHandling.STOP_ON_FAILURE)
	
	//Selecciona el deporte
	WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/SeleccionarDeporte'), [('sportId') : sportId],
		FailureHandling.STOP_ON_FAILURE)

	//Selecciona el subdeporte
	WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/SeleccionarSubDeporte'), [
			('sportName') : sportId, ('subSportName') : subSporId], FailureHandling.STOP_ON_FAILURE)

		//Se selecciona el peridodo
	WebElement periodButton = WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/SeleccionarPeriodoDelJuego'),
		[('sportName') : sportId, ('subSportName') : subSporId, ('leagueName') : tournament,('periodNumber'):period],
		FailureHandling.STOP_ON_FAILURE)

	
	if(!periodButton.isDisplayed()){
		
		//Selecciona el banner de la liga
		WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/SeleccionarLiga'), [('sportName') : sportId
			, ('subDeportName') : subSporId, ('leagueName') : tournament,('period'):period], FailureHandling.STOP_ON_FAILURE)
	}
	
	String periodButtonClass =periodButton.getAttribute("class");
	
	//Si el botón del periodo no esta presionado, se hará para que muestre las líneas
	if ((periodButtonClass != null) && !(periodButtonClass.contains('btn-success'))) {
		periodButton.click();
	}
	
	//Selecciono la línea
	WebElement selectedLineButton = WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/SeleccionarLineaAlt'), [('betType') : 'M'
		, ('selectedTeam') : underdogTeam, ('tournament') : tournament, ('period') : period], FailureHandling.STOP_ON_FAILURE)
	
	selectedLineButton.click();
	//Se lee la información esperada (M<tach,Periodo,Bet type, Selectiony Line
	String gameNum = selectedLineButton.getAttribute("gamenum");
	
	   expectedMatch = WebUI.callTestCase(findTestCase('NEW PREGAME/5. Betslip/5.1 Bet Type/Funciones Auxiliares/DefinirMatchExperado'), [('gameNum') : gameNum],
	FailureHandling.STOP_ON_FAILURE)

	expectedLineDesc = selectedLineButton.findElement(By.cssSelector('div')).getAttribute('innerText')

	expectedLineDesc = expectedLineDesc.trim().replace(' ', '').replace('\n', '')

	//Se prepara la información esperada (M<tach,Periodo,Bet type, Selectiony Line etc) de la línea seleccioanda
	betslipExpectedResult = CustomKeywords.'com.utils.BetSlipUtils.returnWagerGeneralInfo'(expectedMatch, selectedLineButton.getAttribute(
			'groupdescription'), selectedLineButton.getAttribute('betdescription'), selectedLineButton.getAttribute('chosenteamid'),
		expectedLineDesc)
	
	CustomKeywords.'com.utils.AutomationUtils.scrollToTopOfThePage'() ;
	
	betslipExpectedResult = CustomKeywords.'com.utils.BetSlipUtils.returnWagerGeneralInfo'(expectedMatch, selectedLineButton.getAttribute('groupdescription') , selectedLineButton.getAttribute('betdescription'), selectedLineButton.getAttribute('chosenteamid'), expectedLineDesc)

	 betslipActualResult = CustomKeywords.'com.utils.BetSlipUtils.returnWagerGeneralInfo'(gameNum,period);

	 String odds = selectedLineButton.getAttribute("us");
	  
	
	 
	assert betslipExpectedResult.contains(betslipActualResult)
	
	testStatus = 'Exitoso'
	
	testResultDescription = 'La siguiente información de la línea   '+betslipExpectedResult+'  con spread negative odds se refleja correctamente en el betslip'
	
}catch (AssertionError asserError) {
	String errorCode = testcaseId.concat('-10 ')

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)

	testResultDescription = 'La información esperada del betslip era esta: '+betslipExpectedResult+ ' pero actualmente es: '+betslipActualResult + ' o el odd del equipo seleccionado es positivo cuando debería ser negativo'

	throw new StepFailedException(errorCode.concat(failedTestCaseMessage), asserError)
}finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, customerPIN)

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2, customerPass)

	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(5, testEndHour)

	//Guarda Resultado de la prueba
	testResultData.put(9, testStatus)

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10, testResultDescription)

	//Guarda resultado de prueba

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.resetGlobalVariables'()

		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}