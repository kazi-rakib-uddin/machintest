package com.example.machinetest.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "orderTable")
data class OrderTable(
    @PrimaryKey(autoGenerate = true)
    val id : Long?=null,
    val productName : String,
    val price : String,
    val desc : String,
    val category : String,
    val quantity : String

)