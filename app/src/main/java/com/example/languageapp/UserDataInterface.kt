package com.example.languageapp

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.example.languageapp.signUpIn.NewUser

const val PREF_COMPLETE_NAME = "onboarding_complete"
const val FINISHED_STATE_KEY = "finished_state"

const val PREF_AUTH_NAME = "auth_completed"
const val AUTHORIZED_STATE_KEY = "is_authorized"
const val EMAIL_KEY = "authorized_email"

const val SUPABASE_KEY =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFsdG1zYmhkbWVtanZmbHFoZWxiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTA1NDQzMzIsImV4cCI6MjAyNjEyMDMzMn0.KNw5Rf0IT792yJAuNDViJvDMFV8ov23ukxeLiE78ILs"
const val PROJECT_URL = "https://qltmsbhdmemjvflqhelb.supabase.co"

interface UserDataInterface {
    suspend fun getUserData(email: String, password: String): List<NewUser>

    fun isValidData(data: String, needPattern: String): Boolean
    fun isEverythingOkay(userData: NewUser): Boolean

    fun onBoardingCompleted(context: Context)

    fun toAuthorizeUser(context: Context, email: String)
    fun registerNewUser(newUser: NewUser)

    fun isOnBoardingCompleted(requireActivity: FragmentActivity): Boolean
    fun isUserAuthorized(requireActivity: FragmentActivity): Boolean

    fun hashedPass(input: String): String

}