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
import java.util.List as List
import java.util.ArrayList as ArrayList
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.support.ui.Select as Select
import org.openqa.selenium.NoSuchElementException as NoSuchElementException;
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger;
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.By;
import  org.openqa.selenium.support.ui.ExpectedConditions;

WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/Ir a CustomerMaintenance'), [('customerId') : customerPin],
FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()
WebDriverWait webDriverWait = new WebDriverWait(driver, 30)

WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Customer Maintenance/a_Agent'), 3)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Performance'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Perfomance/a_Wagers'),
		4)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Perfomance/a_Wagers'))

//Vlida que el jugador tenga apuestas
List<String> wagersElements = null;

List<String> customerWagersTickects = new ArrayList<Integer>()

WebUI.delay(2)
Select drpWagerDay = new Select(driver.findElement(By.cssSelector('div#WagersDay select')))


if(tipoDeApuesta.toString().equalsIgnoreCase("PENDING")){

	drpWagerDay.selectByVisibleText('Pending Wagers')

	//Esperamos a que se cargen las apuestas pendientes, si es que hay
	wagersElements = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'('cSs', 'tbody#wagerreportpre tr td a',10)


	//Se cargan los tiquetes para devolver
	if (wagersElements.size() > 0) {
		for (WebElement wagerElement : wagersElements) {
			customerWagersTickects.add(wagerElement.getAttribute('innerText'))
		}
	}
}else if(tipoDeApuesta.toString().equalsIgnoreCase("GRADED")){
	switch (gradedDaysInterval){
		case 2:
			drpWagerDay.selectByVisibleText("2 Days")
			break;
		case 7:
			drpWagerDay.selectByVisibleText("7 Days")
			break;
		case 15:
			drpWagerDay.selectByVisibleText("15 Days")
			break;
		case 30:
			drpWagerDay.selectByVisibleText("30 Days")
			break;
	}


	//Esperamos a que se cargen las apuestas pendientes, si es que hay
	wagersElements = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'('cSs', 'tbody#wagerreportpre tr ',10)

	//Se cargan los tiquetes para devolver
	if (wagersElements.size() > 0) {
		for (WebElement wagerElement : wagersElements) {
			String wagerStatus = wagerElement.findElement( By.cssSelector("td:nth-child(7)")).getAttribute("innerText");

			if(!"P".equalsIgnoreCase(wagerStatus) && !'O'.equalsIgnoreCase(wagerStatus) ){
				customerWagersTickects.add( wagerElement.findElement( By.cssSelector("td a")).getAttribute('innerText'))
			}
		}
	}
}






return customerWagersTickects


