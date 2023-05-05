package com.example.machinetest.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.CartTable
import com.example.machinetest.data.OrderTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartProductViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun allProduct() : LiveData<List<CartTable>>
    {

        return userRepository.getCartProduct()

    }

    fun updateQuantity(id : Long, quantity : String)
    {

        viewModelScope.launch(Dispatchers.IO) {

            userRepository.updateQuantity(id = id, quantity = quantity)

        }


    }


    fun deleteProduct(id : Long)
    {
        viewModelScope.launch(Dispatchers.IO) {

            userRepository.deleteProduct(id = id)
        }

    }


    fun allOrder(list: ArrayList<OrderTable>)
    {

        viewModelScope.launch(Dispatchers.IO) {

           userRepository.addProductList(list = list)
        }


    }


    fun deleteCartTable()
    {
        viewModelScope.launch(Dispatchers.IO) {

            userRepository.deleteCartTable()
        }
    }



}