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
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.lang.AssertionError as AssertionError
import org.bouncycastle.operator.KeyWrapper as KeyWrapper
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.testobject.ConditionType as ConditionType

//dynamic test object attirbutes
final String CSS_SELECTOR = CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'()

final ConditionType equalsCondType = CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'()

final String textContentAtribute = CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'()

final String EMPTY_SPACE= " ";

//-----------------------Future props variables ------------------------------------//
//Extre del sitio el idioma del mismo
String languageKey = ''

//Descripción del sub deporte
String gameDesc = ''

//Define el tipo de sub deporte, puede ser prop o game
String oddGameType = ''

//Descripción del prop del deporte asociado
String futurePropsSubSportGroupDesc = ''

//Id para ubicar el contestNum
String futurePropId = ''

//Alamcena el nombre del contestant del future prop
String futurePropContestantName = ''

String futurePropContestName = ''

//-------------------------------Lines variables--------------------------------//
String leagueMatchTitle = '';

String lineType = '';

String lineValue = '';

String gameNum = '';

String lineChoosenTeam = '';

String linePeriod = '';

String awayTeamDesc = '';

String homeTeamDesc = '';

String threshold = '';


//-------------------------BetSlip varibles ------------------------------------//
//Encabezado del la apuesta
String betSlipWagerHeaderDesc = ''

//Alamacena la descripcion del contestant en el betSlip
String betSlipContestantDesc = ''

//Alamacena la descripcion del contest en el betSlip
String betSlipContestDesc = ''

//alamcena el valor del contestant selecioando
String propLine = ''

String betslipThreshold = '';

String teams = '';

String betslipWagerLineValue = '';

String betSlipWagerThreshold = ' ';

Map<String, String> oddGameTypesMap = GlobalVariable.oddDescpirtion



//----Future props objects -----//
TestObject selectedSubSportGame = new TestObject('sub sport Game')

TestObject firstPropObject = null

TestObject futurePropsGroupObject = null

TestObject futureContestGroup = null

TestObject futureContestObj = null

TestObject futurePropLineDescObject = null

TestObject propGroupSeasonObject = null


KeywordLogger log = new KeywordLogger()

//----------future props selectors ----------------------------------//
String propContestSelector = ' div.selectionTable div.selectionFix div div.selectionDesc '

String tournamentContestGroupSelector = ''

//------------------------Sports selectors-------------------------------//
String subSportSelector = '.sportsPanelWrap div:nth-child(6) .panel-group ul li div.table div div.ellipsis'


//--------------------------lines selectors--------------------------------------//
String subSportContentCSSSelector ='div#groupsWrap div#groupsWrapLong div.group';
String subSporttitleCSSSelector = subSportContentCSSSelector.concat(EMPTY_SPACE).concat('div.title');

String linePeriodCSSSelector = 'div#sliderID button span';

String gameNumAwayTeamCSSSelector = ' div#groupPeriodDesktop table.table-games.sort tbody tr[class~="away"]';

String gameNumHomeTeamCSSSelector = ' div#groupPeriodDesktop table.table-games.sort tbody tr[class~="home"]';

String awayTeamDescCSSSelector = gameNumAwayTeamCSSSelector.concat(EMPTY_SPACE).concat("td.border-bottom.teamInfo");

String homeTeamDescCSSSelector = gameNumHomeTeamCSSSelector.concat(EMPTY_SPACE).concat("td.border-bottom.teamInfo");

String linebuttonCSSSelector =gameNumAwayTeamCSSSelector.concat(' td:nth-child(3) button[gamenum="?"]') ;
//-----------betslip selectors----------------//
// Css
String betSlipBoxSelector = 'div.selections.sort div.selection.secret div.panel.panel-default'

String betSlipWagerDescSelector = betSlipBoxSelector + ' div.panel-heading.pannel-heading-2'

