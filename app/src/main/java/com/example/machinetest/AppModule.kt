package com.example.machinetest

import android.content.Context
import androidx.room.Room
import com.example.machinetest.data.ContactDatabse
import com.example.machinetest.data.ContactDeo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext
        context:Context
    ):ContactDatabse{
        return Room.databaseBuilder(context,
            ContactDatabse::class.java,
            "contactDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAppDao(appDatabse: ContactDatabse):ContactDeo
    {
        return appDatabse.contactDeo()
    }
}