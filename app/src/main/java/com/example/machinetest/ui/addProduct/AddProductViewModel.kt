package com.example.machinetest.ui.addProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.AddTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun addProduct(productName:String,price:String,desc:String,category:String)
    {
        viewModelScope.launch(Dispatchers.IO) {

                userRepository.addProduct(AddTable(
                    productName = productName,
                    price = price,
                    desc = desc,
                    category = category
                ))


        }

    }
}