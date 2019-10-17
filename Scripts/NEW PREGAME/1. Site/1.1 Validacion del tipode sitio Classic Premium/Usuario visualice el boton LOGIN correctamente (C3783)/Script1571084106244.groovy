import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import bminc.eu.exceptions.LoginException
import internal.GlobalVariable as GlobalVariable
WebUI.openBrowser('')


WebUI.navigateToUrl(url)

WebUI.maximizeWindow()



TestObject loginButton = CustomKeywords.'com.utils.AutomationUtils.findTestObject'("login", "css", "#logIn", 2);

assert !loginButton.equals(CustomKeywords.'com.utils.AutomationUtils.getNullObject'());

GlobalVariable.pregameSiteEsVisible = true;