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
import com.utils.TransactionDetail as TransactionDetail

import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import java.time.DayOfWeek as DayOfWeek
import java.time.format.DateTimeFormatter as DateTimeFormatter
import org.openqa.selenium.support.ui.Select as Select
import java.util.ArrayList as ArrayList
import java.util.List as List
import java.time.temporal.TemporalAdjusters;

List<TransactionDetail> transaccionesDeCustomerMaintenace = new ArrayList<TransactionDetail>()
//List<TransactionDetail> listTransact = new ArrayList<TransactionDetail>();
String INNER_TEXT_ATT = 'innerText'

DayOfWeek expectedDailyFigureDate = null

//Se define que dia se deben buscar las transacciones
//Prueba
switch (dayOfTheWeek.toString().toUpperCase()) {
	case 'MONDAY':
		expectedDailyFigureDate = DayOfWeek.MONDAY

		break
	case 'TUESDAY':
		expectedDailyFigureDate = DayOfWeek.TUESDAY

		break
	case 'WEDNESDAY':
		expectedDailyFigureDate = DayOfWeek.WEDNESDAY

		break
	case 'THURSDAY':
		expectedDailyFigureDate = DayOfWeek.THURSDAY

		break
	case 'FRIDAY':
		expectedDailyFigureDate = DayOfWeek.FRIDAY

		break
	case 'SATURDAY':
		expectedDailyFigureDate = DayOfWeek.SATURDAY

		break
	case 'SUNDAY':
		expectedDailyFigureDate = DayOfWeek.SUNDAY

		break
	default:
		expectedDailyFigureDate = DayOfWeek.MONDAY

		break
}

//ir a customer maitenance

if(!CMIsCurrentUrl){
	WebUI.callTestCase(findTestCase('NEW PREGAME/7. Funciones Auxiliares/Ir a CustomerMaintenance'), [('customerId') : customerId],
	FailureHandling.STOP_ON_FAILURE)
}
//Presiono el botón de transacciones
WebUI.waitForElementClickable(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/a_Transactions'),
		4)

WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/a_Transactions'))

//Verifica que la tabla de transacciones es visible
TestObject tablaTransaciones = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Tabla Transacciones', 'Css', 'table#tlbContentScroll1',
		4)

WebElement tablaTransacc = WebUI.findWebElement(tablaTransaciones)

WebUI.delay(2);

List<TransactionDetail> transactionsElements = tablaTransacc.findElements(By.cssSelector('tbody#normal tr'))

