package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    interface SignUpFragmentActions {
        fun onSignUpFieldsValidate(email:String,password:String,passwordConfirmation :String)
    }
    private lateinit var  signUpFragmentActions : SignUpFragment.SignUpFragmentActions
    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpFragmentActions = try {
            context as SignUpFragmentActions
        }catch (e :ClassCastException){
            throw ClassCastException("$context must implment")
        }
    }

    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentSignUpBinding.inflate(inflater)
        setupSignUpButton()
        return binding.root
    }

    private fun setupSignUpButton() {
        binding.signUpButton.setOnClickListener {
           validateFields()
        }
    }

    private fun validateFields() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
        binding.confirmPasswordInput.error = ""
        val email  = binding.emailEdit.text.toString()
        if(!isValidEmail(email)){
            binding.emailInput.error = getString(R.string.email_is_not_valid)
            return
        }

        val password = binding.passwordEdit.text.toString()
        if(password.isEmpty()){
            binding.passwordInput.error = getString(R.string.password_is_not_valid)
            return
        }
        val passwordconfirmation = binding.confirmPasswordEdit.text.toString()
        if(passwordconfirmation.isEmpty()){
            binding.confirmPasswordInput.error = getString(R.string.password_is_not_valid)
            return
        }

        if(password != passwordconfirmation){
            binding.confirmPasswordInput.error = getString(R.string.password_no_match)
        }

        signUpFragmentActions.onSignUpFieldsValidate(email,password,passwordconfirmation)

    }

    private  fun isValidEmail(email : String?) : Boolean{
        return  !email.isNullOrEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}