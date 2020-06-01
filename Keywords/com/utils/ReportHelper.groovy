package com.utils

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
import java.text.SimpleDateFormat as SimpleDateFormat
import internal.GlobalVariable
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter as DateTimeFormatter;
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import java.awt.*
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.time.DayOfWeek as DayOfWeek;
import  java.time.LocalDate as LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAdjuster
import com.kms.katalon.core.webui.driver.SmartWaitWebDriver
import com.kms.katalon.core.driver.DriverType
/**
 * @author qa-katalon
 * Contiene funciones para registrar la infomación relacionadas  al tiempo y espacio donde se  de ejecuccten las pruebas tales como fecha hora de ejecucción, navegador usado etc 
 */
public class ReportHelper {


	@Keyword
	def static String getCurrentDayOfTheWeek(DateTimeFormatter formatter,TemporalAdjuster tempAdjuster,int daysBefore){
		if(daysBefore>0){
			LocalDate weekBefore =  LocalDate.now().minusDays(daysBefore).with(tempAdjuster);
			return  weekBefore.format(formatter);
		}else{
			return LocalDate.now().with(tempAdjuster).format(formatter);
		}
	}

	@Keyword
	// Consigue la Fecha del Sistema.
	def static String getDate(){
		//return new SimpleDateFormat('dd/MM/YYYY').format(Calendar.getInstance().getTime());
		return LocalDateTime.now().format( DateTimeFormatter.ofPattern("dd/MM/YYYY"));
	}

	@Keyword
	// Consigue la Hora del sistema.
	def static String getHours(){
		//return new SimpleDateFormat('H:mm:ss').format(Calendar.getInstance().getTime());
		return  LocalTime.now().toString();
	}

	@Keyword
	def String getOperatingSystem () {
		return System.getProperty('os.name')
	}

	@Keyword
	def String getBrowserAndVersion() {
		WebDriver driver = DriverFactory.getWebDriver()
		DriverType driverType=	DriverFactory.getExecutedBrowser();
		return driverType.getName();
	}

	@Keyword
	def String getScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize()
		Integer screenHeight = screenSize.height
		Integer screenWidth = screenSize.width
		return screenWidth + 'x' + screenHeight
	}

}
