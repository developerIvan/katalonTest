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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import java.util.List as List
import org.openqa.selenium.WebElement as WebElement
String testEndHour = ''

String browserVersion = ''

String screenResolution = ''

String OsName = ''

String testStatus = 'Fallido'

String testResultDescription = ''

ArrayList<Integer> rows = new ArrayList<Integer>()

rows.add(1)

HashMap<Integer, String> testResultData = new HashMap<Integer, String>()

String testcaseId = 'C3861'

String actualErrorMessage = ''

boolean errorEnLaPrueba = false

String testStartDate = CustomKeywords.'com.utils.ReportHelper.getDate'()

String testStartHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
testResultData.put(3, testStartDate)

//Registro  hora  incio de la prueba
testResultData.put(4, testStartHour)

try {
	//Precondiciones de la prueba
	//Verifica si hay apuestas pendientes
	List<String> tiquetesDeCustomerMaintenace = new ArrayList<String>();

	tiquetesDeCustomerMaintenace =  WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/CargarPrecondicionesDeCustomerMaintenance'),
			[('casoDePrueba') : testcaseId], FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.1 Reports/Boton Reportes sea desplegado correctamente C3860'),
			[('url') : GlobalVariable.pregameUrl, ('customerPIN') : GlobalVariable.customerPIN, ('customerPass') : GlobalVariable.customerPassword],
			FailureHandling.STOP_ON_FAILURE)

	OsName = CustomKeywords.'com.utils.ReportHelper.getOperatingSystem'()

	browserVersion = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

	screenResolution = CustomKeywords.'com.utils.ReportHelper.getScreenResolution'()

	//Guarda Version del browser
	testResultData.put(7, browserVersion)

	//Guarda Version del sistema operativo
	testResultData.put(6, OsName)

	//Guarda resolucion de pantalla
	testResultData.put(8, screenResolution)

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_REPORTS'))



	String pendingButtonName = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/MY ACCOUNT/Reports/button_Pendings'),
			'className', FailureHandling.STOP_ON_FAILURE)


	assert pendingButtonName != null && pendingButtonName.contains("btn-success");

	testResultDescription = "El botón pending aparece seleccionado de forma predeterminada correctamente";
    List<WebElement> tiquetesDepregame = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'("CsS","div.wpr_headerWagerDiv div:nth-child(3)")
	
	
	//Valida que aparescan los tiquetes de las apuestas en customer maitnenance
	if(tiquetesDeCustomerMaintenace.size()>0){
		
		//Verifa que en regame el boton contenga las apuestas pendeitnes
		assert tiquetesDepregame.size() == tiquetesDeCustomerMaintenace.size();
		

		    for(WebElement tiquetePregame:tiquetesDepregame){
				String tiquete = tiquetePregame.getAttribute("innerText")!=null?tiquetePregame.getAttribute("innerText").toString().replace(" ", "").trim():"";
			    assert tiquetesDeCustomerMaintenace.contains(tiquete);
				
			}
			testResultDescription.concat("Y las apuestas pendietnes  de customer maintenance aparecen correctamente")
			
	}

	testStatus = 'Exitoso'

	testResultDescription = 'Los botones graded y pending de la sección de  reportes son visibles'
}catch (StepFailedException stepE) {
	String errorCode = '-09'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + stepE.getMessage())

	testResultDescription = 'La prueba resulto fallida por algún componente que no pudó ser localizado. Favor revisar el log de katalon con el código: '+errorCode

	throw new StepFailedException('El botón graded o pending  no esta visible', stepE)
}
catch (AssertionError asserError) {
	String errorCode = '-10'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + asserError.getMessage())

	testResultDescription = 'El botón  pending  no está seleccionado de forma predeterminada al presionar el botón account o, las apuestas pendientes de customer maintenance no se reflejan correctamente en pregame. Si se desea, se puede revisar en el log dfe katalon con el código '+errorCode

	throw new AssertionError('Prueba fallida', asserError)
}
catch (Exception e) {
	String errorCode = '-99'

	errorEnLaPrueba = true

	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + e.getMessage())

	testResultDescription = 'La prueba resulto fallida por algún fallo anomalo. Favor revisar los log o bitacoras de katalon con el código '+errorCode

	throw e
}
finally {
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
	CustomKeywords.'com.utils.ExcelsUtils.saveTestResult'(GlobalVariable.excelReportFileLocation, testcaseId, rows, testResultData)

	//toma screenshot en caso de error
	if (errorEnLaPrueba == true) {
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(GlobalVariable.screenshotLocation, testcaseId)
	}
}

