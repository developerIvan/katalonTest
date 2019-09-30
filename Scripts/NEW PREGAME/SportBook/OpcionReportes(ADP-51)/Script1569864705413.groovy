import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

String dynamicId = '.accordion-body div span b'

TestObject reportDetail = new TestObject('reportDetail')

reportDetail.addProperty('css', ConditionType.EQUALS, dynamicId)

TestObject wagerGradedDetail = new TestObject('wagerGradedDetail')

TestObject wagerDesc = new TestObject('wagerDesc')

TestObject wagerStatus = new TestObject('wagerStatus');

TestObject wagerResultAmountObj = new TestObject("wagerResultAmountObj");

String wagerDescContest = ''

String wagerStatusDesc = '';

String wagerAmountText = '';

String wagerLocatorId = '';

String xpathSelector = '';

if(GlobalVariable.individualTestCase == true){
   WebUI.callTestCase(findTestCase('NEW PREGAME/SportBook/Transacciones(ADP-53)'), [:], FailureHandling.STOP_ON_FAILURE)
}
WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_REPORTES'), 2)

WebUI.waitForElementClickable(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_REPORTES'), 2)

WebUI.click(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_REPORTES'))

WebUI.delay(2)

if ((GlobalVariable.wagerId != null) && (GlobalVariable.wagerId != '')) {
    WebUI.click(findTestObject('Repositorio Sportbook/Reportes(ADP-49)/button_Resueltas'))

    WebUI.delay(2)

   wagerLocatorId = ('i[id=\'' + GlobalVariable.wagerId) + '_i\']'

    wagerGradedDetail.addProperty('css', ConditionType.EQUALS, wagerLocatorId)

    WebUI.click(wagerGradedDetail, FailureHandling.STOP_ON_FAILURE)

	xpathSelector = "//*[text()[contains(.,'"+GlobalVariable.wagerDesc+"')]]";
	
    wagerDesc.addProperty('xpath', ConditionType.EQUALS, xpathSelector)

	WebUI.waitForElementVisible(wagerDesc, 2)

    WebUI.verifyElementVisible(wagerDesc, FailureHandling.STOP_ON_FAILURE)

	wagerStatus.addProperty('xpath', ConditionType.EQUALS, "//*[@id='"+GlobalVariable.wagerId+"']/div/span[4]" ) 
	
	wagerStatusDesc = WebUI.getAttribute(wagerStatus, "textContent")
    
	wagerResultAmountObj.addProperty('css', ConditionType.EQUALS, "div.accordion-heading.wtr_headerDiv div.wpr_contentWagerDiv div div.ticket-general-description span:nth-child(4) " )
	
	wagerAmountText =  WebUI.getAttribute(wagerResultAmountObj, "textContent")

	wagerAmountText = wagerAmountText.replace(wagerAmountText.substring(0,wagerAmountText.indexOf(":")+1),"").trim();
	 
	wagerAmountText = wagerAmountText.replace(".00","")
	
	assert wagerStatusDesc.contains(GlobalVariable.wagerStatus)
	
	assert wagerAmountText.equals(GlobalVariable.wagerAmount)
}