String betSlipHeaderSelector = 'div.selections.sort div.selection.secret div.panel.panel-default div.panel-heading.pannel-heading-2'

String betSlipBodySelector = 'div.selections.sort div.selection.secret div.panel.panel-default div.panel-body'

String betSlipContestantSelector = betSlipBodySelector + ' div div.desc'

String betSlipContestSelector = betSlipBodySelector + ' div div.descBet'

String betSlipthresholdCCSSelector = betSlipBodySelector.concat(' div div.selectionDescWrap div.oddsWrap div.defaultOdds div.threshold')

String betSlipLineSelector = betSlipBodySelector.concat(' div div.selectionDescWrap div.oddsWrap div.defaultOdds div.odds')

String betSlipTournamentContestSelector = betSlipBodySelector.concat('')

//-----------betslip objects ------------------------------//
TestObject betSliptHeader = null

TestObject betSlipBodyContestant = null

TestObject betSlipBodyContest = null

TestObject betSlipBodyLineWager = null

TestObject betSlipBodyThreshold = null;

//------------------------------ future props objects ---------------------------------//
TestObject contestTournamentObject = new TestObject('Contest Tournament Object')

//------------------------------ lines Objects -------------------------------------//
TestObject gameLeagueTitleObj =  new TestObject('League title');
TestObject linePeridodObject = new TestObject('Game Type');
TestObject gameNumObject = new TestObject('Game Num');
TestObject lineButtonObject = null;
TestObject teamAwayDescObject = new TestObject('Team Away Desc');
TestObject teamHomeDescObject  = new TestObject('Team Home Desc');
//Carga del excel
CustomKeywords.'com.utils.ExcelsUtils.loadFileInputStream'(GlobalVariable.lineasExcelRuta)

//Abre archivo lectura
CustomKeywords.'com.utils.ExcelsUtils.createReadXSSFWorkbook'()

//Selecciona la hoja del execl
CustomKeywords.'com.utils.ExcelsUtils.loadXSSFSheet'('Pregame')

String url = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 1)

String userName = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 2)

String userPassword = CustomKeywords.'com.utils.ExcelsUtils.getColumValue'(1, 3)

String startHour = CustomKeywords.'com.utils.DateUtil.getHours'()

//Registro fecha incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 4, CustomKeywords.'com.utils.DateUtil.getDate'())

//Registro  hora  incio de la prueba
CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 5, startHour)

