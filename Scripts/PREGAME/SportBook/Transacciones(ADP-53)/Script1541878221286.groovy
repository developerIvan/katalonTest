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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor;
import java.awt.Robot;
import java.awt.event.KeyEvent;

String dynamicDivId = ''

String wagerDescVar = ''

String wagerStatusVar = ''

String wagerDate = ''

String wagerAmount = ''

boolean transacionIsVisible = false

TestObject detailTestObj = new TestObject('WagerDesc')

TestObject wagerStatusTestObj = new TestObject('WagerDesc')

TestObject wagerIdObj = new TestObject('WagerIdSpan')

TestObject modalOscTestObj = new TestObject('modalOscureScreen')

TestObject dateTransactionObj = new TestObject('dataTransaccitonsObjects')

wagerIdObj.addProperty('css', ConditionType.EQUALS, '.modal-content .modal-body #tdm_accordion div.accordion-group.borderedWagerDiv div.accordion-heading.wtr_headerDiv div.wpr_headerWagerDiv span')

TestObject documentIdObj = new TestObject('DocumentIdData')

TestObject wagerIdObjectPlusSingObj = new TestObject('wagerIdObjectPlusSing')

TestObject wagerResultAmountObj = new TestObject('wagerAmount')

Robot robot = new Robot();

if (GlobalVariable.individualTestCase == true) {
	WebUI.callTestCase(findTestCase('PREGAME/SportBook/MenuMiCuenta(ADP-50)'), [('excelLabelFileLoc') : 'C:\\\\Users\\\\jmatamoros.BMINC\\\\Documents\\\\Qa\\\\Functional Testing\\\\TestData\\\\PregameLanguageLabels.xlsx'],
	FailureHandling.STOP_ON_FAILURE)
}

WebUI.waitForElementClickable(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_TRANSACCIONES'), 2)

WebUI.click(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_TRANSACCIONES'))

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_ Documento'), 2)

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_ Documento'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_Balance'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_Cantidad'))

if (!(GlobalVariable.mobileBrowser)) {
	WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_Descripcin'))

	WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_Detalles'))

	WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/th_Fecha'))
}

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/button_Todo'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/button_Jugadas de Cortesa'), FailureHandling.STOP_ON_FAILURE)

wagerIdObjectPlusSingObj.addProperty('css', ConditionType.EQUALS, '.plus-sgin-wager-report i')



if (GlobalVariable.mobileBrowser) {
	WebDriver driver = DriverFactory.getWebDriver()
	WebElement table = driver.findElement(By.cssSelector("#ahr_Table tbody"))
	List<WebElement> rows = table.findElements(By.tagName('tr'))
	//remueve el tr sin elementos


	// Scroll Down using Robot class
	robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	table: for (int i = 2; i < rows.size(); i++) {
		WebElement mobileDetailPlusSing = driver.findElement(By.cssSelector("#ahr_Table tbody tr:nth-child("+i+") td a span"));

		mobileDetailPlusSing.click();

		WebElement plusSing = driver.findElement(By.cssSelector(".plus-sgin-wager-report i"));

		if(plusSing != null){
			wagerIdObjectPlusSingObj.addProperty('css', ConditionType.EQUALS, '.plus-sgin-wager-report i')
		}else{
			mobileDetailPlusSing.click();
		}

	}


}

if (WebUI.verifyElementVisible(wagerIdObjectPlusSingObj, FailureHandling.OPTIONAL)) {
	transacionIsVisible = true
} else {
	transacionIsVisible = false
}

if (transacionIsVisible == true) {
	dateTransactionObj.addProperty('css', ConditionType.EQUALS, '#ahr_Table tbody tr:nth-child(2) td:nth-child(2)')

	wagerDate = WebUI.getAttribute(dateTransactionObj, 'textContent', FailureHandling.STOP_ON_FAILURE)

	GlobalVariable.wagerDate = wagerDate.substring(0, 10)

	WebUI.click(wagerIdObjectPlusSingObj)

	WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/span_Wager_Id'), 2)

	WebUI.click(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/span_Wager_Id'))

	wagerResultAmountObj.addProperty('xpath', ConditionType.EQUALS, ('//span[contains(.,"' + GlobalVariable.transWagerAmountLoc) +
			'")]')

	GlobalVariable.wagerId = WebUI.getAttribute(wagerIdObj, 'textContent', FailureHandling.STOP_ON_FAILURE)

	wagerAmount = WebUI.getAttribute(wagerResultAmountObj, 'textContent', FailureHandling.STOP_ON_FAILURE)

	GlobalVariable.wagerAmount = wagerAmount.replace(wagerAmount.substring(0, wagerAmount.indexOf(':') + 1), '').trim()
	/*
	 dynamicDivId = (('//*[@id=\'' + GlobalVariable.wagerId.toString()) + '\']')
	 detailTestObj.addProperty('xpath', ConditionType.EQUALS, dynamicDivId.concat('/div/span[2]'))
	 *///GlobalVariable.wagerId.toString() description_133512799_1_VS

	/*
	 dynamicDivId = '#description_'+GlobalVariable.wagerId.toString()+'_VS';
	 detailTestObj.addProperty('css', ConditionType.EQUALS, dynamicDivId)
	 */
   dynamicDivId = (('//*[@id=\'' + GlobalVariable.wagerId.toString()) + '\']')

	detailTestObj.addProperty('xpath', ConditionType.EQUALS, dynamicDivId.concat('/div/span[2]'))
	  
	wagerDescVar = WebUI.getAttribute(detailTestObj, 'textContent', FailureHandling.STOP_ON_FAILURE)

	wagerStatusTestObj.addProperty('xpath', ConditionType.EQUALS,dynamicDivId.concat('/div[contains(@class,"accordion-inner wrt_InternalDivContent")]/span[4]'))

	wagerStatusVar = WebUI.getAttribute(wagerStatusTestObj, 'textContent', FailureHandling.STOP_ON_FAILURE)

	GlobalVariable.wagerDesc = wagerDescVar.replace(wagerDescVar.substring(0, wagerDescVar.indexOf(':') + 2), '').trim()

	GlobalVariable.wagerStatus = wagerStatusVar.replace(wagerStatusVar.substring(0, wagerStatusVar.indexOf(':') + 2), '').trim()

	WebUI.delay(2)

	WebUI.waitForElementClickable(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/button_Cerrar'), 2)

	WebUI.click(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/button_Cerrar'))

	WebUI.waitForElementNotVisible(findTestObject('Repositorio Sportbook/Transacciones(ADP-53)/button_Cerrar'), 4, FailureHandling.STOP_ON_FAILURE)

	if (GlobalVariable.mobileBrowser) {
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
}





