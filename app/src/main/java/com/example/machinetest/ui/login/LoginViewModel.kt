package com.example.machinetest.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.ContactDeo
import com.example.machinetest.data.SignUpTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dao: ContactDeo
) : ViewModel() {



    fun checkLogin(email: String, password: String): Boolean {
        Log.d("TAG", "checkLogin: $email $password")
        var loginValid = false
        viewModelScope.launch {
            runBlocking {
                loginValid = userRepository.checkLogin(email = email, password = password)

            }
        }
        Log.d("TAG", "checkLogin 1: $loginValid")

        return loginValid

    }

}