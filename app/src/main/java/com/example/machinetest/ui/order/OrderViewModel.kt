package com.example.machinetest.ui.order

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
class OrderViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun allOrderProduct() : LiveData<List<OrderTable>>
    {

        return userRepository.getOrderProduct()

    }


}