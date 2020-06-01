import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.JavascriptExecutor;
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


String sporId = sportName != null ? sportName.toLowerCase().trim().replace(' ', '_') : ''

String subSporId = subSportName != null ? subSportName.toLowerCase().trim().replace(' ', '_').replace('_','_b') : ''

//Valida si el sub deporte ya fue seleccionado
if(subSporId.equals(GlobalVariable.betSelectedSubSport)){
	println "Subdeporte ya fue seleccioando "+subSporId +" vs "+GlobalVariable.betSelectedSubSport 
	return;
}

String subSportSelector = '#eventItem_?_*'

subSportSelector = subSportSelector.replace('?', sporId).replace('*', subSporId)

TestObject subSportCategoryBObj = new TestObject(subSporId.concat(' category object'))

subSportCategoryBObj.setSelectorMethod(SelectorMethod.CSS)

subSportCategoryBObj.setSelectorValue(SelectorMethod.CSS, subSportSelector)

WebUI.waitForElementVisible(subSportCategoryBObj, 2, FailureHandling.STOP_ON_FAILURE)

WebUI.click(subSportCategoryBObj)

GlobalVariable.betSelectedSubSport = subSporId;

