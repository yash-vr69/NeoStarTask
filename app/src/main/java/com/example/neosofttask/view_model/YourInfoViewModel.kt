package com.example.neosofttask.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neosofttask.utils.Constants
import com.example.neosofttask.repository.UsersRepository
import com.example.neosofttask.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourInfoViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

    val education = MutableLiveData<String>()
    val yearOfPassing = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val experience = MutableLiveData<String>()
    val designation = MutableLiveData<String>()
    val domain = MutableLiveData<String>()

    private val _errorMsgEducation = MutableSharedFlow<String>()
    val educationErroMsg = _errorMsgEducation

    private val _errorMsgYearOfPassing = MutableSharedFlow<String>()
    val yearOfPassingErroMsg = _errorMsgYearOfPassing

    private val _errorMsgGrade = MutableSharedFlow<String>()
    val gradeErroMsg = _errorMsgGrade

    private val _errorMsgExperience = MutableSharedFlow<String>()
    val experienceErroMsg = _errorMsgExperience

    private val _errorMsgDesignation = MutableSharedFlow<String>()
    val designationErroMsg = _errorMsgDesignation

    private val _errorMsgDomain = MutableSharedFlow<String>()
    val domainErroMsg = _errorMsgDomain

    private val _actionData = MutableLiveData<String>()
    val actionDataForView: LiveData<String> get() = _actionData

    fun validate(uniqueId: String) = viewModelScope.launch{

        if(!Validator.validateEducation(education.value.toString()).equals(Constants.successMsg)){
            _errorMsgEducation.emit(Validator.validateEducation(education.value.toString()))
        }
        else if(!Validator.validateYearOfPassing(yearOfPassing.value.toString()).equals(Constants.successMsg)){
            _errorMsgYearOfPassing.emit(Validator.validateYearOfPassing(yearOfPassing.value.toString()))
        }
        else if(!Validator.validateGrade(grade.value).equals(Constants.successMsg)){
            _errorMsgGrade.emit(Validator.validateGrade(grade.value))
        }
        else if(!Validator.validateExperience(experience.value).equals(Constants.successMsg)){
            _errorMsgExperience.emit(Validator.validateExperience(experience.value))
        }
        else if(!Validator.validateDesignation(designation.value.toString()).equals(Constants.successMsg)){
            _errorMsgDesignation.emit(Validator.validateDesignation(designation.value.toString()))
        }
        else if(!Validator.validateDomain(domain.value.toString()).equals(Constants.successMsg)){
            _errorMsgDomain.emit(Validator.validateDomain(domain.value.toString()))
        }
        else{
            viewModelScope.launch {
                usersRepository.updateInfoDetails(education.value.toString(),
                    yearOfPassing.value.toString(),
                    grade.value.toString(),
                    experience.value.toString(),
                    designation.value.toString(),
                    domain.value.toString(),uniqueId)
                _actionData.value = uniqueId
            }
        }



//        if(_errorMsgEducation.value?.equals(Constants.successMsg) == true && _errorMsgYearOfPassing.value?.equals(Constants.successMsg) == true
//            && _errorMsgGrade.value?.equals(Constants.successMsg) == true && _errorMsgExperience.value?.equals(Constants.successMsg) == true
//            && _errorMsgDesignation.value?.equals(Constants.successMsg) == true && _errorMsgDomain.value?.equals(Constants.successMsg) == true){
//
//            viewModelScope.launch {
//                usersRepository.updateInfoDetails(education.value.toString(),
//                    yearOfPassing.value.toString(),
//                    grade.value.toString(),
//                    experience.value.toString(),
//                    designation.value.toString(),
//                    domain.value.toString(),uniqueId)
//                _actionData.value = uniqueId
//            }
//        }
    }


    /*fun validate(uniqueId: String){
        if(education.value.equals("Select")){
            _errorMsgEducation.value = Constants.educationSpinnerError
        }
        else if(yearOfPassing.value.equals("Select")){
            _errorMsgYearOfPassing.value = Constants.yearOfPassSpinnerError
        }
        else if(grade.value.isNullOrEmpty()){
            _errorMsgGrade.value = Constants.emptyStringErrorMessage
        }
        else if(experience.value.isNullOrEmpty()){
            _errorMsgExperience.value = Constants.emptyStringErrorMessage
        }
        else if(designation.value.equals("Select")){
            _errorMsgDesignation.value = Constants.designationSpinnerError
        }
        else if(domain.value.equals("Select")){
            _errorMsgDomain.value = Constants.domainSpinnerError
        }
        else{
            //update values in databse
            viewModelScope.launch {
                usersRepository.updateInfoDetails(education.value.toString(),
                    yearOfPassing.value.toString(),
                    grade.value.toString(),
                    experience.value.toString(),
                    designation.value.toString(),
                    domain.value.toString(),uniqueId)
                    _actionData.value = uniqueId
            }
        }
    }*/
}