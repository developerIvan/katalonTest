import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import java.util.ArrayList as ArrayList
import java.util.Arrays as Arrays
import org.joda.time.DateTime as DateTime
import org.joda.time.Weeks as Weeks
import org.joda.time.DateTimeConstants as DateTimeConstants
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement

KeywordLogger log = new KeywordLogger()

TestObject tuesdayThObject = null

TestObject tuesdayThObjectTest = null

TestObject dayThByBalance = null

TestObject dailyfigWagerPlusDetailBtn = new TestObject("dailyfigWagerPlusDetailBtn");

TestObject wagerAmountLinkObj = new TestObject('WagerBalanceAmountLink')

if(GlobalVariable.individualTestCase == true){
   WebUI.callTestCase(findTestCase('PREGAME/SportBook/Transacciones(ADP-53)'), [:], FailureHandling.STOP_ON_FAILURE)
}
//Test Comment
WebUI.click(findTestObject('Repositorio Sportbook/MiCuenta(ADP-50)/button_BALANCE DIARIO'))

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Monday'), 2)

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Monday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Tuesday'))

tuesdayThObject = findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Tuesday')

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Wednesday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Thursday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Friday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Saturday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/th_Sunday'))

WebUI.click(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/input_To Win_br_startDaySelect'))

WebUI.delay(2)

tuesdayThObjectTest = new TestObject('tuesdayTh')

tuesdayThObjectTest.addProperty('css', ConditionType.EQUALS, '#br_headerTable  thead  tr  th:nth-child(1)')

String nonStartTuesdayValue = WebUI.getAttribute(tuesdayThObject, 'textContent', FailureHandling.STOP_ON_FAILURE)

String startTuesdayValue = WebUI.getAttribute(tuesdayThObjectTest, 'textContent', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(nonStartTuesdayValue, startTuesdayValue)

WebUI.waitForElementVisible(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/select_semanas'), 2)

//Validacion de semanas
if ((GlobalVariable.wagerDate != null) && (GlobalVariable.wagerDate != '')) {
	ArrayList<String> wagerDateElements = new ArrayList<String>(Arrays.asList(GlobalVariable.wagerDate.split('/')))

	DateTime dateTimeToday = new DateTime()

	DateTime wagerDateTime = new DateTime(Integer.parseInt(wagerDateElements.get(2)), Integer.parseInt(wagerDateElements.get(
			0)), Integer.parseInt(wagerDateElements.get(1)), 0, 0, 0)

	String balanceDayAmount = ''

	switch (wagerDateTime.getDayOfWeek()) {
		case DateTimeConstants.MONDAY:
			balanceDayAmount = 'Monday'
			break;
		case DateTimeConstants.TUESDAY:
			balanceDayAmount = 'Tuesday'
			break;
		case DateTimeConstants.WEDNESDAY:
			balanceDayAmount = 'Wednesday'
			break;
		case DateTimeConstants.THURSDAY:
			balanceDayAmount = 'Thursday'
			break;
		case DateTimeConstants.FRIDAY:
			balanceDayAmount = 'Friday'
			break;
		case DateTimeConstants.SATURDAY:
			balanceDayAmount = 'Saturday'
			break;
		case DateTimeConstants.SUNDAY:
			balanceDayAmount = 'Sunday'
			break;
		default:
			break
	}

	int wagerWeeks = Weeks.weeksBetween(wagerDateTime, dateTimeToday).getWeeks()

	wagerAmountLinkObj.addProperty('css', ConditionType.EQUALS, "td[data-th='"+balanceDayAmount+"'] a")

	//Verifica por esta semana o la pasada
	while (wagerWeeks < 4) {
		WebUI.selectOptionByIndex(findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/select_semanas'), wagerWeeks)

		if (WebUI.waitForElementVisible(wagerAmountLinkObj, 2, FailureHandling.OPTIONAL)) {
			wagerWeeks = 4

			WebUI.click(wagerAmountLinkObj, FailureHandling.STOP_ON_FAILURE)
			WebUI.delay(2)


			dailyfigWagerPlusDetailBtn.addProperty('xpath', ConditionType.EQUALS, "//*[@id='"+GlobalVariable.wagerId+"_i']")

			WebUI.waitForElementClickable(dailyfigWagerPlusDetailBtn, 2)

			WebUI.click(dailyfigWagerPlusDetailBtn)
			
			TestObject wagerStatusObj = findTestObject('Repositorio Sportbook/BalanceDiario(ADP-52)/span_Estatus de Apuesta').addProperty('xpath', ConditionType.EQUALS, '//span[contains(.,"'+GlobalVariable.wagerStatusLabel+'")]')
			
			String wagerStatusDailyWagerResult = WebUI.getAttribute(wagerStatusObj,
					'textContent')
			
			TestObject wagerResultAmountObj= findTestObject('Object Repository/Repositorio Sportbook/BalanceDiario(ADP-52)/span_GananciaOPerdida');
			
			wagerResultAmountObj.addProperty('xpath', ConditionType.EQUALS, 'id("wpr_contentWagerDiv_'+GlobalVariable.wagerId+'")/div[@class="leftFloatDiv table-cell"]/span[2]')
			
			String wagerStatusDailyAmount = WebUI.getAttribute(wagerResultAmountObj,
				'textContent')
			
			TestObject wagerResultDesc = findTestObject('Object Repository/Repositorio Sportbook/BalanceDiario(ADP-52)/span_WagerDesc')
			
			wagerResultDesc.addProperty('xpath', ConditionType.EQUALS, '//*[text()[contains(.,"'+GlobalVariable.wagerDesc+'")]]')
			
			WebUI.waitForElementVisible(wagerResultDesc, 2, FailureHandling.STOP_ON_FAILURE)
			
			WebUI.verifyElementVisible(wagerResultDesc)
			
			assert wagerStatusDailyWagerResult.contains(GlobalVariable.wagerStatus)
			
			assert wagerStatusDailyAmount.contains(GlobalVariable.wagerAmount)

			WebUI.delay(2)
			
			WebUI.click(dailyfigWagerPlusDetailBtn)
			
		} else {
			wagerWeeks++
		}
	}
	//Validación suma de montos
	if(GlobalVariable.wagerAmount != null && GlobalVariable.wagerAmount != ''){
		WebDriver driver = DriverFactory.getWebDriver();
		double totalAmountCells = 0.0;
		
		WebElement dailyBalTable = driver.findElement(By.id("br_headerTable"));
		
		List<WebElement> tdtable = dailyBalTable.findElements(By.tagName('td'))
		
		WebElement totalTd = driver.findElement(By.cssSelector('td[data-th="Total"]'))
		
		tdtable.remove(totalTd)
		
		for(WebElement tdElemnt:tdtable ){
		 totalAmountCells+=   Double.parseDouble(tdElemnt.getAttribute("textContent").toString())
		}
	
		assert Double.parseDouble(totalTd.getAttribute("textContent").toString()) == totalAmountCells		
	}
}

