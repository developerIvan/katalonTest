import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepFailedException

import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import bminc.eu.exceptions.LoginException
import internal.GlobalVariable as GlobalVariable
import java.lang.AssertionError;

KeywordLogger log = new KeywordLogger()

//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.permisosExcelRuta)
//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl

CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'('Casino')

String url = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 1)

String userName = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 2)

String userPassword = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 3)

String startHour = CustomKeywords.'com.utils.DateUtil.getHours'()

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.DateUtil.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 5, startHour)


try{
	if (!(GlobalVariable.usuarioLogeado)) {
		WebUI.callTestCase(findTestCase('PREGAME/SportBook/Login_Successfull'), [('url') : url, ('loginUser') : userName, ('loginPassword') : userPassword],
		FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.usuarioLogeado = true;
	}

	//Guarda Sistema operativo
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'())
	
	//Guarda Resoluci�n
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'())
	
	//Guarda Version del browser
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())


	WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Permisos/Casino/div_Casino'))

	String permiso = WebUI.getAttribute(findTestObject('Repositorio Sportbook/Permisos/Casino/div_Casino'), 'textContent')

	//Guarda permito otorgado
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, permiso)

	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.PASSED)

	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Permiso Casino es correctamente accesible para el usuario')
	
	
}catch(LoginException ex){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)
	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validacion de permiso fallida por error en test case de Login error: ')
	throw  new AssertionError('Error en la prueba casino debido a que no se ingreso al sitio correctamente',ex);
}catch(StepFailedException step){
//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)
	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validacion de permiso fallida por incumplimiento de verifaicación de elementos en la página ')
throw  new AssertionError('Error en la prueba casino debido a que hay un paso que no se cumplio',step);

}catch(Exception ex){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)
	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validacion de permiso fallida causado por excepción inesperada')
	throw  new AssertionError('Error en la prueba casino ',ex);
}finally{
	//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, CustomKeywords.'com.utils.DateUtil.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.DateUtil.getDate'())

	//Cierra archivo de lectura
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()

	//Abre  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.loadFileOutputStream'(GlobalVariable.permisosExcelRuta)

	//escribe informacion en la hoja del exec
	CustomKeywords.'com.utils.ExcelsUtils.writeOutputExcelSheet'()

	//Cierra  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
}
