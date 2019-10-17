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



//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.excelReportFileLocation)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'('C6414')

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 3, CustomKeywords.'com.utils.ReportHelper.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.ReportHelper.getHours'())


WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'),
	[('url') : url], FailureHandling.STOP_ON_FAILURE)

WebUI.maximizeWindow();

CharSequence BrowserNameVersion = CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'()

String ScreenResolution = CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'()

//Guarda Version del browser
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())


//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7,OsName)


//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, ScreenResolution)

ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

String XPATH_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getXPathSelectorId'()

try {
    WebUI.delay(2)

    TestObject loginButton = CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Login Button ', new TestObjectProperty(
        XPATH_SELECTOR, equalsCondType, 'id(\'logIn\')'), 2)

    TestObject userPinInput = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('input_Bienvenido_user ', new TestObjectProperty(
        XPATH_SELECTOR, equalsCondType, 'id(\'user\')'), 4)

    TestObject userPasswordInput = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('input_Bienvenido_password ', 
        new TestObjectProperty(XPATH_SELECTOR, equalsCondType, 'id(\'password\')'), 4)

    WebUI.waitForElementVisible(userPinInput, 4)

    WebUI.sendKeys(userPinInput, loginUser)

    WebUI.sendKeys(userPasswordInput, loginPassword)

    //click para entrar al sitio
    CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, ' Button Entrar ', new TestObjectProperty(CSS_SELECTOR, 
        equalsCondType, '#btnEntrar'), 4)

   String overviewButton =  CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/Overview Button'), "innerText", 2);
   
   WebUI.waitForElementPresent(findTestObject('Repositorio Objetos Proyecto Premium/Page_Sportbook/Overview Button'), 0)
   
   assert null != overviewButton
   
   //Result passed
   //Guara estado de la prueba
   CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Exitoso")
   
   CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El jugador logró ingresar al Overwiew")
   
}
catch (com.kms.katalon.core.exception.StepFailedException stepE) {
    String errorCode = '-01'

    KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + stepE.getMessage())

	
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")
	
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El jugador no logró ingresar al Overwiew debido a un paso de la prueba o elmento de la página que no está visible. Favor revisar el log de katalon")
	
    throw new LoginException('Paso de la prueba login no completado', stepE, errorCode)
} 
catch (Exception e) {
    String errorCode = '-99'

    KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + e.getMessage())
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, "Fallido")
	
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, "El jugador no logró ingresar al Overwiew debido a un error anomalo en la prueba. Favor revisar los log ao bitacoras de katalon")
	
    throw new LoginException('Login Test Case fallido', e, errorCode)
}finally{
         //Cierra el navegador si la prueba se ejecuto de forma individual
		if(GlobalVariable.individualTestCase == true){
			WebUI.closeBrowser();
		}
		
		//Guarda url o dirrecion del sitio según el ambiente
		CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,0,url);
	
		//Guarda pin del jugador que se usó para la prueba
	   CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,1,loginUser);
	
	   //Guarda password del jugador que se usó para la prueba
	   CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,2,loginPassword);
		
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