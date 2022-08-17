package com.example.myskills.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.myskills.R

class SharedPrefRepo (val context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    private fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, "")
    }


    fun isfirstTime():Boolean{
        var check  = false
        if (fetchAuthToken()?.isNotEmpty()!!){
            check = true
        }
        return  check
    }
}