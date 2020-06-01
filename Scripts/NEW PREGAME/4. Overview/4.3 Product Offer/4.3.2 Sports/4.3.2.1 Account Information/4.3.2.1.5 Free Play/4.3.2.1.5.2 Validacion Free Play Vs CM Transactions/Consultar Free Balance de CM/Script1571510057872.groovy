import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
//This is to write to the log file
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

String value = "";

WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/Ir a CustomerMaintenance'), [('customerId') : PlayerPin], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_7cat'))

value = WebUI.getAttribute(findTestObject('Repositorio Objetos Customer Maintenance/input_Makeup Figure_7cat'), 'value')

return value;



