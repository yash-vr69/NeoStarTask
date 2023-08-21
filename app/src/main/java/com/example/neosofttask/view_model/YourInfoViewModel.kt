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
class YourInfoViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

    val education = MutableLiveData<String>()
    val yearOfPassing = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val experience = MutableLiveData<String>()
    val designation = MutableLiveData<String>()
    val domain = MutableLiveData<String>()

    private val _errorMsgEducation = MutableLiveData<String>()
    val educationErroMsg: LiveData<String> get() = _errorMsgEducation

    private val _errorMsgYearOfPassing = MutableLiveData<String>()
    val yearOfPassingErroMsg: LiveData<String> get() = _errorMsgYearOfPassing

    private val _errorMsgGrade = MutableLiveData<String>()
    val gradeErroMsg: LiveData<String> get() = _errorMsgGrade

    private val _errorMsgExperience = MutableLiveData<String>()
    val experienceErroMsg: LiveData<String> get() = _errorMsgExperience

    private val _errorMsgDesignation = MutableLiveData<String>()
    val designationErroMsg: LiveData<String> get() = _errorMsgDesignation

    private val _errorMsgDomain = MutableLiveData<String>()
    val domainErroMsg: LiveData<String> get() = _errorMsgDomain

    private val _actionData = MutableLiveData<String>()
    val actionDataForView: LiveData<String> get() = _actionData

    fun validate(uniqueId: String){

        if(!Validator.validateEducation(education.value.toString()).equals(Constants.successMsg)){
            _errorMsgEducation.value = Validator.validateEducation(education.value.toString())
        }
        else if(!Validator.validateYearOfPassing(yearOfPassing.value.toString()).equals(Constants.successMsg)){
            _errorMsgYearOfPassing.value = Validator.validateYearOfPassing(yearOfPassing.value.toString())
        }
        else if(!Validator.validateGrade(grade.value).equals(Constants.successMsg)){
            _errorMsgGrade.value = Validator.validateGrade(grade.value.toString())
        }
        else if(!Validator.validateExperience(experience.value).equals(Constants.successMsg)){
            _errorMsgExperience.value = Validator.validateExperience(experience.value.toString())
        }
        else if(!Validator.validateDesignation(designation.value.toString()).equals(Constants.successMsg)){
            _errorMsgDesignation.value = Validator.validateDesignation(designation.value.toString())
        }
        else if(!Validator.validateDomain(domain.value.toString()).equals(Constants.successMsg)){
            _errorMsgDomain.value = Validator.validateDomain(domain.value.toString())
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