try {
	if (!(GlobalVariable.usuarioLogeado)) {
		WebUI.callTestCase(findTestCase('PREGAME/SportBook/Login_Successfull'), [('url') : url, ('loginUser') : userName
			, ('loginPassword') : userPassword], FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.usuarioLogeado = true
	}

	selectedSubSportGame.addProperty(CSS_SELECTOR, equalsCondType, subSportSelector)

	WebUI.delay(2);

	WebUI.waitForElementVisible(selectedSubSportGame, 3)

	WebUI.waitForElementClickable(selectedSubSportGame, 3)

	WebUI.waitForElementNotVisible(findTestObject('Repositorio Sportbook/RevisarLineasPregame/InitModal'), 4);
	WebUI.waitForElementNotPresent(findTestObject('Repositorio Sportbook/RevisarLineasPregame/InitModal'), 4);

	WebUI.click(selectedSubSportGame)

	gameDesc = WebUI.getAttribute(selectedSubSportGame, textContentAtribute, FailureHandling.STOP_ON_FAILURE)

	gameDesc = gameDesc.replace(' ', '').trim()

	languageKey = WebUI.getAttribute(findTestObject('Repositorio Sportbook/RevisarLineasPregame/span_English'), textContentAtribute,
			FailureHandling.STOP_ON_FAILURE)

	oddGameType = oddGameTypesMap.get(languageKey)

	//Juegos de tipo propocisiones
	if (gameDesc.equalsIgnoreCase(oddGameType)) {
		firstPropObject = new TestObject('FirstFuturePropMenu')

		firstPropObject.addProperty(CSS_SELECTOR, equalsCondType, '#groupsWrapLong div div div:nth-child(2)')

		WebUI.waitForElementClickable(firstPropObject, 2)

		//Click para seleccionar props del lado izquierdo
		WebUI.click(firstPropObject)

		futurePropsGroupObject = new TestObject('futurePropsGroupObject')

		futurePropsGroupObject.addProperty(CSS_SELECTOR, equalsCondType, 'div#groupsWrapLong div div')

		futurePropsSubSportGroupDesc = WebUI.getAttribute(futurePropsGroupObject, textContentAtribute, FailureHandling.STOP_ON_FAILURE)

		futurePropsSubSportGroupDesc = futurePropsSubSportGroupDesc.replace(' ', '').trim()

		futureContestGroup = new TestObject('futureContestGroup')

		futureContestGroup.addProperty(CSS_SELECTOR, equalsCondType, 'div[id^=group_contest]')

		futurePropId = WebUI.getAttribute(futureContestGroup, 'contestid', FailureHandling.STOP_ON_FAILURE)

		tournamentContestGroupSelector = (('div[id^=group_contest_' + futurePropId) + '] div.title.header div.ellipsis div')

		futurePropId = (('li[contestnum=\'' + futurePropId) + '\']')

		futureContestObj = new TestObject('futureContestObj')

		futureContestObj.addProperty(CSS_SELECTOR, equalsCondType, futurePropId.concat(propContestSelector))

		futurePropContestantName = WebUI.getAttribute(futureContestObj, textContentAtribute, FailureHandling.STOP_ON_FAILURE)

		futurePropContestantName = futurePropContestantName.replace(' ', '').trim()

		futurePropLineDescObject = new TestObject('futurePropLineDesc')

		futurePropLineDescObject.addProperty(CSS_SELECTOR, equalsCondType, futurePropId + ' div.selectionTable div.selectionFix div div:nth-child(2)')

		propLine = WebUI.getAttribute(futurePropLineDescObject, textContentAtribute, FailureHandling.STOP_ON_FAILURE)

		propLine = propLine.replace(' ', '')

		propLine = propLine.replace('\n', '')

		propLine = propLine.substring(2)

		//Click al primer contest que encuentra para halilitar el betslip
		WebUI.click(futureContestObj)

		//Seleccion del encabezado del betslip
		betSlipWagerHeaderDesc = loadbetSlipDesc(betSliptHeader,betSlipWagerHeaderDesc,betSlipHeaderSelector)

		//Comparación del encabezado del betSlip con el encabezado de los proprs del sub deporte
		assert betSlipWagerHeaderDesc.equals(futurePropsSubSportGroupDesc)

		betSlipContestantDesc =	loadBetSlipContestantDesc(betSlipBodyContestant,betSlipContestantDesc,betSlipContestantSelector)

		//Comparación para asegurar que la descipción de la apuesta con la descipción de contestant  seleccioando
		assert futurePropContestantName.equals(betSlipContestantDesc)

		betslipWagerLineValue =	getBetslipWagerLineValue(betSlipBodyLineWager,betslipWagerLineValue,betSlipLineSelector);

		//comparación de la línea del betslip sea igual a la línea seleccionada en el contestant
		assert propLine.equals(betslipWagerLineValue)

		//Seleccion de elementos para comparar el torneo del contest con la descripcion del torneo  del bestsilp
		contestTournamentObject.addProperty(CSS_SELECTOR, equalsCondType, tournamentContestGroupSelector)

		futurePropContestName = WebUI.getAttribute(contestTournamentObject, textContentAtribute, FailureHandling.STOP_ON_FAILURE)

		futurePropContestName = futurePropContestName.replace(" ","").trim();

		TestObjectProperty betSlipContestObjProperty = new  TestObjectProperty();
		
		betSlipContestDesc =	getBetSlipContestDesc(betSlipBodyContest,betSlipContestDesc,betSlipContestSelector);
		
		//Valida que la descripción del betslip sea igual al encabezado del contest de torneo
		assert betSlipContestDesc.equals(futurePropContestName)
		
		
	} else {
		//validación de lineas  del juego
		TestObjectProperty linePeriodPropierty = new TestObjectProperty(CSS_SELECTOR, equalsCondType, linePeriodCSSSelector);

		linePeriod = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Line Period Obj", textContentAtribute, linePeriodPropierty, 2);

		TestObjectProperty lineMenuPreperty = new TestObjectProperty(CSS_SELECTOR, equalsCondType, 'div#groupsWrapLong div div div');

		CharSequence lineMenuColapsed = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Sport line menu", 'class',lineMenuPreperty, 2);

		//Hace click para espandir el menu del juego seleccioando si es necesario
		if(lineMenuColapsed.contains("collapsed")){
			CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(null, 'Line menu', lineMenuPreperty, 4);
		}
		
		TestObjectProperty gameNumProperty = new TestObjectProperty(CSS_SELECTOR, equalsCondType, gameNumAwayTeamCSSSelector);

		gameNum = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Game Num", 'gamenum',gameNumProperty, 2);

		linebuttonCSSSelector = linebuttonCSSSelector.replace("?",gameNum);

		TestObjectProperty lineButtonObjProperty =  new TestObjectProperty(CSS_SELECTOR, equalsCondType, linebuttonCSSSelector);

	    lineButtonObject = CustomKeywords.'com.utils.AutomationUtils.clickAndReturnObject'(lineButtonObject, 'Line Button', lineButtonObjProperty, 6)
	/*	lineButtonObject = new TestObject('Linea de juego');
		lineButtonObject.addProperty(lineButtonObjProperty);

		WebUI.waitForElementVisible(lineButtonObject , 4)
		
		WebUI.waitForElementClickable(lineButtonObject , 6)
		WebUI.click(lineButtonObject);*/

		lineChoosenTeam = WebUI.getAttribute(lineButtonObject, 'chosenteamid', FailureHandling.STOP_ON_FAILURE);

		linePeriod = WebUI.getAttribute(lineButtonObject, 'groupdescription', FailureHandling.STOP_ON_FAILURE);

		lineType =  WebUI.getAttribute(lineButtonObject, 'betdescription', FailureHandling.STOP_ON_FAILURE);

			//Carga info team away
		teamAwayDescObject.addProperty(CSS_SELECTOR, equalsCondType, awayTeamDescCSSSelector);

		awayTeamDesc =  WebUI.getAttribute(teamAwayDescObject, 'innerText', FailureHandling.STOP_ON_FAILURE);

		awayTeamDesc = formatString(awayTeamDesc,"[0-9]+","",false);

		TestObjectProperty teamHomeObjProperty = new TestObjectProperty(CSS_SELECTOR, equalsCondType, homeTeamDescCSSSelector);

		homeTeamDesc =  CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Team Home Desc", 'innerText', teamHomeObjProperty);

		homeTeamDesc = formatString(homeTeamDesc,"[0-9]+","",false);

		teams = homeTeamDesc+'VS'+awayTeamDesc;

		teams = teams.toUpperCase();

		TestObjectProperty betLipWagerObjProperty = new TestObjectProperty(CSS_SELECTOR, equalsCondType,betSlipHeaderSelector );
		betSlipWagerHeaderDesc =  CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Wager Obj property", textContentAtribute, betLipWagerObjProperty);
		
		betSlipWagerHeaderDesc = betSlipWagerHeaderDesc.toUpperCase();
		
		betSlipWagerHeaderDesc = betSlipWagerHeaderDesc.replace(" ","").trim();
		
		//Comparacion equipo de los equipos de la contestant con los equipos del betslip
		assert betSlipWagerHeaderDesc.equals(teams);
		
		betLipWagerObjProperty = new TestObjectProperty(CSS_SELECTOR, equalsCondType,betSlipContestantSelector );
		betSlipContestantDesc =   CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Wager Slip Contestant Desc", textContentAtribute, betLipWagerObjProperty);
		
		//Valdiación de equipo seleccionado
		betSlipContestantDesc.equals(awayTeamDesc);
	
		betslipWagerLineValue =   CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Wager Slip line value", textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType,betSlipLineSelector ));
		
		threshold  =  WebUI.getAttribute(lineButtonObject, 'threshold', FailureHandling.STOP_ON_FAILURE);
		
	    threshold = CustomKeywords.'com.utils.ConstantsUtil.notEmptyString'(threshold)?threshold.replace(".5","½"):"";
	   
	   //betSlipthresholdCCSSelector
		betSlipWagerThreshold =   CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Wager Slip threshold value", textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType,betSlipthresholdCCSSelector ));
		
		lineValue = WebUI.getAttribute(lineButtonObject, 'us', FailureHandling.STOP_ON_FAILURE);
		
		lineValue = threshold+lineValue;
		
		betslipWagerLineValue = CustomKeywords.'com.utils.ConstantsUtil.notEmptyString'(betSlipWagerThreshold)?betSlipWagerThreshold+betslipWagerLineValue:betslipWagerLineValue;
			
		//Validación de la línea
        assert betslipWagerLineValue.equals(lineValue);	

		/*
		betSlipContestDesc =	getBetSlipContestDesc(betSlipBodyContest,betSlipContestDesc,betSlipContestSelector);
		
		//Valida que la descripción del betslip sea igual al encabezado del contest de torneo
		assert betSlipContestDesc.equals(futurePropContestName)*/
        
		betSlipContestDesc = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'("Wager Bet Slip game period tipe", textContentAtribute, new TestObjectProperty(CSS_SELECTOR, equalsCondType,betSlipContestSelector ));
		
		String lineWagerPeriodTypeDesc = linePeriod.concat(EMPTY_SPACE).concat("-").concat(EMPTY_SPACE).concat(lineType);
		
		assert lineWagerPeriodTypeDesc.equals(betSlipContestDesc.trim())
	}

	//Guarda Sistema operativo
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 8, CustomKeywords.'mycompany.GetTestingConfig.getOperatingSystem'())

	//Guarda Resoluci�n
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 10, CustomKeywords.'mycompany.GetTestingConfig.getScreenResolution'())

	//Guarda Version del browser
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 9, CustomKeywords.'mycompany.GetTestingConfig.getBrowserAndVersion'())

	WebUI.verifyElementVisible(findTestObject('Repositorio Sportbook/Permisos/Horses/div_Caballos'))

	String permiso = WebUI.getAttribute(findTestObject('Repositorio Sportbook/Permisos/Horses/div_Caballos'), textContentAtribute)

	//Guarda permito otorgado
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 11, permiso)

	//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, CustomKeywords.'com.utils.DateUtil.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.DateUtil.getDate'())

	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.PASSED)

	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Permiso Horses es correctamente accesible para el usuario')
}
catch (StepFailedException step) {
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)

	KeywordUtil.logger.logError(' Error en validar pasos de la prueba  ' + step)

	//Guara descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validacion de permiso fallida por incumplimiento de verifaicación de elementos en la página')

	throw new AssertionError('Error en la prueba revisión de líneas debido a que hay un paso que no se cumplio', step)
}
catch (Exception ex) {
	//Guara estado de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 12, log.FAILED)

	//Guarda descripci�n de la prueba
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 13, 'Validacion de permiso fallida causado por excepción inesperada')

	KeywordUtil.logger.logError(' Excepcion general ' + ex.getMessage())

	throw new AssertionError('Error en la prueba horses ', ex)
}
finally {
	//Guarda hora final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 7, CustomKeywords.'com.utils.DateUtil.getHours'())

	//Guarda fecha final
	CustomKeywords.'com.utils.ExcelsUtils.saveDataOnExcel'(1, 6, CustomKeywords.'com.utils.DateUtil.getDate'())

	//Cierra archivo de lectura
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()

	//Abre  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.loadFileOutputStream'(GlobalVariable.lineasExcelRuta)

	//escribe informacion en la hoja del exec
	CustomKeywords.'com.utils.ExcelsUtils.writeOutputExcelSheet'()

	//Cierra  archivo de escritua
	CustomKeywords.'com.utils.ExcelsUtils.closeFileInStream'()
}

