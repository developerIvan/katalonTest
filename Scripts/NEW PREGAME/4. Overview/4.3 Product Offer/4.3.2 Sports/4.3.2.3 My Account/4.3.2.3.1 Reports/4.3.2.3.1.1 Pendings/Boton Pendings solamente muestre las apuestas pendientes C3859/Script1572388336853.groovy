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
import java.util.List as List
import java.util.ArrayList as ArrayList
import org.openqa.selenium.WebElement as WebElement;
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
import com.kms.katalon.core.exception.StepFailedException as StepFailedException

String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3859'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

String customerWagers = '';
//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

List<String> pendingWagersFromCM = new ArrayList<Integer>()
try{
	//Carga las apuestas pendientes de custoemr maintenance
	pendingWagersFromCM = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/CargarPrecondicionesDeCustomerMaintenance'),
			[('tipoDeApuesta') : 'PENDING'], FailureHandling.STOP_ON_FAILURE)


	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	WebUI.verifyNotEqual(pendingWagersFromCM.size(), 0)
	//ir apregame y seleccioanr el botón account
	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/4.3.2.3.1.1 Pendings/Boton Pendings sea desplegado correctamente C3858'),
			[('url') : url, ('customerLogin') : customerId, ('customerPass') : password], FailureHandling.STOP_ON_FAILURE)

	//Click en boton de graded
	WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_Graded'), 2)

	WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_Graded'))

	//Se verifica que las apuestas pendientes no sea visible
	WebUI.delay(1)

	for(String ticketWagerCM:pendingWagersFromCM){

		//Se remueve el guion para facilitar la valdiaci☻n de elementos de la pagina. Esto se debe a que el formato de los tiquetes de cm y pregame son diferentes
		String ticketWithoutQuote =ticketWagerCM.substring(0, ticketWagerCM.indexOf("-"))
		println "tiquete formateado: "+ticketWithoutQuote

		//Se valida que en la secci~n de apuestas graideadas no aparescan las apuestas pendientes
		boolean ticketNoEsVisiblenGraded =  CustomKeywords.'com.utils.AutomationUtils.verifyTestObjectIsNotPresent'("Pending Tickt N0:"+ticketWithoutQuote, "XPATH", "//div[contains(text(),'"+ticketWagerCM+"')]" , 3);

		assert ticketNoEsVisiblenGraded == true;
		//Valida que aparescan los tiquetes de las apuestas en customer maitnenance
	}

	//Se presiona el botón de 'Pending'
	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_Pendings'))

	List<WebElement> tiquetesDepregame = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'("CsS","div.wpr_headerWagerDiv div:nth-child(3)")

	//Verifa que en regame el boton contenga las apuestas pendeitnes
	assert tiquetesDepregame.size() == pendingWagersFromCM.size();

	for(WebElement tiquetePregame:tiquetesDepregame){
		String tiquete = tiquetePregame.getAttribute("innerText")!=null?tiquetePregame.getAttribute("innerText").toString().replace(" ", "").trim():"";
		assert pendingWagersFromCM.contains(tiquete);
		customerWagers =	customerWagers.concat(tiquete)
		customerWagers =	customerWagers.concat(",")
	}

	testStatus = 'Exitoso'

	testResultDescription = 'Las apuestas '+customerWagers.substring(0, customerWagers.length()-1)+'  del jugador '+customerId+ ' aparecen correctamente en la sección Pensdings de reprotes '
}catch (StepFailedException stepE) {
	String errorCode = '-09'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, stepE)


	testResultDescription = 'La prueba resulto fallida porque el jugador de prueba no tiene apuestas pendientes en customer maintenance, o algún componente que no pudó ser localizado. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForStepExceptions'(errorCode);

	throw  stepE
}catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true


	testResultDescription = 'Las apuestas pendientes de customer maintenance no se reflejan correctamente en pregame o aparecen en la sección de apuestas gradideas. '

	throw new AssertionError('Prueba fallida', asserError)
}catch(Exception e){
	String errorCode = '-99'

	errorEnLaPrueba = true

	KeywordLogger.getInstance(this.class).logger.error(errorCode, e)

	testResultDescription = 'La prueba resulto fallida por algún fallo anomalo. '+CustomKeywords.'com.utils.ConstantsUtil.getCustomErrorMessageForGeneralExceptions'(errorCode);

	throw e
}finally {
	//Guarda url o dirrecion del sitio según el ambiente
	testResultData.put(0, url)

	//Guarda pin del jugador que se usó para la prueba
	testResultData.put(1, customerId)

	//Guarda password del jugador que se usó para la prueba
	testResultData.put(2, password)

	//Guarda hora final
	testEndHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

	testResultData.put(5, testEndHour)

	//Guarda Resultado de la prueba
	testResultData.put(9, testStatus)

	//GuardaDescrpipción del  Resultado de la prueba
	testResultData.put(10, testResultDescription)

	//Guarda resultado de prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}

