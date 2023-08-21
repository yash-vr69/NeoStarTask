package com.example.neosofttask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neosofttask.view_model.AddressViewModel
import com.example.neosofttask.R
import com.example.neosofttask.databinding.ActivityAddressBinding
import com.example.neosofttask.utils.IndianStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding
    private lateinit var viewModel: AddressViewModel
    private var uniqueId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentData = intent
        uniqueId = intentData.getStringExtra("uniqueId")

        binding = DataBindingUtil.setContentView(this@AddressActivity, R.layout.activity_address)
        viewModel = ViewModelProvider(this@AddressActivity).get(AddressViewModel::class.java)

        binding.apply {

            lifecycleOwner = this@AddressActivity
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

            addressErroMsg.observe(this@AddressActivity, Observer {
                binding.addressEdtTxt.error = it
                displayErrorToast(it)
            })
            landmarkErroMsg.observe(this@AddressActivity, Observer {
                binding.landmarkEdtTxt.error = it
                displayErrorToast(it)
            })
            cityErroMsg.observe(this@AddressActivity, Observer {
                binding.cityEdtTxt.error = it
                displayErrorToast(it)
            })
            stateErroMsg.observe(this@AddressActivity, Observer {
                displayErrorToast(it)
            })
            pinCodeErroMsg.observe(this@AddressActivity, Observer {
                binding.pinCodeEdtTxt.error = it
                displayErrorToast(it)
            })

            statusMsg.observe(this@AddressActivity, Observer {
                displayErrorToast(it)
                val i = Intent(this@AddressActivity, RegisterActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            })
        }
    }

    private fun setUp(){

        val stateAdapter = ArrayAdapter(this@AddressActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, IndianStates.values())
        binding.stateSpinner.adapter = stateAdapter

    }

    private fun getValuesFromSpinner(){

        binding.apply {
            viewModel.state.value = stateSpinner.selectedItem.toString()
        }

    }

    private fun displayErrorToast(errorMessage: String){
        Toast.makeText(this@AddressActivity,errorMessage,Toast.LENGTH_SHORT).show()
    }
}