package com.utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
/**
 * Alamcena todos los selectores de css que se usan para el betSlip
 * */
public class BetSlipUtils {
	final String EMPTY_SPACE= " ";
	//-----------betslip selectors----------------//
	// Css

	private final String betSlipBoxSelector = 'div.selections.sort div.selection.secret div.panel.panel-default'

	private final String betSlipWagerDescSelector = betSlipBoxSelector + ' div.panel-heading.pannel-heading-2'

	private final String betSlipHeaderSelector = 'div.selections.sort div.selection.secret div.panel.panel-default div.panel-heading.pannel-heading-2'

	private final String betSlipBodySelector = 'div.selections.sort div.selection.secret div.panel.panel-default div.panel-body'

	private final  String betSlipContestantSelector = betSlipBodySelector + ' div div.desc'

	private final String betSlipContestSelector = betSlipBodySelector + ' div div.descBet'

	private final  String betSlipthresholdCCSSelector = betSlipBodySelector.concat(' div div.selectionDescWrap div.oddsWrap div.defaultOdds div.threshold')

	private final String betSlipLineSelector = betSlipBodySelector.concat(' div div.selectionDescWrap div.oddsWrap div.defaultOdds div.odds')

	private final String betSlipMaxLimitSelector = betSlipBodySelector.concat(EMPTY_SPACE).concat('div.maxbet div.limit')

	private final String betSlipIdButtonSelector = 'placeBet';

	@Keyword
	public def String getBetSlipBoxSelector(){
		return betSlipBoxSelector;
	}

	@Keyword
	public def String getBetSlipWagerDescSelector(){
		return betSlipWagerDescSelector;
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
		return betSlipContestantSelector;
	}
	@Keyword
	public String getBetSlipContestSelector() {
		return betSlipContestSelector;
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
