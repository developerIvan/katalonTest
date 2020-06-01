import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String sportGenericId = '<SportId>'

String subSporGeneryId = '<SubsportId>'

String genericLeagueId = '<leagueId>'

String genericPeriodId = '<period>'

String periodSelector = "button[data-period='period_group_<SportId>_<SubsportId>_<leagueId>_<period>']";



//period_group_basketball_nba_3
String sportId = sportName != null ? sportName.toLowerCase().trim().replace(' ', '_') : ''

String subSporId = subSportName != null ? subSportName.toLowerCase().trim().replace(' ', '_'):''

String leagueId = leagueName != null ? leagueName.toLowerCase().trim().replace(' ', '_') : ''

String periodId = periodNumber != null ? periodNumber.trim().replace(' ', '_') : ''

if(leagueId.isEmpty()){
	periodSelector = "button[data-period='period_group_<SportId>_<SubsportId>_<period>']";
}

TestObject periodButtonObj = new TestObject('Period ' + periodId)

//Da formato ak seklector de liga por un bug en el sitio en el cual el sisteman agrea una "b "al segundo nombre de la liga 
leagueId = CustomKeywords.'com.utils.BetSlipUtils.formatBetSelector'(leagueId, "_", "_b");

subSporId = CustomKeywords.'com.utils.BetSlipUtils.formatBetSelector'(subSporId, "_", "_b");

periodSelector = periodSelector.replace(sportGenericId, sportId).replace(subSporGeneryId, subSporId).replace(
	genericPeriodId, periodId).replace(genericLeagueId, leagueId)

periodButtonObj.setSelectorMethod(SelectorMethod.CSS)

periodButtonObj.setSelectorValue(SelectorMethod.CSS, periodSelector)

WebUI.waitForElementVisible(periodButtonObj, 2)

return WebUI.findWebElement(periodButtonObj);

/*
 * 
 * String periodButtonClass = WebUI.getAttribute(periodButtonObj, 'class')

//Si el botón del periodo no esta presionado, se hará para que muestre las líneas
if ((periodButtonClass != null) && !(periodButtonClass.contains('btn-success'))) {
	WebUI.click(periodButtonObj)
}
 * 
 * */
 