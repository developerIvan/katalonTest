import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.logging.KeywordLogger
import internal.GlobalVariable as GlobalVariable
import java.lang.AssertionError;
import com.kms.katalon.core.exception.StepFailedException;
KeywordLogger log = new KeywordLogger()
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty

//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.permisosExcelRutaSportbook)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'('Sportbook')

String url = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 1)

String userName = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 2)

String userPassword = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 3)

String startHour = CustomKeywords.'com.utils.DateUtil.getHours'()

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.DateUtil.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 5, startHour)

final String FAILED_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getFailedStatus'();

final String SUCCESS_STATUS = CustomKeywords.'com.utils.ConstantsUtil.getsuccessStatus'();

final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'();

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

try{
	if (!(GlobalVariable.usuarioLogeado)) {
		WebUI.callTestCase(findTestCase('NEW PREGAME/SportBook/Login_Successfull'), [('url') : url, ('loginUser') : userName, ('loginPassword') : userPassword],
		FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.usuarioLogeado = true;
	}

	//Guarda Sistema operativo
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'())

	//Guarda Resoluci?n
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'())

	//Guarda Version del browser
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())

	//WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Permisos/Sportbook/div_Sports'))

	String permisoLive = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'('Enlace live Obj', textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType, "#live_link .mainMenuLi div"),4);

	assert !permisoLive.equals(null)&&!permisoLive.isEmpty();
	//Guarda permito otorgado
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, permisoLive)

	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, SUCCESS_STATUS)

	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Permiso Sportbook es correctamente accesible para el usuario')

}catch(StepFailedException step){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, FAILED_STATUS)
	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13,  'Validaci\u00f3n de permiso fallida por incumplimiento de verifaicaci\u00f3n de elementos en la p\u00e1gina')
	throw  new AssertionError('Error en la prueba Sportbook debido a que hay un paso que no se cumplio',step);

}catch(Exception ex){
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, FAILED_STATUS)
	//Guara descripci?n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validaci\u00f3n de permiso fallida causado por excepci\u00f3n inesperada')
	throw  new AssertionError('Error en la prueba Sporbbok ',ex);
}finally{
	//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, CustomKeywords.'com.utils.DateUtil.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.DateUtil.getDate'())

	//Cierra archivo de lectura
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()

	//Abre  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.loadFileOutputStream'(GlobalVariable.permisosExcelRutaSportbook)

	//escribe informacion en la hoja del exec
	CustomKeywords.'com.utils.ExcelsUtils.writeOutputExcelSheet'()

	//Cierra  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
}
