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
import org.openqa.selenium.WebElement as WebElement
import java.util.List;
import java.util.ArrayList;
import com.utils.DailyFigureContainer as DailyFigureContainer
import com.utils.DailyFigureItemDetail as DailyFigureItemDetail
//Selectores css para las transacciones en pregame
String transactionId = ''

String transactionContainerCSS = null

String descriptionLocatorCss = null

String wagerTypeLocatorCss = null

String wagerAmountLocatorCss = null

String CSS_SELECTOR_TYPE = 'CSS'

String INNER_TEXT_ATT = 'innerText'

String itemsLocator = "div.wpr_headerWagerDiv span";

List<WebElement> items = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'(CSS_SELECTOR_TYPE, "div.wpr_headerWagerDiv span", 3) 

DailyFigureContainer dailyFigureContainer  = new DailyFigureContainer();


Double dailyFigureDayTotalAmount = 0.00;
for(WebElement webElement:items){
	transactionId = webElement.getAttribute(INNER_TEXT_ATT);
	
	DailyFigureItemDetail itemDetail = new DailyFigureItemDetail();
	//Click en icono + para abrir la transacción
	/*
	TestObject transactionOpenDetailIcon = new TestObject("");
	
	TestObject transactionOpenDetailIcon = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Selected Day Amount',
			'css', ('i[id^="' + transactionId) + '"]', 4)

	WebUI.click(transactionOpenDetailIcon)
*/
	transactionContainerCSS = (('div#wpr_contentWagerDiv_' + transactionId) + ' ')

	descriptionLocatorCss = transactionContainerCSS.concat('div.rightFloatDiv.table-cell')

	wagerTypeLocatorCss = transactionContainerCSS.concat('div.leftFloatDiv.table-cell span:nth-child(1)')

	wagerAmountLocatorCss = transactionContainerCSS.concat('div.leftFloatDiv.table-cell span:nth-child(3)')

	TestObject transactionDescriptionObj = CustomKeywords.'com.utils.AutomationUtils.findTestObject'(('Transaction ' +
			transactionId) + ' description', CSS_SELECTOR_TYPE, descriptionLocatorCss, 2)

	TestObject transactionAmountObj = CustomKeywords.'com.utils.AutomationUtils.findTestObject'(('Transaction ' + transactionId) +
			' lost/won amount', CSS_SELECTOR_TYPE, wagerAmountLocatorCss, 2)

	TestObject transactionWagerType = CustomKeywords.'com.utils.AutomationUtils.findTestObject'(('Transaction ' + transactionId) +
			' type', CSS_SELECTOR_TYPE, wagerTypeLocatorCss, 2)

	String actualDescription = WebUI.getAttribute(transactionDescriptionObj, INNER_TEXT_ATT)

	String actualAmount = WebUI.getAttribute(transactionAmountObj, INNER_TEXT_ATT)

	String actualTransactionType = WebUI.getAttribute(transactionWagerType, INNER_TEXT_ATT)
	
	
	//Se repmplaza el el texto del max amout para solo dejar el valor númerico
	
	boolean isNegativeValue = actualAmount.indexOf("-")>-1?true:false;
	
	String nonNumericAmonuntValues =  actualAmount.replaceAll("\\d+\\.\\d+","")
	actualAmount = actualAmount.replaceAll(nonNumericAmonuntValues,"")
	actualAmount = actualAmount.substring(actualAmount.indexOf(":")+1,actualAmount.length())
	
	//Se reemplaza la información adicional que tenga el tipo de apuesta
	actualTransactionType = actualTransactionType.substring(actualTransactionType.indexOf(":")+1,actualTransactionType.length())
	actualTransactionType = actualTransactionType.substring(1,actualTransactionType.length());
	//Se da la sumatoria de los montos der los rubros 
	Double itemDetailAmount = Double.parseDouble(actualAmount);
	
	if(isNegativeValue){
		itemDetailAmount = itemDetailAmount*-1;
	}
	dailyFigureDayTotalAmount+=itemDetailAmount;
	
	itemDetail.setItemDescription(actualDescription);
	itemDetail.setItemLostWonAmount(itemDetailAmount)
	itemDetail.setItemType(actualTransactionType)
	
	dailyFigureContainer.addDailyFigureItemDetail(itemDetail);
}

dailyFigureContainer.setDailyFigureDayTotalAmount(dailyFigureDayTotalAmount)

return dailyFigureContainer;



