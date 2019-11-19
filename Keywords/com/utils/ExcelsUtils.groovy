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
import com.kms.katalon.core.util.KeywordUtil
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFRow;
import internal.GlobalVariable
import java.util.List;
import java.util.Map;
import com.kms.katalon.core.util.KeywordUtil;
import bminc.eu.exceptions.ExcelException;
public class ExcelsUtils {

	private static FileOutputStream outFile;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static  FileInputStream inputFile;

	//Abre el archivo de escritura
	@Keyword
	def static loadFileOutputStream(String fileLoc){
		File excelFile = new File(fileLoc);
		outFile = new FileOutputStream(excelFile);
	}

	//Abre el archivo de lectura
	@Keyword
	def static loadFileInputStream(String fileLoc){
		File excelFile = new File(fileLoc);
		inputFile = new FileInputStream(excelFile);
	}

	//Crea el objecto workbook, el cual se encargara de almacena en memoria las hojas de l excel a leer o escribir
	@Keyword
	def static createReadXSSFWorkbook(){
		//KeywordUtil.logInfo("input file create  Read "+inputFile)
		workbook =  new XSSFWorkbook(inputFile);
	}

	//Define el objeto sheet utilizando el indice o número de pagina, el cual se encargara de almacena en memoria las celdas del excel a leer o escribir
	@Keyword
	def static loadXSSFSheet(int pos){
		//
		sheet = workbook.getSheetAt(pos);
	}

	//Guarda el valor en una celda en especifico, indicando la posición de la fila, la posición de la celda y el valor deseado
	@Keyword
	def static saveDataOnExcel(int rowPos,int cellPos,String value){
		setColumValue(value,rowPos,cellPos);
	}

	//Define el objeto sheet utilizando el nombre   de la pagina, el cual se encargara de almacena en memoria las celdas del excel a leer o escribir
	@Keyword
	def static loadXSSFSheet(String sheetName){
		sheet = workbook.getSheet(sheetName);

	}

	//
	@Keyword
	def static String getColumValue(int rowPos,int cellPos){
		return sheet.getRow(rowPos).getCell(cellPos).toString();
	}

	//Establece el valor en una columna en especifico
	def static setColumValue(String value,int rowPos,int cellPos){
		XSSFRow row = sheet.getRow(rowPos)!=null?sheet.getRow(rowPos):sheet.createRow(rowPos);
		row.createCell(cellPos).setCellValue(value);
	}

	//Almacena la información en el archvio  de excel
	@Keyword
	def static writeOutputExcelSheet(){
		workbook.write(outFile);
	}

	//Cierra el archvio de escritura y libra recurso
	@Keyword
	def static closeFileOutStream(){
		if(outFile != null && !outFile.closed){
			outFile.close();
		}
	}

	@Keyword
	def static closeFileInStream(){
		if( inputFile != null &&   !inputFile.closed){
			inputFile.close();
		}
	}

	@Keyword
	def saveTestResult(String locationFile,String sheetName,List<Integer> rows,Map<Integer,String> cells ) throws ExcelException{
		File excelFile = null;
		FileInputStream inputFile = null;
		XSSFWorkbook workbook =  null;
		XSSFSheet sheet = null;



		try{
			excelFile = new File(locationFile);
			inputFile = new FileInputStream(excelFile);
			workbook =  new XSSFWorkbook(inputFile);
			sheet = workbook.getSheet(sheetName);

			for(int currentRow:rows){
				for(int cellPosition:cells.keySet()){

					XSSFRow row = sheet.getRow(currentRow)!=null?sheet.getRow(currentRow):sheet.createRow(currentRow);
					row.createCell(cellPosition).setCellValue(cells.get(cellPosition));
				}
			}

		}catch(Exception e){
			KeywordUtil.logger.logError('Error al procesar la informacion de la hoja '+sheetName);
			throw new ExcelException("Error durante la lectura de archivo excel",e);
		}finally{
			if( inputFile != null &&   !inputFile.closed){
				inputFile.close();
			}


			FileOutputStream outFile= new FileOutputStream(excelFile);
			workbook.write(outFile);


			if(outFile != null && !outFile.closed){
				outFile.close();
			}

			excelFile = null;
			inputFile = null;
			workbook =  null;
			sheet = null;
		}
	}
}
