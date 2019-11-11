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

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


public class TransactionDetail {

	private String ticketId = null;
	private String transactionDescription = null;
	private String transacctionType = null;
	private double transacctionLostWonAmount = 0;
    private String transactionDate = null;
	
	
	@Keyword
	public TransactionDetail(){
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
	public void setTransactionDescription(String transactionDescription){
		this.transactionDescription = transactionDescription;
	}

	/**
	 * Establece el tipo de transaccion: debit adjustment, Wager won etc
	 * @param wagerType
	 */
	@Keyword
	public void setTransacctionType(String transacctionType){
		this.transacctionType = transacctionType;
	}

	/**
	 * Establece el monto de la transacción
	 * @param wagerLostWonAmount
	 */
	@Keyword
	public void setTransacctionLostWonAmount(double wagerLostWonAmount){
		this.transacctionLostWonAmount = wagerLostWonAmount;
	}

	/**
	 * Establece la fecha de la transaccion (Solo si son apuestas resuetlas del dia especifico)
	 * @param transactionDate
	 */
	public void setTransactionDate(String transactionDate){
		this.transactionDate = transactionDate
	}
	
	/**
	 * Devuevle la fecha de la transaccion (Solo si son apuestas resuetlas del dia especifico tendra datos, sino sera vacio)
	 * @param transactionDate
	 */
	public String getTransactionDate(){
		return this.transactionDate
	}
	
	/**
	 * Devuelve la descripcion de la transaccion Ej:Football - 473 New England Patriots/474 Baltimore Ravens - Game Spread - New England Patriots -3 -115
	 * @param wagerType
	 */
	@Keyword
	public String getTransactionDescription(){
		return this.transactionDescription;
	}

	/**
	 * Devuelve el tipo de transaccion: debit adjustment, Wager won etc
	 * @param wagerType
	 */
	@Keyword
	public String getTransacctionType(){
		return this.transacctionType;
	}

	/**
	 * Devuelve el monto de la pauesta perdida o ganada
	 * @param wagerLostWonAmount
	 */
	@Keyword
	public double getTransacctionLostWonAmount(){
		return this.transacctionLostWonAmount;
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
	public TransactionDetail getNewTransactionDetail(){
		return new TransactionDetail();
	}

	@Keyword
	public void cadenaDeCarateres(){
		println "soy un nuevo objeto de tipo transaccion";
	}

	@Keyword
	public void printCammpos(){
		println "Datos transaccion: { ticketId: "+this.getTicketId()+", \n TranssactionType: "+this.getTransacctionType()+", \n Descripcion: "+this.getTransactionDescription()+", \n Lost Won Amount: "+this.getTransacctionLostWonAmount()+" }";
	}


	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}