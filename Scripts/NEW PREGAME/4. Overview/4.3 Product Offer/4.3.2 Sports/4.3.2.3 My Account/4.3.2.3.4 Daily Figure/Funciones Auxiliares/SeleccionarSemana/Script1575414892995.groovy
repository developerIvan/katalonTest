import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger


KeywordLogger.getInstance(this.class).logger.info("parametro week "+weekNumber);

String weekValue = "";

switch(weekNumber){

	case 0:
		weekValue = "1";
		break;

	case 1:
		weekValue = "2";
		break;

	case 2:
		weekValue = "3";
		break;

	case 3:
		weekValue = "4";
		break;
}


//Se selecciona la opción de 3 weeks ago del select de semanas

TestObject selectedWeeksDropDown =  findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/select_DailyFigureWeeks');


WebUI.selectOptionByValue(selectedWeeksDropDown, weekValue,false)

WebUI.verifyOptionSelectedByValue(selectedWeeksDropDown,weekValue,false, 2)

String selectorId = selectedWeeksDropDown.getSelectorCollection().get(selectedWeeksDropDown.getSelectorMethod());


String selectedOption =WebUI.executeJavaScript('return $("'+selectorId+'").find("option:selected").text()', null)

return selectedOption;