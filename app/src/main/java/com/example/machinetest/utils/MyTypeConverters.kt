package com.example.machinetest.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.machinetest.data.OrderProductTable
import com.example.machinetest.data.OrderTable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

class MyTypeConverters {


    @TypeConverter
    fun listToJson(value: List<OrderProductTable>?) =  Gson().toJson(value)
    @TypeConverter
    fun jsonToList(json: String) = Gson().fromJson(json,Array<OrderProductTable>::class.java).toList()


  /*  @TypeConverter
    fun fromString(value : String) : ArrayList<OrderTable>
    {
        val listType = object : TypeToken<ArrayList<OrderTable>>(){}.type
        return Gson().fromJson(value,listType)
    }
    @TypeConverter
    fun fromArrayList(list : ArrayList<OrderTable>): String {
        return Gson().toJson(list)
    }*/



    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


}