package com.example.machinetest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.machinetest.utils.MyTypeConverters

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [SignUpTable::class,AddTable::class,CartTable::class,OrderTable::class], version = 9, exportSchema = false)
abstract class ContactDatabse : RoomDatabase() {
    abstract fun contactDeo() : ContactDeo
}