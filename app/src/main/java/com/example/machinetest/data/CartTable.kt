package com.example.machinetest.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "cartTable")
data class CartTable(

    @PrimaryKey(autoGenerate = true)
    val id : Long?=null,
    val productName : String,
    val price : String,
    val desc : String,
    val category : String,
    val quantity : String
)