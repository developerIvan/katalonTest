import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String sporId = sportName != null ? sportName.toLowerCase().trim().replace(' ', '_') : ''

String subSporId = subDeportName != null ? subDeportName.toLowerCase().trim().replace(' ', '_') : ''

String leagueId = leagueName != null && leagueName != "" ? leagueName.toLowerCase().trim().replace(' ', '_') :''


leagueId = CustomKeywords.'com.utils.BetSlipUtils.formatBetSelector'(leagueId, '_', '_b')

subSporId =  CustomKeywords.'com.utils.BetSlipUtils.formatBetSelector'(subSporId, '_', '_b')

String sportGenericId = '<SportId>'

String subSporGeneryId = '<SubsportId>'

String genericLeagueId = '<leagueId>'

String genericPeriodId=  '<period>';

String divBannerExpadedStatusSelector = "#body_group_"+sporId+"_"+subSporId+"_"+leagueId+"";

if(leagueId.isEmpty()){
	divBannerExpadedStatusSelector = "#body_group_"+sporId+"_"+subSporId+"";	
}


String supSportLeagueSelector = "//div[(@href='"+divBannerExpadedStatusSelector+"') and starts-with(@class,'titleLeague')]//div";

TestObject subSportLeagueObj = new TestObject('League ' + leagueName)

//Verifica si la liga es visible
subSportLeagueObj.setSelectorMethod(SelectorMethod.XPATH)

subSportLeagueObj.setSelectorValue(SelectorMethod.XPATH, supSportLeagueSelector)

WebUI.waitForElementVisible(subSportLeagueObj, 3)

//WebUI.click(subSportLeagueObj)
//Verifica si el banner de la liga despliega las lineas, caso contrario se hara click para que las desplieuge
TestObject divBannerExpadedStatusObjb = new TestObject("Banner de la liga "+leagueId);
divBannerExpadedStatusObjb.setSelectorMethod(SelectorMethod.CSS);
divBannerExpadedStatusObjb.setSelectorValue(SelectorMethod.CSS, divBannerExpadedStatusSelector);


String bannerIsExpanded = WebUI.getAttribute(divBannerExpadedStatusObjb, 'aria-expanded');

//if(!"true".equals(bannerIsExpanded)){
	WebUI.click(subSportLeagueObj);
//}

