package com.example.neosofttask.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.repository.UsersRepository
import com.example.neosofttask.utils.ErrorTypes
import com.example.neosofttask.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

    val address = MutableLiveData<String>()
    val landmark = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val state = MutableLiveData<String>()
    val pincode = MutableLiveData<String>()

    val _errorMsg = MutableSharedFlow<Pair<String,ErrorTypes>>()
    val errorMsg = _errorMsg.asSharedFlow()

    private val _errorMsgAddress = MutableSharedFlow<String>()
    val addressErroMsg = _errorMsgAddress

    private val _errorMsgLandmark = MutableSharedFlow<String>()
    val landmarkErroMsg = _errorMsgLandmark

    private val _errorMsgCity = MutableSharedFlow<String>()
    val cityErroMsg = _errorMsgCity

    private val _errorMsgState = MutableSharedFlow<String>()
    val stateErroMsg = _errorMsgState

    private val _errorMsgPinCode = MutableSharedFlow<String>()
    val pinCodeErroMsg = _errorMsgPinCode

    private val _status = MutableLiveData<String>()
    val statusMsg: LiveData<String> get() = _status


    fun validate(uniqueId: String) = viewModelScope.launch{
        if(!Validator.validateAddress(address.value).equals(Constants.successMsg)){
            _errorMsg.emit(Pair(Validator.validateAddress(address.value),ErrorTypes.ADDRESS))
        }

        else if(!Validator.validateLandmark(landmark.value).equals(Constants.successMsg)){
            _errorMsg.emit(Pair(Validator.validateLandmark(landmark.value),ErrorTypes.LANDMARK))
//            _errorMsgLandmark.value = Validator.validateLandmark(landmark.value)
        }
        else if(!Validator.validateCity(city.value).equals(Constants.successMsg)){
//            _errorMsgCity.value = Validator.validateCity(city.value)
            _errorMsg.emit(Pair(Validator.validateCity(city.value),ErrorTypes.CITY))
        }
        else if(!Validator.validateState(state.value.toString()).equals(Constants.successMsg)){
//            _errorMsgState.value = Validator.validateState(state.value.toString())
            _errorMsg.emit(Pair(Validator.validateState(state.value.toString()),ErrorTypes.STATE))
        }
        else if(!Validator.validatePinCode(pincode.value).equals(Constants.successMsg)){
//            _errorMsgPinCode.value = Validator.validatePinCode(pincode.value.toString())
            _errorMsg.emit(Pair(Validator.validatePinCode(pincode.value.toString()),ErrorTypes.PINCODE))
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