import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepFailedException

import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObjectProperty
import bminc.eu.exceptions.LoginException
import internal.GlobalVariable as GlobalVariable
import java.lang.AssertionError;
import com.kms.katalon.core.testobject.ConditionType
KeywordLogger log = new KeywordLogger()
import com.kms.katalon.core.testobject.TestObject as TestObject
//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.permisosExcelRutaCasino)
//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl

CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'('Casino')

String url = GlobalVariable.pregameUrl;

String userName = GlobalVariable.customerPIN;

String userPassword = GlobalVariable.customerPassword;

String startHour = CustomKeywords.'com.utils.ReportHelper.getHours'()

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.ReportHelper.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 5, startHour)

final String FAILED_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getFailedStatus'();

final String SUCCESS_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getsuccessStatus'();

final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'();

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

TestObject enlaceCasinoObj = null;
try{
	if (!(GlobalVariable.usuarioLogeado)) {
		WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'), [('url') : url, ('loginUser') : userName, ('loginPassword') : userPassword],
		FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.usuarioLogeado = true;
	}

	//Guarda Sistema operativo
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'())

	//Guarda Resoluci?n
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'())

	//Guarda Version del browser
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())

	String permiso = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'('Enloace casino Obj', textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType, "a.dropdown-toggle .mainMenuLi div"),4);

	assert permiso != null && !permiso.isEmpty();

	//Guarda permito otorgado
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, permiso)

	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, SUCCESS_STATUS)

	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Permiso Casino es correctamente accesible para el usuario')


}catch(LoginException ex){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, FAILED_STATUS)
	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validaci\u00f3n de permiso fallida por error en test case de Login error: ')
	throw  new AssertionError('Error en la prueba casino debido a que no se ingreso al sitio correctamente',ex);
}catch(StepFailedException step){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, FAILED_STATUS)
	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validaci\u00f3n de permiso fallida por incumplimiento de verifaicaci\u00f3n de elementos en la p\u00e1gina ')
	throw  new AssertionError('Error en la prueba casino debido a que hay un paso que no se cumplio',step);

}catch(Exception ex){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, FAILED_STATUS)
	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validaci\u00f3n de permiso fallida causado por excepci\u00f3n inesperada')
	throw  new AssertionError('Error en la prueba casino ',ex);
}finally{

	//Guarda url o dirrecion del sitio según el ambiente
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,1,url);

	//Guarda pin del jugador que se usó para la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,2,userName);

	//Guarda password del jugador que se usó para la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1,3,userPassword);

	//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, CustomKeywords.'com.utils.ReportHelper.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.ReportHelper.getDate'())

	//Cierra archivo de lectura
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()

	//Abre  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.loadFileOutputStream'(GlobalVariable.permisosExcelRutaCasino)

	//escribe informacion en la hoja del exec
	CustomKeywords.'com.utils.ExcelsUtils.writeOutputExcelSheet'()

	//Cierra  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
}
