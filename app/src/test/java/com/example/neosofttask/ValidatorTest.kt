package com.example.neosofttask

import com.example.neosofttask.utils.Constants
import com.example.neosofttask.utils.Education
import com.example.neosofttask.utils.IndianStates
import com.example.neosofttask.utils.Validator
import org.junit.Assert.*

import org.junit.Test

class ValidatorTest {

    @Test
    fun passName_validateName_returnSuccessMsg() {
        val firstName = "yash"
        var result = Validator.validateName(firstName)
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyString_validateName_returnErrorMsg() {
        val firstName = ""
        var result = Validator.validateName(firstName)
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passNameWithLessThan3Characters_validateName_returnErrorMsg() {
        val firstName = "ya"
        var result = Validator.validateName(firstName)
        assertEquals(result,Constants.charLessThan3ErrorMessage)
    }

    @Test
    fun passPhoneNumberEmpty_validatePhone_returnErrorMsg(){
        val result = Validator.validatePhone("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passPhoneNumberWith4Digits_validatePhone_returnErrorMsg(){
        val result = Validator.validatePhone("1234")
        assertEquals(result,Constants.numbersLessThan10ErrorMessage)
    }

    @Test
    fun passPhoneNumber_validatePhone_returnSuccessMsg(){
        val result = Validator.validatePhone("1234567891")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyGender_validateGender_returnErrorMsg(){
        val result = Validator.validateGender("")
        assertEquals(result,Constants.genderErrorMessage)
    }

    @Test
    fun passGender_validateGender_returnSuccessMsg(){
        val result = Validator.validateGender("Male")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyEmailId_validateEmail_returnErrorMsg(){
        val result = Validator.validateEmailId("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passInvalidEmailId_validateEmail_returnErrorMsg(){
        val result = Validator.validateEmailId("test")
        assertEquals(result,Constants.invalidEmailErrorMessage)
    }

    @Test
    fun passValidEmailId_validateEmail_returnSuccessMsg(){
        val result = Validator.validateEmailId("test@gmail.com")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyPassword_validatePassword_returnErrorMsg(){
        val result = Validator.validatePassword("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passInvalidPassword_validatePassword_returnErrorMsg(){
        val result = Validator.validatePassword("test")
        assertEquals(result,Constants.invalidPasswordErrorMessage)
    }

    @Test
    fun passValidPassword_validatePassword_returnSuccessMsg(){
        val result = Validator.validatePassword("tT@12345678")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyConfirmPassword_validateConfirmPassword_returnErrorMsg(){
        val result = Validator.validateConfirmPassword("tT@12345678","")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passValidConfirmPassword_validateConfirmPassword_returnSuccessMsg(){
        val result = Validator.validateConfirmPassword("tT@12345678","tT@12345678")
        assertEquals(result,Constants.successMsg)
    }

    //Your Info View Model
    @Test
    fun passSelectString_validateEducation_returnErrorMsg(){
        val result = Validator.validateEducation("Select")
        assertEquals(result,Constants.educationSpinnerError)
    }

    @Test
    fun passValidString_validateEducation_returnSuccessMsg(){
        val result = Validator.validateEducation(Education.BACHELOR.toString())
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passSelectString_validateYearOfPassing_returnErrorMsg(){
        val result = Validator.validateYearOfPassing("Select")
        assertEquals(result,Constants.yearOfPassSpinnerError)
    }

    @Test
    fun passValidString_validateYearOfPassing_returnSuccessMsg(){
        val result = Validator.validateYearOfPassing("2023")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyGrade_validateGrade_returnErrorMsg(){
        val result = Validator.validateGrade("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passValidGrade_validateGrade_returnSuccessMsg(){
        val result = Validator.validateGrade("A")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passValidExperience_validateExperience_returnSuccessMsg(){
        val result = Validator.validateExperience("2")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passSelectString_validateDesignation_returnErrorMsg(){
        val result = Validator.validateDesignation("Select")
        assertEquals(result,Constants.designationSpinnerError)
    }

    @Test
    fun passValidDesignation_validateDesignation_returnSuccessMsg(){
        val result = Validator.validateDesignation("JR ANDROID DEVELOPER")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passSelectString_validateDomain_returnErrorMsg(){
        val result = Validator.validateDomain("Select")
        assertEquals(result,Constants.domainSpinnerError)
    }

    @Test
    fun passValidDomain_validateDomain_returnSuccessMsg(){
        val result = Validator.validateDomain("E COMMERCE")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyString_validateAddress_returnErrorMsg() {
        var result = Validator.validateAddress("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passAddressWithLessThan3Characters_validateAddress_returnErrorMsg() {
        var result = Validator.validateAddress("Mu")
        assertEquals(result,Constants.charLessThan3ErrorMessage)
    }

    @Test
    fun passAddress_validateAddress_returnSuccessMsg() {
        var result = Validator.validateAddress("AG link road")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyString_validateLandMark_returnErrorMsg() {
        var result = Validator.validateLandmark("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passLandmarkWithLessThan3Characters_validateLandMark_returnErrorMsg() {
        var result = Validator.validateLandmark("ch")
        assertEquals(result,Constants.charLessThan3ErrorMessage)
    }

    @Test
    fun passLandmark_validateLandMark_returnSuccessMsg() {
        var result = Validator.validateLandmark("Chakala")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyString_validateCity_returnErrorMsg() {
        var result = Validator.validateCity("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passCityWithLessThan3Characters_validateCity_returnErrorMsg() {
        var result = Validator.validateCity("Mu")
        assertEquals(result,Constants.charLessThan3ErrorMessage)
    }

    @Test
    fun passCity_validateCity_returnSuccessMsg() {
        var result = Validator.validateCity("Mumbai")
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passSelectString_validateState_returnErrorMsg(){
        val result = Validator.validateState("Select")
        assertEquals(result,Constants.stateSpinnerError)
    }

    @Test
    fun passValidState_validateState_returnSuccessMsg(){
        val result = Validator.validateState(IndianStates.MAHARASHTRA.toString())
        assertEquals(result,Constants.successMsg)
    }

    @Test
    fun passEmptyString_validatePinCode_returnErrorMsg() {
        var result = Validator.validatePinCode("")
        assertEquals(result,Constants.emptyStringErrorMessage)
    }

    @Test
    fun passPinCodeWithLessThan6Characters_validatePinCode_returnErrorMsg() {
        var result = Validator.validatePinCode("123")
        assertEquals(result,Constants.numbersLessThan6ErrorMessage)
    }

    @Test
    fun passValidPinCode_validatePinCode_returnSuccessMsg() {
        var result = Validator.validatePinCode("400099")
        assertEquals(result,Constants.successMsg)
    }
}