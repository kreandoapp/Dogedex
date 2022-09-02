package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    interface LoginFragmentActions {
        fun onregisterButonClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginFragmentActions = try {
            context as LoginFragmentActions
        }catch (e :ClassCastException){
            throw ClassCastException("$context must implment")
        }
    }

    private lateinit var  loginFragmentActions : LoginFragmentActions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding  = FragmentLoginBinding.inflate(inflater)

        binding.loginRegisterButton.setOnClickListener {
            loginFragmentActions.onregisterButonClick()
        }

        return binding.root
    }


}