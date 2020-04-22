import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8081')

WebUI.click(findTestObject('Page_MentorArc/a_Register'))

WebUI.setText(findTestObject('Page_MentorArc/Page_Register/input_Registration Form_email'), 'mentor@mentor.com')

WebUI.setText(findTestObject('Page_MentorArc/Page_Register/input_Registration Form_username'), 'mentor')

WebUI.setText(findTestObject('Page_MentorArc/Page_Register/input_Registration Form_password'), 'mentormentor')

WebUI.click(findTestObject('Page_MentorArc/Page_Register/span_MENTOR'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_MentorArc/Page_Register/span_POULAIN'))

WebUI.click(findTestObject('Page_MentorArc/Page_Register/button_Register User'))

WebUI.click(findTestObject('Page_MentorArc/a_Login'))

WebUI.setText(findTestObject('Page_MentorArc/Page_Login/input_Login_email'), 'mentor@mentor.com')

WebUI.setText(findTestObject('Page_MentorArc/Page_Login/input_Login_password'), 'mentormentor')

WebUI.click(findTestObject('Page_MentorArc/Page_Login/button_Login'))

