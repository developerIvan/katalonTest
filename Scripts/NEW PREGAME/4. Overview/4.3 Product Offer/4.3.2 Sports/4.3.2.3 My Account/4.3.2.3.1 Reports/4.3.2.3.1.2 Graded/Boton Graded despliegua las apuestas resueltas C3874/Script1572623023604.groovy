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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import org.openqa.selenium.WebElement as WebElement;

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3874'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String customerWagers = ''

List<String> gradedWagersFromCM = new ArrayList<Integer>()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

try{
	
	//Carga las apuesta pendientes de customer maintenace
	gradedWagersFromCM = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/CargarApuestasDeCustomerMaintenance'),
		[('tipoDeApuesta') : 'GRADED', ('customerPin') : customerPin, ('gradedDaysInterval') :  gradedDaysInterval], FailureHandling.STOP_ON_FAILURE)
	
	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()
	
	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()
	
	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()
	
	WebUI.verifyNotEqual(gradedWagersFromCM.size(), 0)
	
	//Valida precondicion que botón grade sea visible
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/4.3.2.3.1.2 Graded/Boton Graded sea desplegado correctamente C3870'),
		[('url') : url, ('customerPIN') : customerPin, ('customerPassword') : customerPass], FailureHandling.STOP_ON_FAILURE)
	
	//Guarda Version del browser
	testResultData.put(7, browserVersion)
	
	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)
	
	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)
	
	//Se preisona el botón graded
	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.3 MY ACCOUNT/4.3.2.3.1 Reports/button_Graded'))
	
	//Espero a que pantalla de carga no sea visible
	WebUI.waitForElementNotVisible(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.1 Account Information/4.3.2.3 MY ACCOUNT/4.3.2.3.1 Reports/div_Overlay'),
		4)
	
	List<String> tiquetesDepregame = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'('CsS', 'div.wpr_headerWagerDiv div:nth-child(3)',
		3)

	for (WebElement tiquetePregame : tiquetesDepregame) {
		String tiquete = tiquetePregame.getAttribute('innerText') != null ? tiquetePregame.getAttribute('innerText').toString().replace(
			' ', '').trim() : ''
	
		assert gradedWagersFromCM.contains(tiquete)
	
		customerWagers = customerWagers.concat(tiquete)
	
		customerWagers = customerWagers.concat(',')
	}
	
	testStatus = 'Exitoso'
	
	testResultDescription = 'Las apuestas resueltas '+customerWagers.substring(0, customerWagers.length()-1)+ ' del jugador '+customerPin+' aparecen exitosamente en pregame'
}/*catch (StepFailedException stepE) {
	String errorCode = '-09'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)


	testResultDescription = 'La prueba resulto fallida porque el jugador de prueba no tiene apuestas resueltas en customer maintenance, o algún componente que no pudó ser localizado. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode);

	throw  stepE
}*/catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	testResultDescription = 'Las apuestas graded de customer maintenance no se reflejan correctamente en pregame o no aparecen en la sección de apuestas gradideas. '

	throw asserError
}/*catch(Exception e){
	String errorCode = '-99'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = 'La prueba resulto fallida por algún fallo anomalo. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode);

	throw e
}*/finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, customerPin)

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
//	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}




