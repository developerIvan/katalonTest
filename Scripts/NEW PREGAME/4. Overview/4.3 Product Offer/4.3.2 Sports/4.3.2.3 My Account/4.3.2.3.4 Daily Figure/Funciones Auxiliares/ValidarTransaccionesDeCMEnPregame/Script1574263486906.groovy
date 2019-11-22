import com.utils.TransactionDetail as TransactionDetail
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject as TestObject

//Selectores css para las transacciones en pregame
String transactionId = ''

String transactionContainerCSS = null

String descriptionLocatorCss = null

String wagerTypeLocatorCss = null

String wagerAmountLocatorCss = null

String CSS_SELECTOR_TYPE = 'CSS'

String INNER_TEXT_ATT = 'innerText'


//Se hace la comparación de cada transacción
for (TransactionDetail transaction : transaccionesDeCustomerMaintenance) {
	transactionId = transaction.getTicketId()

	//Clik en icono + para abrir la transacción
	TestObject transactionOpenDetailIcon = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Selected Day Amount',
			'css', ('i[id^="' + transactionId) + '"]', 4)

	WebUI.click(transactionOpenDetailIcon)

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

	//Validación de que los datos de las transacciones de customer maintenance sean visibles en pregame
	//    assert  actualDescription.contains(transaction.getTransactionDescription());
	assert actualAmount.contains(Double.toString(transaction.getTransacctionLostWonAmount()))

	assert actualTransactionType.contains(transaction.getTransacctionType())
}

