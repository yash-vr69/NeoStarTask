package com.example.neosofttask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neosofttask.utils.Education
import com.example.neosofttask.view_model.MainActivity2ViewModel
import com.example.neosofttask.R
import com.example.neosofttask.databinding.ActivityMain2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var viewModel: MainActivity2ViewModel
    private val yearOfPassingList = arrayOf("Select","2023","2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010")
    private val designationList = arrayOf("Select","Technical Consultant","Android Developer","Jr Android Developer","Senior Android Developer","Android Team Lead","Android Project Manager")
    private val domainList = arrayOf("Select","Application Scripting.","Array Programming","Artificial-Intelligence Reasoning","Cloud Computing","Computational Statistics","E-Commerce")
    private var uniqueId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intentData = intent
        uniqueId = intentData.getStringExtra("uniqueId")

        binding = DataBindingUtil.setContentView(this@MainActivity2, R.layout.activity_main2)
        viewModel = ViewModelProvider(this@MainActivity2)[MainActivity2ViewModel::class.java]
        binding.apply {

            lifecycleOwner = this@MainActivity2
            infoViewModel = viewModel
            executePendingBindings()
        }

        handleClickEvents()
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

    private fun setUpObservers(){
        viewModel.educationErroMsg.observe(this) {
            displayError(it)
        }

        viewModel.yearOfPassingErroMsg.observe(this) {
            displayError(it)
        }

        viewModel.gradeErroMsg.observe(this) {
            binding.edtTxtGrade.error = it
            displayError(it)
        }

        viewModel.experienceErroMsg.observe(this) {
            binding.edtTxtExperience.error = it
            displayError(it)
        }

        viewModel.designationErroMsg.observe(this) {
            displayError(it)
        }

        viewModel.domainErroMsg.observe(this) {
            displayError(it)
        }

        viewModel.actionDataForView.observe(this, Observer {
            startActivity(Intent(this, MainActivity3::class.java).apply {
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
        val educationAdapter = ArrayAdapter(this@MainActivity2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            Education.list()
        )
        binding.educationSpinner.adapter = educationAdapter

        val yearOfPassingAdapter = ArrayAdapter(this@MainActivity2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, yearOfPassingList)
        binding.passYearSpinner.adapter = yearOfPassingAdapter

        val designationAdapter = ArrayAdapter(this@MainActivity2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, designationList)
        binding.designationSpinner.adapter = designationAdapter

        val domainAdapter = ArrayAdapter(this@MainActivity2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, domainList)
        binding.domainSpinner.adapter = domainAdapter
    }
}