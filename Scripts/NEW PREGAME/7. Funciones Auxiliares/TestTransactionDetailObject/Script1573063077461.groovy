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
import com.utils.TransactionDetail as TransactionDetail
import internal.GlobalVariable as GlobalVariable
import java.util.List as List
import java.util.ArrayList as ArrayList

TransactionDetail transact = CustomKeywords.'com.utils.TransactionDetail.getNewTransactionDetail'()

transact.cadenaDeCarateres()

transact.setTicketId('Id Tiqete')

transact.setTransacctionLostWonAmount(500.01)

transact.setTransacctionType('Contest')

transact.setTransactionDescription('Ejemplo Transaction')

List<TransactionDetail> listTransact = new ArrayList<Integer>()

listTransact.add(transact)

for (TransactionDetail trans : listTransact) {
    println('' + trans.printCammpos())
}

TestCase test = GlobalVariable.mondayDailyFigureTransactions

if (null == test) {
    println('Objecto vacio ')
}

GlobalVariable.mondayDailyFigureTransactions = WebUI.callTestCase(findTestCase('NEW PREGAME/4. Overview/4.3 Product Offer/4.3.2 Sports/4.3.2.3 My Account/4.3.2.3.4 Daily Figure/Funciones Auxiliares/CargarTransaccionesDeCustomerMaintenance'), 
    [('customerId') : 'PRAC06', ('dayOfTheWeek') : 'Monday', ('CMIsCurrentUrl') : false, ('daysBefore') : 0, ('weekBefore') : 0], 
    FailureHandling.STOP_ON_FAILURE)



listTransact = GlobalVariable.mondayDailyFigureTransactions;


for (TransactionDetail trans : listTransact) {
	println('' + trans.printCammpos())
}