//Busco en las transacciones cuales de estas son validas para el daily figure
for (WebElement transactionEle : transactionsElements) {
	WebElement descElement = transactionEle.findElement(By.cssSelector('td:nth-child(4)'))

	String transactionDescription = descElement.getAttribute(INNER_TEXT_ATT)

	TransactionDetail transDetail = null

	if (null != transactionDescription) {
		WebElement transactionDoc = transactionEle.findElement(By.cssSelector('td:nth-child(3)'));
		if ( transactionDescription.contains('Credit') ||
		transactionDescription.contains('Debit')) {
			transDetail = convertCreditOrDebitAdjIntoTransactionDetail(transactionDoc.findElement(By.cssSelector('a'))  ,expectedDailyFigureDate,transactionDescription)
		} else if ( transactionDescription.contains('Wager Loss') ||  transactionDescription.contains('Wager Won')) {
			String wagerDate = transactionEle.findElement(By.cssSelector('td:nth-child(1) ')).getAttribute(INNER_TEXT_ATT);
			transDetail =  convertWinLossWagersIntoTransactionDetail(transactionDoc.findElement(By.cssSelector('a')) ,expectedDailyFigureDate,wagerDate,transactionDescription);

		} else if (transactionDescription.contains('Casino')){
			String casinoAmount = null;
			String casinoTickedId = null;
			String casinoDate = transactionEle.findElement(By.cssSelector('td:nth-child(1) ')).getAttribute(INNER_TEXT_ATT);
/*
			DayOfWeek actualDayOfWeek = CustomKeywords.'com.utils.ReportHelper.getDayOfWeek'(casinoDate, DateTimeFormatter.ofPattern(
					'MM/dd/yyyy'))*/

			String expectedTransactionDate = CustomKeywords.'com.utils.ReportHelper.getCurrentDayOfTheWeek'(DateTimeFormatter.ofPattern(
				'MM/dd/yyyy'),TemporalAdjusters.previousOrSame( expectedDailyFigureDate ))

			//si la fecha de la transacci►2n es igual a la fehca requerida, esta se incluye
			if(casinoDate.equals(expectedTransactionDate)){
				transDetail = new TransactionDetail();
				casinoTickedId = transactionDoc.getAttribute(INNER_TEXT_ATT).concat("_0");

				if(transactionDescription.contains("Win")){
					casinoAmount = transactionEle.findElement(By.cssSelector('td:nth-child(5)')).getAttribute(INNER_TEXT_ATT);
				}else{
					casinoAmount = transactionEle.findElement(By.cssSelector('td:nth-child(6)')).getAttribute(INNER_TEXT_ATT);
				}

				transDetail.setTicketId(casinoTickedId)
				transDetail.setTransacctionType("Casino");
				transDetail.setTransactionDescription(transactionDescription);
				transDetail.setTransacctionLostWonAmount(Double.parseDouble(casinoAmount));
			}
		}
	}

	if (null != transDetail) {
		transaccionesDeCustomerMaintenace.add(transDetail)
	}
}


return transaccionesDeCustomerMaintenace

/*
 * WebElement creditOrDebitAjustment  Objecto que contiene enlace para ir a prefomance y cargar el detalle de la apuesta
 * DayOfWeek expectedDailyFigureDate  Objecto que contine el día de las transacciones
 * wagerDate: Fecha de las apuestas
 * wagerResult: deine si la apuesta fue ganadora o perdedora
 * */

