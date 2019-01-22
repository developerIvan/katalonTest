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
import java.time.format.DateTimeFormatter;
public class DateUtil {

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
}
