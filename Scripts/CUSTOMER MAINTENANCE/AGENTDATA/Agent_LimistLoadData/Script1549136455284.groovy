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
import com.kms.katalon.core.testobject.TestObjectProperty
import bminc.eu.exceptions.LoginException
import java.lang.AssertionError;
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType

KeywordLogger log = new KeywordLogger()

final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

final String EMPTY_SPACE= " ";

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.callTestCase(findTestCase('NEW PREGAME/AuxTestCases/Lobby/NavigateToCustomerMaintenance'), [('user') : findTestData('LobbyTestData').getValue(
			2, 1), ('password') : findTestData('LobbyTestData').getValue(3, 1)], FailureHandling.STOP_ON_FAILURE)
//css a[href = '/index.php/Limits']

CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Limits', new TestObjectProperty(CSS_SELECTOR, equalsCondType, 'a[href = "/index.php/Limits"]'), 2);
//WebUI.waitForElementClickable(findTestObject('Repositorio Objectos Pagina Customer Maintenance/a_Limits'), 2)

//WebUI.click(findTestObject('Repositorio Objectos Pagina Customer Maintenance/a_Limits'))

//id("globalcustomer")

TestObject inputSearchCustomer = CustomKeywords.'com.utils.AutomationUtils.findTestObject'('Input search user', new TestObjectProperty(CSS_SELECTOR, equalsCondType, '#globalcustomer'), 4);    

//WebUI.waitForElementVisible(findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Save changes_globalcusto'), 2)

WebUI.sendKeys(inputSearchCustomer, agent)
//id("btn_search_customer")

CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Search Customer', new TestObjectProperty(CSS_SELECTOR, equalsCondType, '#btn_search_customer'), 2);

WebUI.delay(2)
//btn_editar

//WebUI.waitForElementVisible(findTestObject('Repositorio Objectos Pagina Customer Maintenance/a_Edit'), 4)

//WebUI.waitForElementClickable(findTestObject('Repositorio Objectos Pagina Customer Maintenance/a_Edit'), 4)

//WebUI.click(findTestObject('Repositorio Objectos Pagina Customer Maintenance/a_Edit'))
CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Search Customer', new TestObjectProperty(CSS_SELECTOR, equalsCondType, '#btn_editar'), 2);


//
CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Search Customer', new TestObjectProperty(CSS_SELECTOR, equalsCondType, '#btn_editar'), 2);
WebUI.waitForElementClickable(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Credit Limit_inetbet'), 4)

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Credit Limit_inetbet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Credit Limit_inetbet'), findTestData('AgentData').getValue(
		2, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_PropContest_contest'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_PropContest_contest'), findTestData('AgentData').getValue(
		4, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Parlay Payout_maxpar'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Parlay Payout_maxpar'), findTestData(
		'AgentData').getValue(5, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Parlay_maxparlaybet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Parlay_maxparlaybet'), findTestData(
		'AgentData').getValue(6, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_CU Min Bet_minbet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_CU Min Bet_minbet'), findTestData('AgentData').getValue(
		7, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_INET Min Bet_inetbet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_INET Min Bet_inetbet'), findTestData('AgentData').getValue(
		8, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Game Profile Times_gameP'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/input_Game Profile Times_gameP'), findTestData('AgentData').getValue(
		9, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Teaser Bet_teasearma'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Teaser Bet_teasearma'), findTestData(
		'AgentData').getValue(10, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Areverse_maxAreverse'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Areverse_maxAreverse'), findTestData(
		'AgentData').getValue(11, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Quick Limit_inetbet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Quick Limit_inetbet'), findTestData('AgentData').getValue(
		12, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Live Max Bet_livemaxbet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Live Max Bet_livemaxbet'), findTestData(
		'AgentData').getValue(13, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Live Min Bet_liveminbet'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Live Min Bet_liveminbet'), findTestData(
		'AgentData').getValue(14, 1))

WebUI.clearText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Parlay Bet Live_maxp'))

WebUI.sendKeys(findTestObject('Repositorio Objectos Pagina Customer Maintenance/Limits/input_Max Parlay Bet Live_maxp'), findTestData(
		'AgentData').getValue(15, 1))

WebUI.click(findTestObject('Repositorio Objectos Pagina Customer Maintenance/a_Save'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Repositorio Objectos Pagina Customer Maintenance/span_Done'), 2)

WebUI.verifyElementText(findTestObject('Repositorio Objectos Pagina Customer Maintenance/p_Changes were succesfully app'), 'Changes were succesfully applied!!')

WebUI.delay(2)

WebUI.click(findTestObject('Repositorio Objectos Pagina Customer Maintenance/span_OK'))

WebUI.closeBrowser()

