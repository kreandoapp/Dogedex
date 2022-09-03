package com.example.dogedex.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.example.dogedex.R
import com.example.dogedex.databinding.ActivityLoginBinding
import com.example.dogedex.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity(),LoginFragment.LoginFragmentActions ,SignUpFragment.SignUpFragmentActions{

   private val viewModel : AuthViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onregisterButonClick() {
        findNavController(R.id.nav_host_fragment).
        navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    override fun onSignUpFieldsValidate(
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        //TODO
    }
}