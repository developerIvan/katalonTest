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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.lang.AssertionError;
import com.kms.katalon.core.exception.StepFailedException;
KeywordLogger log = new KeywordLogger()

//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.permisosExcelRuta)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'('Live')

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

	WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Permisos/Live/div_En Vivo'))

	String permiso = WebUI.getAttribute(findTestObject('Repositorio Sportbook/Permisos/Live/div_En Vivo'), 'textContent')

	//Guarda permito otorgado
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, permiso)

	//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, CustomKeywords.'com.utils.DateUtil.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.DateUtil.getDate'())

	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.PASSED)

	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Permiso Live es correctamente accesible para el usuario')

}catch(StepFailedException step){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)
	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13,  'Validacion de permiso fallida por incumplimiento de verifaicación de elementos en la página')
	throw  new AssertionError('Error en la prueba live debido a que hay un paso que no se cumplio',step);

}catch(Exception ex){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)
	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validacion de permiso fallida causado por excepción inesperada')
	throw  new AssertionError('Error en la prueba live ',ex);
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

