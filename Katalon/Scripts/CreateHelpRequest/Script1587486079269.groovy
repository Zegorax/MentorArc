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

WebUI.click(findTestObject('Page_MentorArc/Page_MentorArc/a_Create a request'))

WebUI.setText(findTestObject('Page_MentorArc/Page_Form HelpProposition/input_Branch_branch'), 'English')

WebUI.executeJavaScript('$(\'#dateBegin\').pickadate().pickadate(\'picker\').set(\'select\', new Date(2099, 1, 1))', [])

WebUI.setText(findTestObject('Page_MentorArc/Page_Form HelpProposition/input_Ok_timeBegin'), '15:00')

WebUI.executeJavaScript('$(\'#dateEnd\').pickadate().pickadate(\'picker\').set(\'select\', new Date(2099, 1, 2))', [])

WebUI.setText(findTestObject('Page_MentorArc/Page_Form HelpProposition/input_Ok_timeEnd'), '16:00')

WebUI.setText(findTestObject('Page_MentorArc/Page_Form HelpProposition/input_Comment_comment'), 'I can help for English.')

WebUI.click(findTestObject('Page_MentorArc/Page_Form HelpProposition/input_Comment_waves-button-input'))

