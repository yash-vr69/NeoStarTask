package com.example.neosofttask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.neosofttask.utils.Education
import com.example.neosofttask.view_model.YourInfoViewModel
import com.example.neosofttask.R
import com.example.neosofttask.databinding.ActivityYourInfoBinding
import com.example.neosofttask.utils.Designation
import com.example.neosofttask.utils.Domain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YourInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYourInfoBinding
    private lateinit var viewModel: YourInfoViewModel
    private val yearOfPassingList = arrayOf("SELECT","2023","2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010")
    private var uniqueId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentData = intent
        uniqueId = intentData.getStringExtra("uniqueId")

        binding = DataBindingUtil.setContentView(this@YourInfoActivity, R.layout.activity_your_info)
        viewModel = ViewModelProvider(this@YourInfoActivity)[YourInfoViewModel::class.java]
        binding.apply {

            lifecycleOwner = this@YourInfoActivity
            infoViewModel = viewModel
            executePendingBindings()
        }

        handleClickEvents()
        collectErrors()
        setUpObservers()
        setUp()
    }

    private fun handleClickEvents(){
        binding.apply {
            btnNext.setOnClickListener {
                getValuesFromSpinner()
                viewModel.validate(uniqueId!!)
            }

            btnPrevious.setOnClickListener {
                finish()
            }

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun collectErrors() = lifecycleScope.launch{

        launch {
            viewModel.educationErroMsg.collect {
                displayError(it)
            }
        }

        launch{
            viewModel.yearOfPassingErroMsg.collect {
                displayError(it)
            }
        }

        launch {
            viewModel.gradeErroMsg.collect {
                binding.edtTxtGrade.error = it
                displayError(it)
            }
        }

        launch{
            viewModel.experienceErroMsg.collect {
                binding.edtTxtExperience.error = it
                displayError(it)
            }
        }

        launch {
            viewModel.designationErroMsg.collect {
                displayError(it)
            }
        }

        launch {
            viewModel.domainErroMsg.collect {
                displayError(it)
            }
        }
    }

    private fun setUpObservers(){
        viewModel.actionDataForView.observe(this, Observer {
            startActivity(Intent(this, AddressActivity::class.java).apply {
                putExtra("uniqueId", it.toString())
            })
        })
    }

    private fun displayError(errorMessage: String){
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
    }

    private fun getValuesFromSpinner(){
        binding.apply {
            viewModel.education.value = educationSpinner.selectedItem.toString()
            viewModel.yearOfPassing.value = passYearSpinner.selectedItem.toString()
            viewModel.designation.value = designationSpinner.selectedItem.toString()
            viewModel.domain.value = domainSpinner.selectedItem.toString()
        }
    }

    private fun setUp(){
        val educationAdapter = ArrayAdapter(this@YourInfoActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            Education.values()
        )
        binding.educationSpinner.adapter = educationAdapter

        val yearOfPassingAdapter = ArrayAdapter(this@YourInfoActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, yearOfPassingList)
        binding.passYearSpinner.adapter = yearOfPassingAdapter

        val designationAdapter = ArrayAdapter(this@YourInfoActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Designation.values())
        binding.designationSpinner.adapter = designationAdapter

        val domainAdapter = ArrayAdapter(this@YourInfoActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Domain.values())
        binding.domainSpinner.adapter = domainAdapter
    }
}