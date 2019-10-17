import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import bminc.eu.exceptions.LoginException
import internal.GlobalVariable as GlobalVariable
WebUI.openBrowser('')

WebUI.navigateToUrl(url)

WebUI.maximizeWindow()
String OsName = CustomKeywords.'com.utils.ReportHelper.getBrowserAndVersion'()

String testcaseId = 'C3783';

boolean tomarInstantanea=false;
//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.excelReportFileLocation)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'(testcaseId)

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 1, CustomKeywords.'com.utils.ReportHelper.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 2, CustomKeywords.'com.utils.ReportHelper.getHours'())

//Guarda Version del browser
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())

//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, OsName)

//Guarda Version del sistema operativo
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'())


TestObject loginButton = new TestObject("BotonInexistente");
try {
	TestObject loginButton = CustomKeywords.'com.utils.AutomationUtils.findTestObject'("login", "css", "#logIn", 2);

	assert !loginButton.equals(CustomKeywords.'com.utils.AutomationUtils.getNullObject'());

	GlobalVariable.pregameSiteEsVisible = true;
	
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, 'Exitoso')
	
		CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, 'El botón login debería es visibler')
	
} catch(java.lang.AssertionError asserError){
	String errorCode = '-10'
	tomarInstantanea = true;
	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + asserError.getMessage())

	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, 'Fallido')

	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, 'El botón login debería ser visible, pero actualmente no lo es. lo cual indica que, o el botón desaparecio  o fue modificado, lo cual cuasa que la prueba automatizada no lo pueda encontrar')

	throw asserError;
} catch(Exception e){
	String errorCode = '-99'
	tomarInstantanea = true;
	KeywordUtil.logger.logError((('Error code: ' + errorCode) + ' error message :') + e.getMessage())

	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, 'Fallido')

	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, 'El botón login debería ser visible, pero actualmente no lo es debido a un comportanmiento anomalo')

	throw e;}
finally{
	//Cierra el navegador si la prueba se ejecuto de forma individual
	if (GlobalVariable.individualTestCase == true) {
		WebUI.closeBrowser()
	}

	//toma screenshot en caso de error
	if(tomarInstantanea == true){
		CustomKeywords.'com.utils.AutomationUtils.createSnapshop'(testcaseId)
	}

	//Guarda url o dirrecion del sitio según el ambiente
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 0, url)

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

return loginButton;