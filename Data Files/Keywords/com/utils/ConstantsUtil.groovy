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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
/*Alamcenma las constantes frecuentemente más usadas de los test cases*/
public class ConstantsUtil {

	@Keyword
	def String getCSSSelectorId(){
		return "css";
	}

	@Keyword
	def String getXPathSelectorId(){
		return "xpath";
	}

	@Keyword
	def ConditionType getEqualsConditionType(){
		return ConditionType.EQUALS;
	}

	@Keyword
	def String getHtmlTextContentAtt(){
		return "textContent";
	}

	@Keyword
	def boolean notEmptyString(Object obj){
		if(obj != null && !obj.toString().equals("") ){
			return true;
		}else{
			return false;
		}
	}

	@Keyword
	def String getHalfLinePoint(){
		return '½';
	}

	@Keyword
	def String getFailedStatus(){
		return 'Fallido';
	}

	@Keyword
	def String getsuccessStatus(){
		return 'Exitoso';
	}

	@Keyword
	def String getCustomErrorMessageForGeneralExceptions(String errorCode){
		return "Favor de revisar en la consola de katalon con el siguiente código ".concat(errorCode);
	}


	@Keyword
	def String getCustomErrorMessageForStepExceptions(String errorCode){
		return " Para ver que elemento faltó, o que pasó no se cumplió, favor ir a la consola de katalon con el siguiente código  ".concat(errorCode);
	}


	@Keyword
	def String getCustomErrorMessageForStepExceptions(String errorCode, String... testcasesId){
		return " Para ver que elemento faltó, o que pasó no se cumplió, favor ir a la consola de katalon con el siguiente código  ".concat(errorCode)+ " o revisar el resultado de los siguientes casos de prueba "+testcasesId+" en el archivo excel de resultados de prueba para verificar si alguna precondición del caso de prueba falló ";
	}
}