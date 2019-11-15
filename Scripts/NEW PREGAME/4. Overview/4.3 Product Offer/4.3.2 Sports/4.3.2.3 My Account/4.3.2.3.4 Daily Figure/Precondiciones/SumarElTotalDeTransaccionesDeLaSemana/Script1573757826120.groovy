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

Dobuble totalTransactionsAmount = 0.0;

final String INNER_TEXT_ATT = "innerText";

String fridayAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Friday'), INNER_TEXT_ATT)

 String mondayAmount =WebUI.getAttribute( findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Monday'), INNER_TEXT_ATT)

 String saturdayAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Saturday'), INNER_TEXT_ATT)

 String sundayAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Sunday'), INNER_TEXT_ATT)

 String thursdayAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Thursday'), INNER_TEXT_ATT)

 String tuesdayAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Tuesday'), INNER_TEXT_ATT)

 String wednesdayAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Wednesday'), INNER_TEXT_ATT)



 totalTransactionsAmount = Double.valueOf(fridayAmount) + Double.valueOf(mondayAmount) + Double.valueOf(saturdayAmount) + Double.valueOf(sundayAmount) + Double.valueOf(thursdayAmount) + Double.valueOf(tuesdayAmount)+  Double.valueOf(wednesdayAmount)

return totalTransactionsAmount;