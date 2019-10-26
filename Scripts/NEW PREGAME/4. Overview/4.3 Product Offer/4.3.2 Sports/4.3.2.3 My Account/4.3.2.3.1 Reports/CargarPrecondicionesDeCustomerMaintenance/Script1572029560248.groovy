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
import java.util.List;
import java.util.ArrayList;
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.support.ui.Select;




WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/Ir a CustomerMaintenance'), [:], FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()
WebDriverWait webDriverWait = new WebDriverWait(driver, 30)

WebUI.waitForElementClickable(findTestObject('Repositorio Objetos Customer Maintenance/a_Agent'), 3)

WebUI.click(findTestObject('Repositorio Objetos Customer Maintenance/a_Performance'));

WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Perfomance/a_Wagers'), 4);

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Perfomance/a_Wagers'));

//Vlida que el jugador tenga apuestas
List<WebElement> wagersElements =  driver.findElements(By.cssSelector("tbody#wagerreportpre tr"));

List<String> pendingWagersTickects = new ArrayList<String>();

WebUI.delay(3);

if(wagersElements.size()>0){
	//casoDePrueba ='C3861';
	switch (casoDePrueba) {
		case 'C3861':
		//Busca solamente apuestas pendientes
   
			//Se selecciona solo las apuesta pendientes
			Select drpWagerDay = new Select(driver.findElement(By.cssSelector("div#WagersDay select")));
			 
			
			
			drpWagerDay.selectByVisibleText("Pending Wagers");

			WebUI.delay(2);
			//driver.findElements(By.cssSelector("tbody#wagerreportpre tr td a"))
			//Esperamos a que se cargen las apuestas pendientes, si es que hay
			wagersElements =   CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'("cSs", "tbody#wagerreportpre tr td a") ;
			
			//Se cargan los tiquetes para devolver
			if(wagersElements.size()>0){
				
				for(WebElement wagerElement: wagersElements ){
					pendingWagersTickects.add(wagerElement.getAttribute("innerText"));
				}
			}
			
			break;

		default:
			break
	}
}

return pendingWagersTickects;
