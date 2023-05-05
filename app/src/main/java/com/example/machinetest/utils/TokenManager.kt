package com.example.machinetest.utils

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.putString
import androidx.core.content.edit
import com.example.machinetest.utils.Constants.PREFS_TOKEN_FILE
import com.example.machinetest.utils.Constants.USER_EMAIL
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_EMAIL, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_EMAIL, null)
    }

    fun clear() {

        prefs.edit {
            putString(USER_EMAIL, null)
        }
    }
}