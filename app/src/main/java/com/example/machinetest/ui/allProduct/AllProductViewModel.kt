package com.example.machinetest.ui.allProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.AddTable
import com.example.machinetest.data.CartTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun allProduct() : LiveData<List<AddTable>>
    {

        return userRepository.getAllProduct()

    }


    fun addToCart(productName:String,price:String,desc:String,category:String,quantity:String) {
        viewModelScope.launch(Dispatchers.IO) {

            userRepository.addToCart(
                CartTable(
                    productName = productName,
                    price = price,
                    desc = desc,
                    category = category,
                    quantity = quantity
                )
            )


        }
    }


}