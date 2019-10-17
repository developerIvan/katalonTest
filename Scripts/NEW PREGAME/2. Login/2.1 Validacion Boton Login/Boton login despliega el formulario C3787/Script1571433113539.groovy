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

String OsName = CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'()

SimpleDateFormat format = new SimpleDateFormat('dd/MM/YYYY')

SimpleDateFormat timeFormat = new SimpleDateFormat('H:mm:ss')

String testcaseId = 'C3802'

String expectedUserInputBackgroundText = findTestData('TestData/Datos de Entrada/1.1 Validacion del tipode sitio Classic Premium').getValue(
    1, 1)

String actualUserInputBackGroundTxt = '';
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

//Indica si tomar un snapshot o instantanea en caso de error
boolean tomarInstantanea = false

TestObject lobihButton = WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'), 
    [('url') : GlobalVariable.pregameUrl], FailureHandling.STOP_ON_FAILURE)

WebUI.click(lobihButton)

WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), 2)

actualUserInputBackGroundTxt = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/input_Welcome Back_user'), 
    'placeholder')

assert expectedUserInputBackgroundText.equals(actualUserInputBackGroundTxt)

