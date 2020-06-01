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
import internal.GlobalVariable as GlobalVariable

TestObject wagerTypeObj = null;

String betType = wagerType!=null?wagerType:"";

betType = betType.toLowerCase();

if(betType.contains("straight")){
	wagerTypeObj =findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/5. Betslip/div_Straight Bet');
}else if(betType.contains("parlay")){
	wagerTypeObj = findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/5. Betslip/div_Parlay')
}else if(betType.contains("robin")){
	wagerTypeObj = findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/5. Betslip/div_RndRobin');
}else if(betType.contains("teaser")){
	wagerTypeObj =  findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/5. Betslip/div_Teaser')
}else if(betType.contains("if")){
	wagerTypeObj = 	findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/5. Betslip/div_IfBet')
}else if(betType.contains("reverse")){
	wagerTypeObj =  findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/5. Betslip/div_Reverse')
}

WebUI.waitForElementVisible(wagerTypeObj, 2);

String buttonIsSelected = WebUI.getAttribute(wagerTypeObj, "class");


//Presiona el botón de tipo de apuesta en elk betslip en caso de que no este seleccioando.
if(null!=buttonIsSelected){
	if(!buttonIsSelected.contains("selected")){
		WebUI.click(wagerTypeObj);
	}
}


