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
import com.utils.DailyFigureItemDetail as DailyFigureItemDetail
import com.utils.DailyFigureContainer as DailyFigureContainer
import com.kms.katalon.core.testobject.SelectorMethod
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By


Double itemAmount= 0.00;
int itemIndex = 0;

String itemType = "";
String itemDescription = ""; 
String expectedDailyFigureFullDetail;
String actualDailyFigureFullDetail;

final String INNER_TEXT_ATT = "innerText";


DailyFigureContainer container = dailyFigureContainer

//Selectores para Daily Fgiure Detail 
String dailyFigureDetailTable = "tbody#tbodyDF tr";

String dailyFigureDetailTdAmount = dailyFigureDetailTable.concat(" td:nth-child(2)");
String dailyFigureDetailTdType = dailyFigureDetailTable.concat(" td:nth-child(1)");
String dailyFigureDetailTdDescription = dailyFigureDetailTable.concat(" td:nth-child(3)");


Map<Integer,List<String>> dailyFigureItemsFromCM = null;

if(!currentUrlIsCm){
  WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/IraPerfomance'), [('customerId') : customerId], FailureHandling.STOP_ON_FAILURE)
}


TestObject dailyFigureDate = findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/PERFOMANCE/DAILY/a_DailyfigureDate');
dailyFigureDate.setSelectorMethod(SelectorMethod.XPATH);
dailyFigureDate.setSelectorValue(SelectorMethod.XPATH,  '//a[contains(text(),"'+container.getDailyFigureDate()+'")]')

WebUI.click(dailyFigureDate);

WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/PERFOMANCE/DAILY/DailyFigureDetail/div_DailyFigureDetailHeader'), 2)


List<WebElement> dailyfigureDetailWebElemnts = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'("Css", dailyFigureDetailTable, 2);

dailyFigureItemsFromCM = new HashMap<Integer,List<String>>(dailyfigureDetailWebElemnts.size());


for(WebElement dailyfigureDetailWebelemnt:dailyfigureDetailWebElemnts){
	List<String>   dailyFigureItems = new ArrayList<String>(3);
	dailyFigureItems.add(dailyfigureDetailWebelemnt.findElement(By.cssSelector(dailyFigureDetailTdType)).getAttribute(INNER_TEXT_ATT));
	dailyFigureItems.add(dailyfigureDetailWebelemnt.findElement(By.cssSelector(dailyFigureDetailTdAmount)).getAttribute(INNER_TEXT_ATT));
	dailyFigureItems.add(dailyfigureDetailWebelemnt.findElement(By.cssSelector(dailyFigureDetailTdDescription)).getAttribute(INNER_TEXT_ATT));
	
	dailyFigureItemsFromCM.put(itemIndex, dailyFigureItems);
	itemIndex++;
} 

itemIndex = 0;
for(DailyFigureItemDetail dailyFigureItem:container.getDailyFigureItemDetails()){	 
	 
	
	 itemType = dailyFigureItem.getItemType().toUpperCase();
	 itemAmount = dailyFigureItem.getItemLostWonAmount();
	 itemDescription = dailyFigureItem.getItemDescription();
	 
	 String formatedDoubleAmount = CustomKeywords.'com.utils.AutomationUtils.roundDoubleToFourDecimals'(itemAmount)
	 
	 List<String>   dailyFigureItemFromCM = dailyFigureItemsFromCM.get(itemIndex);
	 
	 assert dailyFigureItemFromCM != null && dailyFigureItemFromCM.size()>0;
	 
	 
	 
	 boolean wagerIsHorseType = false;
	 String horseItemFromDailyFigure ='';
	 String horseItemFromCustomerMaintenace = '';
	 
	 //Formato de descipción de carreras de caballos para omologar la comparación
	 if(itemType.contains("HORSE RACE")){
		 horseItemFromDailyFigure = itemDescription.replace("+", "").replace("/", "").replace(")", "").replace(" ", "");
		 wagerIsHorseType = true;
		 itemDescription = horseItemFromDailyFigure
		 
		 String cmFormatedHorsesDecription = dailyFigureItemFromCM.get(2).replace("#", "").replace("(", "").replace(")", "").replace("/", "").replace(" ", "");
		 
		 dailyFigureItemFromCM.add(2, cmFormatedHorsesDecription);
	 }
	  
	 expectedDailyFigureFullDetail = dailyFigureItemFromCM.get(0)+"/"+dailyFigureItemFromCM.get(1)+"/"+dailyFigureItemFromCM.get(2)
	 
	 expectedDailyFigureFullDetail = expectedDailyFigureFullDetail.trim();
	 
	 actualDailyFigureFullDetail =  itemType+"/"+formatedDoubleAmount+"/"+itemDescription
	 
	 actualDailyFigureFullDetail = actualDailyFigureFullDetail.trim()
	 println expectedDailyFigureFullDetail
	 println actualDailyFigureFullDetail
	 //Sio el tipo de apuestas es de caballo, semodifica la descripcin removiendo los elementos que impiden que la comapración sea vólida
	
	 assert expectedDailyFigureFullDetail.equals(actualDailyFigureFullDetail)
	 itemIndex++;
}