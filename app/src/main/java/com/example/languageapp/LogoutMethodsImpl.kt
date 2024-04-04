package com.example.languageapp

import android.content.Context

class LogoutMethodsImpl : LogoutMethods {

    override fun clearOnBoardingFlag(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_COMPLETE_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }

    override fun clearAuthFlag(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_AUTH_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }

    override fun clearUserData(context: Context) {
        val sharedPref = context.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }
}