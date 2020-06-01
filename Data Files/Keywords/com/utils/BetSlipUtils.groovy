package com.utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
/**
 * Alamcena todos los selectores de css que se usan para el betSlip
 * */
public class BetSlipUtils {
	final String EMPTY_SPACE= " ";
	//-----------betslip selectors----------------//
	// Css

	private final String INNER_TEXT_ATT = "innerText";
	private final String betSlipBoxSelector = 'div.selections.sort div.selection.secret div.panel.panel-default'

	private String betSlipWagerDescSelector = null;

	private String betslipMainSelector = "div[id^='betslip_selection_?_*'] div.panel.panel-default ";

	private String betSlipHeaderSelector = null;

	private  String betSlipBodySelector =   null;

	private String betSlipContestantorTeamSelector = null;

	private String betSlipthresholdCCSSelector = null;

	private String betSlipLineSelector =  null;

	private String betSlipMaxLimitSelector = null; /*betSlipBodySelector.concat(EMPTY_SPACE).concat("div.maxbet div.limit")*/

	private final String betSlipIdButtonSelector = 'placeBet';

	private String betslipBetPeriodDesc =  null;





	/**
	 * Define el id del elemento div que contiene la informaicón del betslip deseada
	 * @param gamenum
	 */
	@Keyword
	public String getBetslipSelectedTeam(String teamSelector){
		TestObject betslipTeamObj = new TestObject("Betslip team");
		betslipTeamObj.setSelectorMethod(SelectorMethod.CSS);
		betslipTeamObj.setSelectorValue(SelectorMethod.CSS, teamSelector);

		WebUI.waitForElementVisible(betslipTeamObj, 2);

		String teamOrContestantDesc = WebUI.getAttribute(betslipTeamObj, INNER_TEXT_ATT);
		betslipTeamObj = null;

		return teamOrContestantDesc;
	}

	@Keyword
	public String getBetslipBetDesc(String selector){
		TestObject betslipTeamObj = new TestObject("Betslip description");
		betslipTeamObj.setSelectorMethod(SelectorMethod.CSS);
		betslipTeamObj.setSelectorValue(SelectorMethod.CSS, selector);

		WebUI.waitForElementVisible(betslipTeamObj, 2);

		String teamOrContestantDesc = WebUI.getAttribute(betslipTeamObj, INNER_TEXT_ATT);
		betslipTeamObj = null;

		return teamOrContestantDesc;
	}

	@Keyword
	public String getBetslipBetLineDesc(String lineSelector){
		TestObject betslipTeamObj = new TestObject("Betslip line description");
		betslipTeamObj.setSelectorMethod(SelectorMethod.CSS);
		betslipTeamObj.setSelectorValue(SelectorMethod.CSS, lineSelector);

		WebUI.waitForElementVisible(betslipTeamObj, 2);

		String teamOrContestantDesc = WebUI.getAttribute(betslipTeamObj, INNER_TEXT_ATT);
		betslipTeamObj = null;

		return teamOrContestantDesc.replace(' ', '').replace('\n', '');
	}


	@Keyword
	public String getBetslipMatch(String selector){
		TestObject betslipTeamObj = new TestObject("Betslip match description");
		betslipTeamObj.setSelectorMethod(SelectorMethod.CSS);
		betslipTeamObj.setSelectorValue(SelectorMethod.CSS, selector);

		WebUI.waitForElementVisible(betslipTeamObj, 2);

		String teamOrContestantDesc = WebUI.getAttribute(betslipTeamObj, INNER_TEXT_ATT);
		betslipTeamObj = null;

		return teamOrContestantDesc;
	}

	/**
	 * Funcion para remplazer caracteres que tengan un patron similar :ejemplo 
	 * sutituir premier_league por premier_bleague
	 * @param selector
	 * @param charToBeReplaced
	 * @param newValue
	 * @return selector
	 */
	@Keyword
	def public String formatBetSelector(String selector,String charToBeReplaced,String newValue){
		if(null == selector){
			selector = "";
		}

		selector = selector.replace(charToBeReplaced,newValue);
		return selector;
	}

	@Keyword
	public String returnWagerGeneralInfo(String match,String period,String betType,String selection,String line){
		return "{Match:"+match+"\n Period:"+period+",\n  Bet Type:"+betType+"\n selection :"+selection+" \n line "+line+" }";
	}

	/**
	 * Selecciona y devuelve la siguiente información del betslip:
	 * * Match
	 * Periodo
	 * Bet type
	 * Selection
	 * Line (spread + odds)
	 * @param gamenum
	 * @return
	 */
	@Keyword
	public  String returnWagerGeneralInfo(String gamenum,String mainBet){
		betslipMainSelector = betslipMainSelector.replace("?",gamenum).replace("*",mainBet);
		focusOnSelectedMatchInBetslip(betslipMainSelector);
		betSlipBodySelector =  betslipMainSelector.concat("div.panel-body")
		betslipBetPeriodDesc =  betSlipBodySelector.concat(" div div.descBet");
		betSlipHeaderSelector = betslipMainSelector.concat("div.panel-heading.pannel-heading-2")
		betSlipContestantorTeamSelector = betSlipBodySelector + " div div.desc"
		betSlipLineSelector = betSlipBodySelector.concat(" div.selectionDescWrap div.oddsWrap div.defaultOdds")
		String periodWagerType = this.getBetslipBetDesc(betslipBetPeriodDesc);

		String period = periodWagerType.substring(0, periodWagerType.indexOf("-")).trim();
		String betType = periodWagerType.substring(periodWagerType.indexOf("-")+1,periodWagerType.length()).trim();

		return "{Match:"+this.getBetslipMatch(betSlipHeaderSelector)+"\n Period:"+period+",\n  Bet Type:"+betType+"\n selection :"+this.getBetslipSelectedTeam(betSlipContestantorTeamSelector)+" \n line "+this.getBetslipBetLineDesc(betSlipLineSelector)+" }";
	}

	public void focusOnSelectedMatchInBetslip(String matchSelector){
		TestObject betslipTeamObj = new TestObject("Betslip selected  match ");
		betslipTeamObj.setSelectorMethod(SelectorMethod.CSS);
		betslipTeamObj.setSelectorValue(SelectorMethod.CSS, matchSelector);

		WebUI.waitForElementVisible(betslipTeamObj, 2);
		WebUI.mouseOver(betslipTeamObj);
		WebUI.focus(betslipTeamObj);

		betslipTeamObj = null;

	}
	@Keyword
	public def String getBetSlipBoxSelector(){
		return betSlipBoxSelector;
	}


	@Keyword
	public def String getBetSlipHeaderSelector(){
		return betSlipHeaderSelector;
	}

	@Keyword
	public def String getBetSlipBodySelector(){
		return betSlipBodySelector;
	}

	@Keyword
	public def String getBetSlipContestantSelector(){
		return betSlipContestantorTeamSelector;
	}


	@Keyword
	public String getBetSlipthresholdCCSSelector() {
		return betSlipthresholdCCSSelector;
	}

	@Keyword
	public String getBetSlipLineSelector() {
		return betSlipLineSelector;
	}

	@Keyword
	public String getBetSlipMaxLimitSelector() {
		return betSlipMaxLimitSelector;
	}

	@Keyword
	public String getBetSlipIdButtonSelector() {
		return betSlipIdButtonSelector;
	}


}
