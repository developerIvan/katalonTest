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
import com.utils.DailyFigureItemDetail as DailyFigureItemDetail
import internal.GlobalVariable
import java.util.ArrayList as ArrayList
import java.util.List as List

/**
 * @author qa-katalon
 *Alamcena la lista de rubros de Daily Fgiure con su respectivo total y fecha
 */
@Keyword
public class DailyFigureContainer {


	private List<DailyFigureItemDetail> dailyFigureDetails;

	private String dailyFgureDate = null;
	private Double dailyFigureDayTotalAmount = 0.00;
	private Double dailyFigureWeekTotalAmount = 0.00;
	private String dailyFgureDay = null;
	
	@Keyword
	public void  setDailyFgureDay(String dailyFgureDay){
		this.dailyFgureDay = dailyFgureDay;
	}

	@Keyword
	public String  getDailyFgureDay(){
		return this.dailyFgureDay;
	}
	
	@Keyword
	public DailyFigureContainer(){}

	@Keyword
	public List<DailyFigureItemDetail> getDailyFigureItemDetails(){
		return this.dailyFigureDetails;
	}

	@Keyword
	public String getDailyFigureDate(){
		return this.dailyFgureDate;
	}

	@Keyword
	public Double getDailyFigureDayTotalAmount(){
		return this.dailyFigureDayTotalAmount;
	}

	@Keyword
	public Double getDailyFigureWeekTotalAmount(){
		return this.dailyFigureWeekTotalAmount;
	}

	@Keyword
	public void addDailyFigureItemDetail(DailyFigureItemDetail dailyFgiureDetail){
		if(dailyFigureDetails == null){
			dailyFigureDetails = new ArrayList<DailyFigureItemDetail>();
		}
		dailyFigureDetails.add(dailyFgiureDetail);
	}

	@Keyword
	public void setDailyFigureDate(String dailyFgureDate){
		this.dailyFgureDate = dailyFgureDate;
	}

	@Keyword
	public void  setDailyFigureDayTotalAmount(Double dailyFigureDayTotalAmount){
		this.dailyFigureDayTotalAmount = dailyFigureDayTotalAmount;
	}

	@Keyword
	public void setDailyFigureWeekTotalAmount(Double dailyFigureWeekTotalAmount){
		this.dailyFigureWeekTotalAmount = dailyFigureWeekTotalAmount;
	}
}


