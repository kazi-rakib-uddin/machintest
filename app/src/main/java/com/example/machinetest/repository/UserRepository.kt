package com.example.machinetest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.machinetest.data.AddTable
import com.example.machinetest.data.CartTable
import com.example.machinetest.data.ContactDeo
import com.example.machinetest.data.OrderTable
import com.example.machinetest.data.SignUpTable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val contactDeo: ContactDeo
) {

    suspend fun signupUser(signUpTable: SignUpTable) = contactDeo.signup(signUpTable)

    suspend fun checkRegisterEmail(email : String) = contactDeo.emailCheck(email)

     suspend fun checkLogin(email : String, password : String): Boolean {
         val checkLogin = contactDeo.chechLogin(emailId = email, password = password)
         Log.d("TAG", "checkLogin: "+checkLogin)
         return checkLogin
     }

    suspend fun addProduct(addTable: AddTable) = contactDeo.addProduct(addTable)

    fun getAllProduct() :LiveData<List<AddTable>>
    {
        return contactDeo.getAllProduct()
    }

    suspend fun updateProduct(addTable: AddTable) = contactDeo.updateProduct(addTable)
    suspend fun addToCart(cartTable: CartTable) = contactDeo.addToCart(cartTable)

    fun getCartProduct() :LiveData<List<CartTable>>
    {
        return contactDeo.getCartProduct()
    }

    suspend fun updateQuantity(id : Long,quantity : String) = contactDeo.updateQuantity(id = id, quantity = quantity)

    suspend fun deleteProduct(id : Long) = contactDeo.deleteProduct(id = id)

    suspend fun addProductList(list : ArrayList<OrderTable>)
    {
        contactDeo.addProductList(list)
    }

    fun getOrderProduct() :LiveData<List<OrderTable>>
    {
        return contactDeo.getOrderProduct()
    }

    suspend fun deleteCartTable() = contactDeo.deleteCartTable()

    suspend fun checkProductIsAlreadyInCart(productId : String) = contactDeo.checkProductIsAlreadyInCart(productId = productId)


    fun searchProduct(productName :String) : LiveData<List<AddTable>>
    {
        return contactDeo.searchProduct(productName = productName)
    }

}