import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebElement as WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.exception.StepFailedException as StepFailedException

String testcaseId = 'C3809';

String exceptionMessageDesc = "Caso de prueba "+testcaseId+ " Fallido.";

String testResultDescription = '';

boolean tomarInstantanea=false;


//Valida que el site de pregame on se haya cargado ya en el navegador
if(GlobalVariable.pregameSiteEsVisible == false){

	if(! GlobalVariable.browserIsOpen){
		WebUI.openBrowser('')
		GlobalVariable.browserIsOpen = true;
	}
	WebUI.navigateToUrl(url)

	WebUI.maximizeWindow()
}

try {
	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/1. Site/1.1 Validacion del tipo de sitio/Page_Apuestas deportivas juegos de casino carreras de caballos/Logo Jueguelo'), 2)

	WebElement logoJueguelo = WebUI.findWebElement(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/1. Site/1.1 Validacion del tipo de sitio/Page_Apuestas deportivas juegos de casino carreras de caballos/Logo Jueguelo'));

	assert logoJueguelo.isDisplayed() == true;

} catch(java.lang.AssertionError asserError){
	String errorCode = testcaseId.concat('-09 ')

	KeywordLogger.getInstance(this.class).logger.error(errorCode, asserError)
	tomarInstantanea = true;
	testResultDescription = 'El logo jueguelo de pregame  debería ser visible, pero actualmente no lo es. lo cual indica que, o el botón desaparecio  o fue modificado lo que impide que la prueba automatizada no lo pueda encontrar. Favor buscar en el log de katalon';
	throw new StepFailedException(errorCode.concat(exceptionMessageDesc) ,asserError);
}






