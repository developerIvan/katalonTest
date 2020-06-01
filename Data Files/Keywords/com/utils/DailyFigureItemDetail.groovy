package com.utils
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

public class DailyFigureItemDetail {

	private String ticketId = null;
	private String itemDescription = null;
	private String itemType = null;
	private double itemLostWonAmount = 0.00;
	private String itemDate = null;


	@Keyword
	public DailyFigureItemDetail(){
	}

	@Keyword
	public void setTicketId(String ticketId){
		this.ticketId = ticketId;
	}


	/**
	 * Establece la descripcion de la transaccion Ej:Football - 473 New England Patriots/474 Baltimore Ravens - Game Spread - New England Patriots -3 -115
	 * @param wagerType
	 */
	@Keyword
	public void setItemDescription(String transactionItem){
		this.itemDescription = transactionItem;
	}

	/**
	 * Establece el tipo de transaccion: debit adjustment, Wager won etc
	 * @param wagerType
	 */
	@Keyword
	public void setItemType(String itemType){
		this.itemType = itemType;
	}

	/**
	 * Establece el monto de la transacción
	 * @param wagerLostWonAmount
	 */
	@Keyword
	public void setItemLostWonAmount(double itemWonAmount){
		this.itemLostWonAmount = itemWonAmount;
	}

	/**
	 * Establece la fecha de la transaccion (Solo si son apuestas resuetlas del dia especifico)
	 * @param itemDate
	 */
	public void setItemnDate(String itemDate){
		this.itemDate = itemDate
	}

	/**
	 * Devuevle la fecha de la transaccion (Solo si son apuestas resuetlas del dia especifico tendra datos, sino sera vacio)
	 * @param itemDate
	 */
	public String getItemDate(){
		return this.itemDate
	}

	/**
	 * Devuelve la descripcion de la transaccion Ej:Football - 473 New England Patriots/474 Baltimore Ravens - Game Spread - New England Patriots -3 -115
	 * @param wagerType
	 */
	@Keyword
	public String getItemDescription(){
		return this.itemDescription;
	}

	/**
	 * Devuelve el tipo de transaccion: debit adjustment, Wager won etc
	 * @param wagerType
	 */
	@Keyword
	public String getItemType(){
		return this.itemType;
	}

	/**
	 * Devuelve el monto de la pauesta perdida o ganada
	 * @param wagerLostWonAmount
	 */
	@Keyword
	public double getItemLostWonAmount(){
		return this.itemLostWonAmount;
	}

	/**
	 * Devuelve el numero de tiquete de la transaccion
	 * @param wagerLostWonAmount
	 */

	@Keyword
	public String getTicketId(){
		return this.ticketId;
	}

	@Keyword
	public void printCammpos(){
		println "Datos de Daily figure: { ticketId: "+this.getTicketId()+", \n TranssactionType: "+this.getItemType()+", \n Descripcion: "+this.getItemDescription()+", \n Lost Won Amount: "+this.getItemLostWonAmount()+" }";
	}
}