def  String loadbetSlipDesc(TestObject betSliptHeader,String betSlipWagerHeaderDesc,String betSlipHeaderSelector) {
	betSliptHeader = new TestObject("BetSlip Header");

	betSliptHeader.addProperty(CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'(), CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'(),betSlipHeaderSelector );

	WebUI.waitForElementVisible(betSliptHeader, 2, FailureHandling.STOP_ON_FAILURE);

	betSlipWagerHeaderDesc =WebUI.getAttribute(betSliptHeader, CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'(), FailureHandling.STOP_ON_FAILURE)
	betSlipWagerHeaderDesc = betSlipWagerHeaderDesc.replace(" ","").trim();

	return betSlipWagerHeaderDesc;
}

def  String loadBetSlipContestantDesc(TestObject betSlipBodyContestant,String betSlipContestantDesc,String betSlipContestantSelector) {
	betSlipBodyContestant = new TestObject('BetSlip Contestant')

	betSlipBodyContestant.addProperty(CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'(), CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'(), betSlipContestantSelector)

	betSlipContestantDesc = WebUI.getAttribute(betSlipBodyContestant, CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'(), FailureHandling.STOP_ON_FAILURE)

	betSlipContestantDesc = betSlipContestantDesc.replace(' ', '').trim()

	return betSlipContestantDesc;
}

def String getBetslipWagerLineValue(TestObject betSlipBodyLineWager,String betslipWagerLineValue,String betSlipLineSelector){
	betSlipBodyLineWager = new TestObject('BetSlip Line Oject')

	betSlipBodyLineWager.addProperty(CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'(), CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'(), betSlipLineSelector)

	betslipWagerLineValue = WebUI.getAttribute(betSlipBodyLineWager, CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'(), FailureHandling.STOP_ON_FAILURE)

	return betslipWagerLineValue;
}

def String getBetSlipContestDesc(betSlipBodyContest,String betSlipContestDesc,String betSlipContestSelector){
	betSlipBodyContest = new TestObject('BetSlip Contest')

	betSlipBodyContest.addProperty(CustomKeywords.'com.utils.ConstantsUtil.getCSSSelectorId'(), CustomKeywords.'com.utils.ConstantsUtil.getEqualsConditionType'(), betSlipContestSelector)

	betSlipContestDesc = WebUI.getAttribute(betSlipBodyContest, CustomKeywords.'com.utils.ConstantsUtil.getHtmlTextContentAtt'(), FailureHandling.STOP_ON_FAILURE)

	betSlipContestDesc = betSlipContestDesc.trim();

	return betSlipContestDesc;
}


def String formatString(String unformatedString,String clearPattern,String replaceValue,boolean uppercase){
	String formmatedString = "";
	int emptyPos = 0;

	if(CustomKeywords.'com.utils.ConstantsUtil.notEmptyString'(clearPattern)){
		formmatedString = unformatedString.replaceAll(clearPattern,replaceValue);
		formmatedString = formmatedString.replace(" ","");
		formmatedString  = formmatedString.replace("\n","");
		formmatedString = formmatedString.substring(1);
	}

	if(uppercase){
		formmatedString = formmatedString.toUpperCase();
	}

	return formmatedString;
}