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
import java.util.Calendar as Calendar
import java.util.Date as Date
import java.text.SimpleDateFormat as SimpleDateFormat

String OsName = CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'()

SimpleDateFormat format = new SimpleDateFormat('dd/MM/YYYY')

SimpleDateFormat timeFormat = new SimpleDateFormat('H:mm:ss')

String expectedErrorMesage = GlobalVariable.mensajeUsuarioInvalido;

String testcaseId = 'C3810';

String actualErrorMessage = '';

//Indica si tomar un snapshot o instantanea en caso de error
boolean tomarInstantanea = false;

//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.excelReportFileLocation)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'(testcaseId)

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 3, CustomKeywords.'com.utils.ReportHelper.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.ReportHelper.getHours'())

if(GlobalVariable.pregameSiteEsVisible == false){
	WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'), 
	    [('url') : url], FailureHandling.STOP_ON_FAILURE)
}
WebUI.maximizeWindow()

CharSequence BrowserNameVersion = CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'()

String ScreenResolution = CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'()

//Guarda Version del browser
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())

//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, OsName)

//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, ScreenResolution)

try {
    WebUI.click(findTestObject('Repositorio Objetos Proyecto Premium/a_Login'))

    WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), 2)

	
	def loginResult = WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/ScriptAuxiliares/CargarMensajdeDeErrordeIngreso'),
		[('userPin') : pinInvalido, ('userPass') : loginPassword], FailureHandling.STOP_ON_FAILURE)
	loginUser = loginResult.userId;
	
	loginPassword = loginResult.password;
	
	actualErrorMessage =loginResult.errorMgs;

	assert expectedErrorMesage.equalsIgnoreCase(actualErrorMessage)
	
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, 'Exitoso')
	
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El sistema no permitio que el usuario ingresará al sitio sin definir el pin de forma exitosa. El esperado mensaje de error '"+expectedErrorMesage+"' es desplegado existosamente")
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = '-01'

	tomarInstantanea = true;
	
    KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + stepE.getMessage())

    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, 'Fallido')

    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, 'El sistema no pudo validar que el usuario no pueda rentrar al sitio si su pin no es ingresado  debido a que el mesaje de error no es el esperado o algún  elmento esperado  de la página no está visible. Favor revisar el log de katalon')

    throw new LoginException('Paso de la prueba login no completado', stepE, errorCode)
}catch(java.lang.AssertionError asserError){
    String errorCode = '-10'
	
	tomarInstantanea = true;
	
	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + asserError.getMessage())

	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, 'Fallido')

	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, 'El mensaje de error esperado debería ser '+expectedErrorMesage+" pero actualmente es: "+actualErrorMessage)
 
	throw new LoginException('Validación de pin invalido fallida', asserError, errorCode)
} catch (Exception e) {
    String errorCode = '-99'

	tomarInstantanea = true;
	
    KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + e.getMessage())

    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, 'Fallido')

    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, 'El sistema no pudo validar que el usuario no pueda rentrar al sitio si su pin no es ingresado  debido a un error anomalo en la prueba. Favor revisar los log ao bitacoras de katalon')

    throw new LoginException('Login Test Case fallido', e, errorCode)
} 
finally { 
	
	//toma screenshot en caso de error
	if(tomarInstantanea == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(testcaseId)
	 }
	
    //Cierra el navegador si la prueba se ejecuto de forma individual
    if (GlobalVariable.individualTestCase == true) {
        WebUI.closeBrowser()
    }
   
    //Guarda url o dirrecion del sitio según el ambiente
    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 0, url)

    //Guarda pin del jugador que se usó para la prueba
    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 1, loginUser)

    //Guarda password del jugador que se usó para la prueba
    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 2, loginPassword)

    //Guarda hora final
    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.ReportHelper.getHours'())

    //Guarda fecha final
    CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 5, CustomKeywords.'com.utils.ReportHelper.getDate'())

    //Cierra archivo de lectura para permitir la escritura
    CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()

    //Abre  archivo de escritua
    CustomKeywords.'com.utils.ExcelsUtils.loadFileOutputStream'(GlobalVariable.excelReportFileLocation)

    //escribe informacion en la hoja del exec
    CustomKeywords.'com.utils.ExcelsUtils.writeOutputExcelSheet'()

    //Cierra  archivo de escritua
    CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
}

