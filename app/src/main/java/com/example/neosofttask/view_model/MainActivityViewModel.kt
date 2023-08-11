package com.example.neosofttask.view_model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.model.UsersData
import com.example.neosofttask.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val usersRepository: UsersRepository) :
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
        if (firstName.value.isNullOrEmpty()) {
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
        }
    }

    private fun emailValidator(usersEmail: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(usersEmail).matches()

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
}
