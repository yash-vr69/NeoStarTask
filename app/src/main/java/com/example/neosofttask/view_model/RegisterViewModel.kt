package com.example.neosofttask.view_model

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Validator
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.model.UsersData
import com.example.neosofttask.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
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
    val profilePic = MutableLiveData<Uri>()

    private lateinit var usersData: UsersData
    private var uniqueId by Delegates.notNull<Long>()

    private val _errorMsgFirstName = MutableSharedFlow<String>()
    val firstNameErrorMsg: MutableSharedFlow<String> = _errorMsgFirstName

    private val _errorMsgLastName = MutableSharedFlow<String>()
    val lastNameErroMsg = _errorMsgLastName

    private val _errorMsgPhoneNumber = MutableSharedFlow<String>()
    val phoneErroMsg = _errorMsgPhoneNumber

    private val _errorMsgEmail = MutableSharedFlow<String>()
    val emailErroMsg = _errorMsgEmail

    private val _errorMsgPassword = MutableSharedFlow<String>()
    val passwordErroMsg = _errorMsgPassword

    private val _errorMsgConfirmPassword = MutableSharedFlow<String>()
    val confirmPassErroMsg = _errorMsgConfirmPassword

    private val _errorMsgGender = MutableSharedFlow<String>()
    val genderErroMsg = _errorMsgGender

    private val _actionData = MutableLiveData<Long>()
    val actionDataForView: LiveData<Long> get() = _actionData

    fun validate() = viewModelScope.launch{

        if(!Validator.validateName(firstName.value).equals(Constants.successMsg)){
            _errorMsgFirstName.emit(Validator.validateName(firstName.value))
        }
        else if(!Validator.validateName(lastName.value).equals(Constants.successMsg)){
            _errorMsgLastName.emit(Validator.validateName(lastName.value))
        }
        else if(!Validator.validatePhone(phoneNumber.value).equals(Constants.successMsg)){
            _errorMsgPhoneNumber.emit(Validator.validatePhone(phoneNumber.value))
        }
        else if(!Validator.validateEmailId(emailId.value).equals(Constants.successMsg)){
            _errorMsgEmail.emit(Validator.validateEmailId(emailId.value))
        }
        else if(!Validator.validateGender(gender.value).equals(Constants.successMsg)){
            _errorMsgGender.emit(Validator.validateGender(gender.value))
        }
        else if(!Validator.validatePassword(password.value).equals(Constants.successMsg)){
            _errorMsgPassword.emit(Validator.validatePassword(password.value))
        }
        else if(!Validator.validateConfirmPassword(password.value,confirmPassword.value).equals(Constants.successMsg)){
            _errorMsgConfirmPassword.emit(Validator.validateConfirmPassword(password.value,confirmPassword.value))
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
    }
}
