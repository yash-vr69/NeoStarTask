package com.example.neosofttask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neosofttask.view_model.MainActivity3ViewModel
import com.example.neosofttask.R
import com.example.neosofttask.databinding.ActivityMain3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    private lateinit var viewModel: MainActivity3ViewModel
    private val stateList = arrayOf("Select","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Maharashtra","Manipur")
    private var uniqueId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentData = intent
        uniqueId = intentData.getStringExtra("uniqueId")

        binding = DataBindingUtil.setContentView(this@MainActivity3, R.layout.activity_main3)
        viewModel = ViewModelProvider(this@MainActivity3).get(MainActivity3ViewModel::class.java)

        binding.apply {

            lifecycleOwner = this@MainActivity3
            addressViewModel = viewModel
            executePendingBindings()

        }

        handleOnClickEvent()
        setObservers()
        setUp()
    }

    private fun handleOnClickEvent(){
        binding.apply {
            btnSubmit.setOnClickListener {
                getValuesFromSpinner()
                viewModel.validate(uniqueId!!)
            }
        }
    }

    private fun setObservers(){

        viewModel.apply {

            addressErroMsg.observe(this@MainActivity3, Observer {
                binding.addressEdtTxt.error = it
                displayErrorToast(it)
            })
            landmarkErroMsg.observe(this@MainActivity3, Observer {
                binding.landmarkEdtTxt.error = it
                displayErrorToast(it)
            })
            cityErroMsg.observe(this@MainActivity3, Observer {
                binding.cityEdtTxt.error = it
                displayErrorToast(it)
            })
            stateErroMsg.observe(this@MainActivity3, Observer {
                displayErrorToast(it)
            })
            pinCodeErroMsg.observe(this@MainActivity3, Observer {
                binding.pinCodeEdtTxt.error = it
                displayErrorToast(it)
            })

            statusMsg.observe(this@MainActivity3, Observer {
                displayErrorToast(it)
                val i = Intent(this@MainActivity3, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            })
        }
    }

    private fun setUp(){

        val stateAdapter = ArrayAdapter(this@MainActivity3, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stateList)
        binding.stateSpinner.adapter = stateAdapter

    }

    private fun getValuesFromSpinner(){

        binding.apply {
            viewModel.state.value = stateSpinner.selectedItem.toString()
        }

    }

    private fun displayErrorToast(errorMessage: String){
        Toast.makeText(this@MainActivity3,errorMessage,Toast.LENGTH_SHORT).show()
    }
}