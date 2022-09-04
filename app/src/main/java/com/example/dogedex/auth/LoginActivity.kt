package com.example.dogedex.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.dogedex.MainActivity
import com.example.dogedex.R
import com.example.dogedex.api.ApiResponseStatus
import com.example.dogedex.databinding.ActivityLoginBinding
import com.example.dogedex.databinding.ActivityMainBinding
import com.example.dogedex.model.User

class LoginActivity : AppCompatActivity(),LoginFragment.LoginFragmentActions ,SignUpFragment.SignUpFragmentActions{

   private val viewModel : AuthViewModel  by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    binding.loadingWheel.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> binding.loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> binding.loadingWheel.visibility = View.GONE
            }
        }
        viewModel.user.observe(this){ user ->
            if(user != null){
                User.setLoggedInUser(this,user)
                startMainactivity()
            }
        }

    }

    private fun startMainactivity() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun showErrorDialog(messageId:Int){
            AlertDialog.Builder(this)
                .setTitle("There one error")
                .setMessage(messageId)
                .setPositiveButton(android.R.string.ok){ _,_->{}

                }
                .create()
                .show()
    }

    override fun onregisterButonClick() {
        findNavController(R.id.nav_host_fragment).
        navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    override fun onLoginUpFieldsValidate(email: String, password: String) {
        viewModel.login(email,password)

    }

    override fun onSignUpFieldsValidate(
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
       viewModel.signup(email,password,passwordConfirmation)
    }
}