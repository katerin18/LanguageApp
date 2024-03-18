package com.example.languageapp.onboarding

import android.content.Context

const val PREF_COMPLETE_NAME = "onboarding_complete"
const val FINISHED_STATE_KEY = "finished_state"

const val PREF_AUTH_NAME = "auth_completed"
const val AUTHORIZED_STATE_KEY = "is_authorized"
const val EMAIL_KEY = "authorized_email"

object LoginStateManager {

    fun onBoardingCompleted(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_COMPLETE_NAME, Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putBoolean(FINISHED_STATE_KEY, true)
            .apply()
    }

    fun userAuthorized(context: Context, email: String) {
        val sharedPref = context.getSharedPreferences(PREF_AUTH_NAME, Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putBoolean(AUTHORIZED_STATE_KEY, true)
            .putString(EMAIL_KEY, email)
            .apply()
    }
}