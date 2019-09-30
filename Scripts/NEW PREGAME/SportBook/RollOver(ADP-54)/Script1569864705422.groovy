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

if (GlobalVariable.individualTestCase == true) {
    WebUI.callTestCase(findTestCase('NEW PREGAME/SportBook/MenuMiCuenta(ADP-50)'), [('excelLabelFileLoc') : 'C:\\\\Users\\\\jmatamoros.BMINC\\\\Documents\\\\Qa\\\\Functional Testing\\\\TestData\\\\PregameLanguageLabels.xlsx'], 
        FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_ROLL OVER'))

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_From'), 0)

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/button_Submit'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/div_Hour'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/input_date_From'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/input_endDate'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/input_Hour_endTime'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/input_hour_initTime'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Contest'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_From'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Horses'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Hour'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_If Bet'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Live'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Parlay'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Straight'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Teaser'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_To'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/label_Total Rollover'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/span_From_Date_icon'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/RollOver(ADP-54)/span_To_Date_icon'))

