package com.example.machinetest.ui.editProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.data.AddTable
import com.example.machinetest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun updateProduct(id :Long ,productName:String,price:String,desc:String,category:String)
    {
        viewModelScope.launch(Dispatchers.IO) {

                userRepository.updateProduct(AddTable(
                    id = id,
                    productName = productName,
                    price = price,
                    desc = desc,
                    category = category
                ))


        }

    }
}