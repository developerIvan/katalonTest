package mycompany

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import java.awt.*

public class GetTestingConfig {

	@Keyword
	def String getOperatingSystem () {
		println System.getProperty('os.name')
		return System.getProperty('os.name')
	}

	@Keyword
	def String getBrowserAndVersion() {
		WebDriver driver = DriverFactory.getWebDriver()
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities()
		String browserName = caps.getBrowserName().capitalize()
		String browserVersion = caps.getVersion()
		println browserName + ' ' + browserVersion
		return browserName + ' ' + browserVersion
	}

	@Keyword
	def String getScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize()
		Integer screenHeight = screenSize.height
		Integer screenWidth = screenSize.width
		println screenWidth + 'x' + screenHeight
		return screenWidth + 'x' + screenHeight
	}
}
