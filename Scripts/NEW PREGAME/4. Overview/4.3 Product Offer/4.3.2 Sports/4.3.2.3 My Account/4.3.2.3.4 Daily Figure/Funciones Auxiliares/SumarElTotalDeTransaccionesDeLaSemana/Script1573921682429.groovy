import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.text.DecimalFormat as DecimalFormat


final String INNER_TEXT_ATT = "innerText";
final DecimalFormat format = new DecimalFormat("0.00");
String totalAmountF = "";
Double totalTransactionsAmount = 0.0;
 String fridayAmount = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_FridayTransactionAmount'), INNER_TEXT_ATT)

 String mondayAmount =WebUI.getAttribute( findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_MondayTransactionAmount'), INNER_TEXT_ATT)

 String saturdayAmount = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_SaturdayTransactionAmount'), INNER_TEXT_ATT)

 String sundayAmount = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_SundayTransactionAmount'), INNER_TEXT_ATT)

 String thursdayAmount = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_ThursdayTransactionAmount'), INNER_TEXT_ATT)

 String tuesdayAmount = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_TuesdayTransactionAmount'), INNER_TEXT_ATT)

 String wednesdayAmount = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/td_WednesdayTransactionAmount'), INNER_TEXT_ATT)


 totalTransactionsAmount = Double.valueOf(fridayAmount) + Double.valueOf(mondayAmount) + Double.valueOf(saturdayAmount) + Double.valueOf(sundayAmount) + Double.valueOf(thursdayAmount) + Double.valueOf(tuesdayAmount)+  Double.valueOf(wednesdayAmount)

 totalAmountF =  format.format(totalTransactionsAmount);
 
 totalTransactionsAmount = (Double)format.parse(totalAmountF);
 
return   totalTransactionsAmount;