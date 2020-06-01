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

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//String sportCategoyrSelector = "div[href='#sportpanel_?_content']";

String lineButtonSelector="div[schedule]";
//div#groupPeriodDesktop table tbody tr button[betdescription='?'][chosenteamid='^']
String wagerType = '';
String wagerTypeParam = betType != null?betType:"";
String selectedTeam = selectedTeam!=null?selectedTeam.trim():"";
String leagueId =tournament!=null?tournament:null;  
String gameId = gamenum!=null&&gamenum!=""?gamenum:null;
switch(wagerTypeParam.toUpperCase()){
	case "S":
		wagerType = "Spread";
		break;
	case "M":
		wagerType = "Money";
		break;
	case "T":
		wagerType = "Total";
		break;
	case "TO":
	//Team total over, en el
		wagerType = "Team Total - Total";
		break;
	case "TU":
		wagerType  = "Team Total - Under";
	default:
		wagerType = "Spread";
		break;
}

if(null == gameId){
	lineButtonSelector = leagueId!=null?"div[schedule='"+leagueId.toUpperCase()+"']":lineButtonSelector;
	lineButtonSelector = lineButtonSelector.concat(" div#groupPeriodDesktop table tbody tr ").concat(" button[betdescription='"+wagerType+"'][chosenteamid*='"+selectedTeam+"'][periodnumber='"+period+"']");
}else{
    lineButtonSelector = "button[chosenteamid*='"+selectedTeam+"'][gamenum='"+gameId+"']"

}

//Busca y selecciona el botón
TestObject lineButtonObj = new TestObject('line button')

lineButtonObj.setSelectorMethod(SelectorMethod.CSS)

lineButtonObj.setSelectorValue(SelectorMethod.CSS, lineButtonSelector)

WebUI.waitForElementPresent(lineButtonObj, 2)

return  WebUI.findWebElement(lineButtonObj)