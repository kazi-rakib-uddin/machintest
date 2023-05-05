package com.example.machinetest.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.SignUpTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun signup(username:String,email:String,phone:String,password:String )
    {
        viewModelScope.launch(Dispatchers.IO) {

                userRepository.signupUser(SignUpTable(
                    userName = username,
                    email = email,
                    phone = phone,
                    password = password
                ))


        }

    }



    fun checkRegisterEmail(email: String): Boolean {
        Log.d("TAG", "checkLogin: $email")
        var emailValid = false
        viewModelScope.launch {
            runBlocking {
                emailValid = userRepository.checkRegisterEmail(email = email)

            }
        }

        return emailValid

    }



}