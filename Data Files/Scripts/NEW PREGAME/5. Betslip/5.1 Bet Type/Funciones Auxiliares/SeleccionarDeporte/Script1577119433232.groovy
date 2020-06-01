import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String sportId = sportName != null ? sportName.toLowerCase().trim() : ''

String sportSelector ="div[href='#sportpanel_?_content']";

//Seleccionamos el deporte
sportSelector = sportSelector.replace("?",sportId);

TestObject sportCategoryBObj = new TestObject(sportId.concat(" category object"));

sportCategoryBObj.setSelectorMethod(SelectorMethod.CSS);
sportCategoryBObj.setSelectorValue(SelectorMethod.CSS, sportSelector);

WebUI.waitForElementVisible(sportCategoryBObj, 2, FailureHandling.STOP_ON_FAILURE);

String sportPanelIsExpanded = WebUI.getAttribute(sportCategoryBObj, "class");

//WebUI.mouseOver(sportCategoryBObj);
//Expande el panel encaso de estar cerrado
if(null!=sportPanelIsExpanded&&!sportPanelIsExpanded.contains('collapsed')){
	WebUI.click(sportCategoryBObj);
}
