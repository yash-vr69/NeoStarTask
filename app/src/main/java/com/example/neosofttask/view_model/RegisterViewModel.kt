package com.example.neosofttask.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Validator
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.model.UsersData
import com.example.neosofttask.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class RegisterViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val emailId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val gender = MutableLiveData<String>()

    private lateinit var usersData: UsersData
    private var uniqueId by Delegates.notNull<Long>()

    private val _errorMsgFirstName = MutableLiveData<String>()
    val firstNameErroMsg: LiveData<String> get() = _errorMsgFirstName

    private val _errorMsgLastName = MutableLiveData<String>()
    val lastNameErroMsg: LiveData<String> get() = _errorMsgLastName

    private val _errorMsgPhoneNumber = MutableLiveData<String>()
    val phoneErroMsg: LiveData<String> get() = _errorMsgPhoneNumber

    private val _errorMsgEmail = MutableLiveData<String>()
    val emailErroMsg: LiveData<String> get() = _errorMsgEmail

    private val _errorMsgPassword = MutableLiveData<String>()
    val passwordErroMsg: LiveData<String> get() = _errorMsgPassword

    private val _errorMsgConfirmPassword = MutableLiveData<String>()
    val confirmPassErroMsg: LiveData<String> get() = _errorMsgConfirmPassword

    private val _errorMsgGender = MutableLiveData<String>()
    val genderErroMsg: LiveData<String> get() = _errorMsgGender

    private val _actionData = MutableLiveData<Long>()
    val actionDataForView: LiveData<Long> get() = _actionData

    fun validate() {

        if(!Validator.validateName(firstName.value).equals(Constants.successMsg)){
            _errorMsgFirstName.value = Validator.validateName(firstName.value)
        }
        else if(!Validator.validateName(lastName.value).equals(Constants.successMsg)){
            _errorMsgLastName.value = Validator.validateName(lastName.value)
        }
        else if(!Validator.validatePhone(phoneNumber.value).equals(Constants.successMsg)){
            _errorMsgPhoneNumber.value = Validator.validatePhone(phoneNumber.value)
        }
        else if(!Validator.validateEmailId(emailId.value).equals(Constants.successMsg)){
            _errorMsgEmail.value = Validator.validateEmailId(emailId.value)
        }
        else if(!Validator.validateGender(gender.value).equals(Constants.successMsg)){
            _errorMsgGender.value = Validator.validateGender(gender.value)
        }
        else if(!Validator.validatePassword(password.value).equals(Constants.successMsg)){
            _errorMsgPassword.value = Validator.validatePassword(password.value)
        }
        else if(!Validator.validateConfirmPassword(password.value,confirmPassword.value).equals(Constants.successMsg)){
            _errorMsgConfirmPassword.value = Validator.validateConfirmPassword(password.value,confirmPassword.value)
        }else{

            viewModelScope.launch {
                usersData = UsersData(
                    firstName.value.toString(),
                    lastName.value.toString(),
                    phoneNumber.value.toString(),
                    emailId.value.toString(),
                    gender.value.toString(),
                    password.value.toString(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null)

                uniqueId = usersRepository.insertRegistrationDetails(usersData)
                _actionData.value = uniqueId

            }

        }

//        _errorMsgFirstName.value = Validator.validateName(firstName.value)
//        _errorMsgLastName.value = Validator.validateName(lastName.value)
//        _errorMsgPhoneNumber.value = Validator.validatePhone(phoneNumber.value)
//        _errorMsgEmail.value = Validator.validateEmailId(emailId.value)
//        _errorMsgGender.value = Validator.validateGender(gender.value)
//        _errorMsgPassword.value = Validator.validatePassword(password.value)
//        _errorMsgConfirmPassword.value = Validator.validateConfirmPassword(password.value,confirmPassword.value)

//        if(_errorMsgFirstName.value?.equals(Constants.successMsg) == true && _errorMsgLastName.value?.equals(Constants.successMsg) == true && _errorMsgPhoneNumber.value?.equals(Constants.successMsg) == true
//            && _errorMsgEmail.value?.equals(Constants.successMsg) == true && _errorMsgGender.value?.equals(Constants.successMsg) == true && _errorMsgPassword.value?.equals(Constants.successMsg) == true
//            && _errorMsgConfirmPassword.value?.equals(Constants.successMsg) == true){
//
//            viewModelScope.launch {
//                usersData = UsersData(
//                    firstName.value.toString(),
//                    lastName.value.toString(),
//                    phoneNumber.value.toString(),
//                    emailId.value.toString(),
//                    gender.value.toString(),
//                    password.value.toString(),
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null)
//
//                uniqueId = usersRepository.insertRegistrationDetails(usersData)
//                _actionData.value = uniqueId
//
//        }

        /*if (firstName.value.isNullOrEmpty()) {
            _errorMsgFirstName.value = Constants.emptyStringErrorMessage
        }else if (firstName.value?.length?.compareTo(3) == -1) {
            _errorMsgFirstName.value = Constants.charLessThan3ErrorMessage
        }else if (lastName.value.isNullOrEmpty()) {
            _errorMsgLastName.value = Constants.emptyStringErrorMessage
        }else if (lastName.value?.length?.compareTo(3) == -1) {
            _errorMsgLastName.value = Constants.charLessThan3ErrorMessage
        }else if (phoneNumber.value.isNullOrEmpty()) {
            _errorMsgPhoneNumber.value = Constants.emptyStringErrorMessage
        }else if (phoneNumber.value?.length?.compareTo(10) == -1) {
            _errorMsgPhoneNumber.value = Constants.numbersLessThan10ErrorMessage
        }else if (emailId.value.isNullOrEmpty()) {
            _errorMsgEmail.value = Constants.emptyStringErrorMessage
        }else if (!emailValidator(emailId.value.toString())) {
            _errorMsgEmail.value = Constants.invalidEmailErrorMessage
        }else if(gender.value.isNullOrEmpty()){
            _errorMsgGender.value = Constants.genderErrorMessage
        }else if (password.value.isNullOrEmpty()) {
            _errorMsgPassword.value = Constants.emptyStringErrorMessage
        }else if (!passwordValidator(password.value.toString())) {
            _errorMsgPassword.value = Constants.invalidPasswordErrorMessage
        }else if (confirmPassword.value.isNullOrEmpty()) {
            _errorMsgConfirmPassword.value = Constants.emptyStringErrorMessage
        } else if (!password.value.equals(confirmPassword.value)) {
            _errorMsgConfirmPassword.value = Constants.passwordNotMatchedErrorMessage
        } else {
            //insert into database
            viewModelScope.launch {
               usersData = UsersData(
                   firstName.value.toString(),
                   lastName.value.toString(),
                   phoneNumber.value.toString(),
                   emailId.value.toString(),
                   gender.value.toString(),
                   password.value.toString(),
                   null,
                   null,
                   null,
                   null,
                   null,
                   null,
                   null,
                   null,
                   null,
                   null,
                   null)

                uniqueId = usersRepository.insertRegistrationDetails(usersData)
                _actionData.value = uniqueId
            }
        }*/
    }

}
//    }
