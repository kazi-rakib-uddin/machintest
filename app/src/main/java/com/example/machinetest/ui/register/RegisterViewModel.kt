package com.example.machinetest.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.SignUpTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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



}