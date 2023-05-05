package com.example.machinetest.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class OrderProductTable(
    val productName : String,
    val price : String,
    val desc : String,
    val category : String,
    val quantity : String,
    val isInCart:Boolean,
    val cartQuantity:Int
)