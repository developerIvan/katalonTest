import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

TestObject popUpWinCristmasAdvice = new TestObject('Cristmas Advice')

WebUI.callTestCase(findTestCase('NEW PREGAME/1. Site/1.1 Validacion del tipode sitio Classic Premium/Usuario visualice el boton LOGIN correctamente (C3783)'),
	[('url') : GlobalVariable.pregameUrl], FailureHandling.STOP_ON_FAILURE)

popUpWinCristmasAdvice.setSelectorMethod(SelectorMethod.CSS)

popUpWinCristmasAdvice.setSelectorValue(SelectorMethod.CSS, 'h4#exampleModalLabel')



WebUI.waitForElementVisible(popUpWinCristmasAdvice, 2, FailureHandling.OPTIONAL)

String atribute = WebUI.getAttribute(popUpWinCristmasAdvice, 'innerText')

if ((null != atribute) && atribute.equals('Attention Customer')) {
    //
    TestObject buttonCloseNotification = new TestObject('button close')

    buttonCloseNotification.setSelectorMethod(SelectorMethod.CSS)

    buttonCloseNotification.setSelectorValue(SelectorMethod.CSS, '#cerrarModalNotifi')

    WebUI.click(buttonCloseNotification)

    WebUI.waitForElementNotVisible(popUpWinCristmasAdvice, 2)

    WebUI.verifyElementNotVisible(popUpWinCristmasAdvice)
}

WebUI.refresh()

WebUI.verifyElementNotVisible(popUpWinCristmasAdvice)



