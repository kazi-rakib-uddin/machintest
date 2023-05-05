package com.example.machinetest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDeo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun signup(signUpTable: SignUpTable)

    @Query("SELECT EXISTS (SELECT * from signup where email=:emailId)")
    suspend fun emailCheck(emailId : String):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(addTable: AddTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductList(addTable: List<OrderTable>)

    @Query("SELECT * FROM addProduct")
    fun getAllProduct(): LiveData<List<AddTable>>

    @Query("SELECT EXISTS (SELECT * from cartTable where productId=:productId)")
    suspend fun checkProductIsAlreadyInCart(productId : String): Boolean

    @Query("SELECT EXISTS (SELECT * from signup where email=:emailId and password=:password)")
    suspend fun chechLogin(emailId : String, password: String):Boolean


    @Update
    suspend fun updateProduct(addTable: AddTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartTable: CartTable)

    @Query("SELECT * from cartTable")
    fun getCartProduct() : LiveData<List<CartTable>>


    @Query("UPDATE cartTable SET quantity =:quantity WHERE id=:id")
    suspend fun updateQuantity(id :Long, quantity : String)

    @Query("DELETE FROM cartTable WHERE id=:id")
    suspend fun deleteProduct(id :Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(orderTable: OrderTable)

    @Query("SELECT * from orderTable")
    fun getOrderProduct() : LiveData<List<OrderTable>>

    @Query("DELETE from cartTable")
    suspend fun deleteCartTable()

    @Query ("SELECT * FROM addProduct WHERE productName LIKE :productName")
    fun searchProduct (productName : String):LiveData<List<AddTable>>

}