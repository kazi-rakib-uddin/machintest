package com.example.machinetest.ui.editProduct

import android.graphics.Bitmap
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


    fun updateProduct(
        id: Long,
        productName: String,
        price: String,
        desc: String,
        category: String,
        image: Bitmap
    )
    {
        viewModelScope.launch(Dispatchers.IO) {

                userRepository.updateProduct(AddTable(
                    id = id,
                    productName = productName,
                    price = price,
                    desc = desc,
                    category = category,
                    image = image
                ))


        }

    }
}