def TransactionDetail convertWinLossWagersIntoTransactionDetail(WebElement winOrLossWagerWebElement,DayOfWeek expectedDailyFigureDate,String wagerDate,String wagerResult){
	TransactionDetail transDetail = null;
	TestObject wagerDetailTable = null;
	TestObject closeButton = null;


	final String INNER_TEXT_ATT =  "innerText";
	String transctionTicketId = null;
	String transactionWagerNumber = null;
	String transactionDescription = "";
	String transactionAmount = null
	String transactionType = null;
	WebElement wagerDetailTableWeb = null;

	String expectedTransactionDate = CustomKeywords.'com.utils.ReportHelper.getCurrentDayOfTheWeek'(DateTimeFormatter.ofPattern(
			'MM/dd/yyyy'),TemporalAdjusters.previousOrSame( expectedDailyFigureDate ))
	/*Si el dia de la apuesta no es el mismo que el esperado no se procede a convertir la transacción */
	if(!wagerDate.equals(expectedTransactionDate)){
		return null;
	}

	//Se procede a hacer click para ir a prefomance
	winOrLossWagerWebElement.click();

	WebUI.waitForElementVisible(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_ticketNumber'), 2)

	transctionTicketId =  WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_ticketNumber'), INNER_TEXT_ATT)

	transactionWagerNumber =  WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_WagerNumber'), INNER_TEXT_ATT)

	transactionType = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_WagerType'), INNER_TEXT_ATT)

	transactionDescription =   WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_Description'), INNER_TEXT_ATT)

	//Carga del monto de la apuesta según el resultado
	if(wagerResult.contains("Loss")){
		transactionAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_AmountLost'), INNER_TEXT_ATT)
	}else if(wagerResult.contains("Won")){
		transactionAmount = WebUI.getAttribute(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/td_AmountWon'), INNER_TEXT_ATT)
	}



	//Se carga la transsacion
	transDetail = new TransactionDetail()

	transDetail.setTicketId(transctionTicketId)

	transDetail.setTransacctionType(transactionType)

	transDetail.setTransacctionLostWonAmount(Double.parseDouble(transactionAmount))

	transDetail.setTransactionDescription(transactionDescription)
	
	transDetail.setTransactionDate(wagerDate)

	//Se cieera el detalle de la apuesta

	WebUI.click(findTestObject('Object Repository/Repositorio Objetos Customer Maintenance/Transactions/Wagers/span_closeWagerDetail'))


	transctionTicketId = null
	transactionDescription = "";
	transactionAmount = null
	transactionType = null;
	wagerDetailTableWeb = null;

	return transDetail;
}


/*
 * WebElement creditOrDebitAjustment  Object que contiene enlace para cargar eld etalle de la transsación en customer maitnenace
 * DayOfWeek expectedDailyFigureDate  Objecto que contine el día de las transacciones
 * transacctionType : tipo de transaccion:Debit or Credit adjustmet
 * */

def TransactionDetail convertCreditOrDebitAdjIntoTransactionDetail(WebElement creditOrDebitAjustment, DayOfWeek expectedDailyFigureDate,String transactionDescription) {
	TransactionDetail transDetail = null

	String transactionAmountWeb = null

	String transctionTicketId = creditOrDebitAjustment.getAttribute("innerText").concat("_0");

	String transactionType = null;

	//Se muestra lla tabla de detalle de la transacción
	creditOrDebitAjustment.click()


	TestObject tablaTDetalleTransaccion = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Tabla detalle de la transaccion',
			'Css', 'div#detail_transaction section.panel-body table tbody tr table', 2)

	//Carga la fila con los datos principales de la trnassaccion
	WebElement tablaDetailTransactionElement = WebUI.findWebElement(tablaTDetalleTransaccion)

	String dailyFigureDate = tablaDetailTransactionElement.findElement(By.cssSelector('tr:nth-child(3) input#fecha')).getAttribute(
			'value')

	String expectedTransactionDate = CustomKeywords.'com.utils.ReportHelper.getCurrentDayOfTheWeek'(DateTimeFormatter.ofPattern(
			'MM/dd/yyyy'),TemporalAdjusters.previousOrSame( expectedDailyFigureDate ))
	/*Si el dia de la apuesta no es el mismo que el esperado no se procede a convertir la transacción */
	if(!dailyFigureDate.equals(expectedTransactionDate)){
		return null;
	}

	WebElement trTransacctionMainData = tablaDetailTransactionElement.findElement(By.cssSelector('tr:nth-child(2)'))

	Select drpWagerDay = new Select(trTransacctionMainData.findElement(By.cssSelector('select#transa_type')))

	transactionType = drpWagerDay.getFirstSelectedOption().getText()

	transactionAmountWeb = trTransacctionMainData.findElement(By.cssSelector('input#trasamount')).getAttribute(
			'value')

	transDetail = new TransactionDetail()

	transDetail.setTicketId(transctionTicketId)

	transDetail.setTransacctionType(transactionType.toUpperCase())


	transDetail.setTransacctionLostWonAmount(Double.parseDouble(transactionAmountWeb))

	if(transDetail.getTransacctionType().contains("Debit")){
		transDetail.setTransacctionLostWonAmount(transDetail.getTransacctionLostWonAmount()*-1);
	}

	transDetail.setTransactionDescription(transactionDescription)

	//Cierrar ventana que carga la infomación de la transacción
	TestObject botonXCerrar =     CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Boton X par cerrar adjustment detail',
			'Css', 'button#cl1', 2)

	WebUI.click(botonXCerrar)

	transactionAmountWeb = null

	drpWagerDay = null

	trTransacctionMainData = null

	transctionTicketId = null


	return transDetail

}
