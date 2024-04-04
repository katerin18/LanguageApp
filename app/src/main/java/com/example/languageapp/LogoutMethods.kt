package com.example.languageapp

import android.content.Context

interface LogoutMethods {
    fun clearOnBoardingFlag(context: Context)
    fun clearAuthFlag(context: Context)
    fun clearUserData(context: Context)
}