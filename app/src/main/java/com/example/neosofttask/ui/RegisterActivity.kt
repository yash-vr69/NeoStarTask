package com.example.neosofttask.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.neosofttask.view_model.RegisterViewModel
import com.example.neosofttask.R
import com.example.neosofttask.databinding.ActivityRegisterBinding
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private var mProfileUri: Uri? = null

    private val profileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                mProfileUri = uri
                binding.tvProfilePic.setImageURI(uri)
            }
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)
        viewModel = ViewModelProvider(this@RegisterActivity)[RegisterViewModel::class.java]

        binding.apply {
           mainViewModel = viewModel
           lifecycleOwner = this@RegisterActivity
           executePendingBindings()
        }

        handleClickEvents()
        setObservers()
        handleRadioButtonSelection()
    }

    private fun handleClickEvents(){
        binding.apply {
            showPassword.setOnClickListener {
                showPassword.visibility = View.GONE
                hidePassword.visibility = View.VISIBLE
                edtTxtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                edtTxtPassword.setSelection(edtTxtPassword.text!!.length)
            }

            hidePassword.setOnClickListener {
                showPassword.visibility = View.VISIBLE
                hidePassword.visibility = View.GONE
                edtTxtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                edtTxtPassword.setSelection(edtTxtPassword.text!!.length)
            }

            tvProfilePic.setOnClickListener {

                ImagePicker.with(this@RegisterActivity)
                    .crop()
                    .cropOval()
                    .maxResultSize(512, 512, true)
                    .provider(ImageProvider.BOTH) // Or bothCameraGallery()
                    .setDismissListener {
                        Log.d("ImagePicker", "onDismiss")
                    }
                    .createIntentFromDialog { profileLauncher.launch(it) }
            }
        }
    }

    private fun handleRadioButtonSelection() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedGender = if (R.id.radioMale == checkedId) "male" else "female"
            viewModel.gender.value = selectedGender
        }
    }

    private fun setObservers(){
        viewModel.firstNameErroMsg.observe(this) {
            if(! it.isNullOrEmpty()){
//                if(it.equals(Consa))
                binding.edtTxtFirstName.error = it
                displayErrorToast(it)
            }
        }
            viewModel.lastNameErroMsg.observe(this) {
            binding.edtTxtLastName.error = it
            displayErrorToast(it)
        }
            viewModel.phoneErroMsg.observe(this) {
            binding.edtTxtPhoneNo.error = it
            displayErrorToast(it)
        }
            viewModel.genderErroMsg.observe(this) {
            displayErrorToast(it)
        }
            viewModel.emailErroMsg.observe(this) {
            binding.edtTxtEmail.error = it
            displayErrorToast(it)
        }
            viewModel.passwordErroMsg.observe(this) {
            binding.edtTxtPassword.error = it
            displayErrorToast(it)
        }
            viewModel.confirmPassErroMsg.observe(this) {
            binding.edtTxtConfirmPass.error = it
            displayErrorToast(it)
        }
        viewModel.actionDataForView.observe(this) {
            startActivity(Intent(this, YourInfoActivity::class.java).apply {
                putExtra("uniqueId", it.toString())
            })
        }
    }

    private fun displayErrorToast(errorMessage: String){
        Toast.makeText(this@RegisterActivity,errorMessage,Toast.LENGTH_SHORT).show()
    }
}

