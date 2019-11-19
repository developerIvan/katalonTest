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
import java.util.Map;
import java.util.HashMap;


Map<String,String> daysOfWeekMap = new  HashMap<String,String>();

WebUI.waitForElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Friday'),2)

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Friday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Monday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Saturday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Sunday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Thursday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Total'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Tuesday'))

WebUI.verifyElementVisible(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Wednesday'))

String mondayDayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Monday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String thursdayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Thursday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String wendesdayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Wednesday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String tuesdayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Tuesday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String fridayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Friday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String saturdayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Saturday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String sundayDFTableHeader = WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Sunday'), "innerText", FailureHandling.STOP_ON_FAILURE)

String totalDFTableHeader =  WebUI.getAttribute(findTestObject('Repositorio Objetos Proyecto Premium/4.3.2.3 MY ACCOUNT/4.3.2.3.4 Daily Figure/th_Total'), "innerText", FailureHandling.STOP_ON_FAILURE)

daysOfWeekMap.put("monday",mondayDayDFTableHeader);
daysOfWeekMap.put("thursday",thursdayDFTableHeader);
daysOfWeekMap.put("wendesday",wendesdayDFTableHeader);
daysOfWeekMap.put("tuesday",tuesdayDFTableHeader);
daysOfWeekMap.put("friday",fridayDFTableHeader);
daysOfWeekMap.put("saturday",saturdayDFTableHeader);
daysOfWeekMap.put("sunday",sundayDFTableHeader);
daysOfWeekMap.put("total",totalDFTableHeader);

return daysOfWeekMap;
