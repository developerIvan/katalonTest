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
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAdjuster;
import java.time.DayOfWeek as DayOfWeek
import java.time.format.DateTimeFormatter as DateTimeFormatter

TemporalAdjuster temAjuster = null;

DayOfWeek dailyFigureDateDay = null
int daysBefore = 0;

String fechaDailyFigure = '';
switch (dayOfTheWeek.toString().toUpperCase()) {
	case 'M':
		dailyFigureDateDay = DayOfWeek.MONDAY
		break;
	case 'K':
		dailyFigureDateDay = DayOfWeek.TUESDAY
		break;
	case 'W':
		dailyFigureDateDay = DayOfWeek.WEDNESDAY
		break;
	case 'T':
		dailyFigureDateDay = DayOfWeek.THURSDAY
		break;
	case 'F':
		dailyFigureDateDay = DayOfWeek.FRIDAY
		break;
	case 'S':
		dailyFigureDateDay = DayOfWeek.SATURDAY
		break;
	case 'E':
		dailyFigureDateDay = DayOfWeek.SUNDAY
		break;
	default:
		dailyFigureDateDay = DayOfWeek.MONDAY;
		break;
}

temAjuster = TemporalAdjusters.previousOrSame( dailyFigureDateDay );

switch(dailyFigureWeekNumber){
	case 0:
		daysBefore = 0;
		break;
	case 1:
		daysBefore = 7
		break;
	case 2:
		daysBefore = 14
		break;
	case 3:
		daysBefore = 21
		break;

	default://Seleccioanra la fecha de esta semana POR DEFECTO
		daysBefore = 0;
		break;
}

fechaDailyFigure = CustomKeywords.'com.utils.ReportHelper.getCurrentDayOfTheWeek'(DateTimeFormatter.ofPattern(
		'MM/dd/yyyy'), temAjuster ,daysBefore)

//Modifica el dia para dar el formato corecto 
String day = dailyFigureDateDay.toString();
char upperCaseInitalDayName = day.charAt(0);
day = day.toLowerCase();
day = day.replace(day.charAt(0),upperCaseInitalDayName);


return fechaDailyFigure+" "+day;
