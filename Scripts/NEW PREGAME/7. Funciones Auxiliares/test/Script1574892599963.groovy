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
import com.utils.DailyFigureContainer as DailyFigureContainer
import com.utils.DailyFigureItemDetail as DailyFigureItemDetail

container = new DailyFigureContainer()

container.setDailyFigureDate('11/11/2019 Monday')

DailyFigureItemDetail detailItem = new DailyFigureItemDetail();
detailItem.setItemType("Casino")

Double itemAmount =25.0;
detailItem.setItemLostWonAmount(itemAmount)

detailItem.setItemDescription("Casino Win - 11/11/2019")

container.addDailyFigureItemDetail(detailItem);

detailItem = new DailyFigureItemDetail();
detailItem.setItemType("DEBIT ADJUSTMENT")
detailItem.setItemLostWonAmount(-20.0)
detailItem.setItemDescription("Debit Adjustment - Retiro de prueba automatizada")
container.addDailyFigureItemDetail(detailItem);
/* DEBIT ADJUSTMENT -20.00 Debit Adjustment - Retiro de prueba automatizada*/


detailItem = new DailyFigureItemDetail();
detailItem.setItemType("HORSE RACE")
detailItem.setItemLostWonAmount(-25.0)

detailItem.setItemDescription("Turf Paradise - Race  +7 \$ +25 Straight  +1: +23 +2/X)")
container.addDailyFigureItemDetail(detailItem);
WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/ValidarRubrosEnCustomerMaintenance'), 
    [('customerId') : 'PRAC06', ('dailyFigureContainer') : container], FailureHandling.STOP_ON_FAILURE)

