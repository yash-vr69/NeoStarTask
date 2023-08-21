package com.example.neosofttask.utils

import androidx.core.util.PatternsCompat

object Validator {

    //RegisterViewModel
    fun validateName(name: String?): String {
        var result = ""
        when {
            name.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            name.length?.compareTo(3) == -1 -> result = Constants.charLessThan3ErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validatePhone(phoneNumber: String?): String{
        var result = ""
        when {
            phoneNumber.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            phoneNumber.length?.compareTo(10) == -1 -> result = Constants.numbersLessThan10ErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateEmailId(emailId: String?): String{
        var result = ""
        when {
            emailId.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            emailValidator(emailId) == false -> result = Constants.invalidEmailErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateGender(gender: String?): String{
        var result = ""
        if(gender.isNullOrEmpty() == true){
            result = Constants.genderErrorMessage
        }else{
            result = Constants.successMsg
        }
        return result
    }

    fun validatePassword(password: String?): String{
        var result = ""
        when {
            password.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            passwordValidator(password) == false -> result = Constants.invalidPasswordErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateConfirmPassword(password: String?,confirmPassword: String?): String{
        var result = ""
        when {
            confirmPassword.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            password != confirmPassword -> result = Constants.passwordNotMatchedErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    private fun emailValidator(usersEmail: String): Boolean = PatternsCompat.EMAIL_ADDRESS.matcher(usersEmail).matches()

    private fun passwordValidator(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }
                .firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }
                .firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false
        return true
    }

//    YourInfoViewModel
    fun validateEducation(education: String): String{
    var result = ""
        when{
            education.equals("Select",true) -> result = Constants.educationSpinnerError
            else -> result = Constants.successMsg
        }
    return result
    }

    fun validateYearOfPassing(yearOfPassing: String): String{
        var result = ""
        when{
            yearOfPassing.equals("Select",true) -> result = Constants.yearOfPassSpinnerError
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateGrade(grade: String?): String{
        var result = ""
        when{
            grade.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateExperience(experience: String?): String{
        var result = ""
        when{
            experience.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateDesignation(designation: String): String{
        var result = ""
        when{
            designation.equals("Select",true) -> result = Constants.designationSpinnerError
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateDomain(domain: String): String{
        var result = ""
        when{
            domain.equals("Select",true) -> result = Constants.domainSpinnerError
            else -> result = Constants.successMsg
        }
        return result
    }

    // AddressViewModel
    fun validateAddress(address: String?): String {
        var result = ""
        when {
            address.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            address.length?.compareTo(3) == -1 -> result = Constants.charLessThan3ErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateLandmark(landMark: String?): String {
        var result = ""
        when {
            landMark.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            landMark.length?.compareTo(3) == -1 -> result = Constants.charLessThan3ErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateCity(city: String?): String {
        var result = ""
        when {
            city.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            city.length?.compareTo(3) == -1 -> result = Constants.charLessThan3ErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validateState(state: String): String{
        var result = ""
        when{
            state.equals("Select",true) -> result = Constants.stateSpinnerError
            else -> result = Constants.successMsg
        }
        return result
    }

    fun validatePinCode(pinCode: String?): String {
        var result = ""
        when {
            pinCode.isNullOrEmpty() -> result = Constants.emptyStringErrorMessage
            pinCode.length?.compareTo(6) == -1 -> result = Constants.numbersLessThan6ErrorMessage
            else -> result = Constants.successMsg
        }
        return result
    }

}