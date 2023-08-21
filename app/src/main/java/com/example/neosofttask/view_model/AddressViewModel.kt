package com.example.neosofttask.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.repository.UsersRepository
import com.example.neosofttask.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

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

        if(!Validator.validateAddress(address.value).equals(Constants.successMsg)){
            _errorMsgAddress.value = Validator.validateAddress(address.value)
        }
        else if(!Validator.validateLandmark(landmark.value).equals(Constants.successMsg)){
            _errorMsgLandmark.value = Validator.validateLandmark(landmark.value)
        }
        else if(!Validator.validateCity(city.value).equals(Constants.successMsg)){
            _errorMsgCity.value = Validator.validateCity(city.value)
        }
        else if(!Validator.validateState(state.value.toString()).equals(Constants.successMsg)){
            _errorMsgState.value = Validator.validateState(state.value.toString())
        }
        else if(!Validator.validatePinCode(pincode.value).equals(Constants.successMsg)){
            _errorMsgPinCode.value = Validator.validatePinCode(pincode.value.toString())
        }else{
            viewModelScope.launch {
                usersRepository.updateAddressDetails(address.value.toString(),landmark.value.toString(),city.value.toString(),state.value.toString(),pincode.value.toString(),uniqueId)
                _status.value = "Registered Successfully"
            }
        }




//        if(_errorMsgAddress.value?.equals(Constants.successMsg) == true && _errorMsgLandmark.value?.equals(Constants.successMsg) == true &&
//            _errorMsgCity.value?.equals(Constants.successMsg) == true && _errorMsgState.value?.equals(Constants.successMsg) == true &&
//            _errorMsgPinCode.value?.equals(Constants.successMsg) == true){
//
//
//        }
    }
}