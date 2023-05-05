package com.example.machinetest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "signup")
data class SignUpTable(

    @PrimaryKey(autoGenerate = true)
    val id : Long?=null,
    val userName : String,
    val email : String,
    val phone : String,
    val password : String,
)