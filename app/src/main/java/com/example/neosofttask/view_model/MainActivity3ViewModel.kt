package com.example.neosofttask.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivity3ViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

    val address = MutableLiveData<String>()
    val landmark = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val state = MutableLiveData<String>()
    val pincode = MutableLiveData<String>()

    private val _errorMsgAddress = MutableLiveData<String>()
    val addressErroMsg: LiveData<String> get() = _errorMsgAddress

    private val _errorMsgLandmark = MutableLiveData<String>()
    val landmarkErroMsg: LiveData<String> get() = _errorMsgLandmark

    private val _errorMsgCity = MutableLiveData<String>()
    val cityErroMsg: LiveData<String> get() = _errorMsgCity

    private val _errorMsgState = MutableLiveData<String>()
    val stateErroMsg: LiveData<String> get() = _errorMsgState

    private val _errorMsgPinCode = MutableLiveData<String>()
    val pinCodeErroMsg: LiveData<String> get() = _errorMsgPinCode

    private val _status = MutableLiveData<String>()
    val statusMsg: LiveData<String> get() = _status


    fun validate(uniqueId: String){
        if(address.value.isNullOrEmpty()){
            _errorMsgAddress.value = Constants.emptyStringErrorMessage
        }
        else if (address.value?.length?.compareTo(3) == -1) {
            _errorMsgAddress.value = Constants.charLessThan3ErrorMessage
        }
        else if(landmark.value.isNullOrEmpty()){
            _errorMsgLandmark.value = Constants.emptyStringErrorMessage
        }
        else if (landmark.value?.length?.compareTo(3) == -1) {
            _errorMsgLandmark.value = Constants.charLessThan3ErrorMessage
        }
        else if(city.value.isNullOrEmpty()){
            _errorMsgCity.value = Constants.emptyStringErrorMessage
        }
        else if(state.value.equals("Select")){
            _errorMsgState.value = Constants.stateSpinnerError
        }
        else if(pincode.value.isNullOrEmpty()){
            _errorMsgPinCode.value = Constants.emptyStringErrorMessage
        }
        else if (pincode.value?.length?.compareTo(6) == -1) {
            _errorMsgPinCode.value = Constants.numbersLessThan6ErrorMessage
        }
        else{
            viewModelScope.launch {
                usersRepository.updateAddressDetails(address.value.toString(),landmark.value.toString(),city.value.toString(),state.value.toString(),pincode.value.toString(),uniqueId)
                _status.value = "Registered Successfully"
            }
        }
    }
}