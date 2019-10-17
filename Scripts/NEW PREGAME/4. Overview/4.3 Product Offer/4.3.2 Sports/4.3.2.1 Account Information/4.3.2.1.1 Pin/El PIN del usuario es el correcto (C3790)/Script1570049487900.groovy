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

String url = GlobalVariable.pregameUrl

String userName = GlobalVariable.customerPIN

String userPassword = GlobalVariable.customerPassword

//Si el usuario no ha ingresado al sitio llamará a la función de login
if (!(GlobalVariable.usuarioLogeado)) {
    WebUI.callTestCase(findTestCase('NEW PREGAME/2. Login/2.1 Validacion Boton Login/2.1.3 Validacion Inet Target/2.1.3.1 Inet Target Correcto/Jugador logra ingresar a Overview (C6414)'), 
        [('url') : url, ('loginUser') : userName, ('loginPassword') : userPassword], FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.usuarioLogeado = true
}

//Valdiación de pin
String userPin = CustomKeywords.'com.utils.AutomationUtils.getObjectAttribute'('User Pin', 'textContent', 'css', '.CustomerID', 
    2)

assert userName.equalsIgnoreCase(userPin)

