package com.example.languageapp.signUpIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val SUPABASE_KEY =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFsdG1zYmhkbWVtanZmbHFoZWxiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTA1NDQzMzIsImV4cCI6MjAyNjEyMDMzMn0.KNw5Rf0IT792yJAuNDViJvDMFV8ov23ukxeLiE78ILs"
const val PROJECT_URL = "https://qltmsbhdmemjvflqhelb.supabase.co"

class UserViewModel : ViewModel() {
    private val mutLiveDataUser = MutableLiveData<NewUser>()
    val userData: LiveData<NewUser> get() = mutLiveDataUser

    fun setRegistrationData(user: NewUser) {
        mutLiveDataUser.value = user
    }

    fun combineWithPassword(password: String): NewUser? {
        val currentData = mutLiveDataUser.value
        return currentData?.copy(password = password)
    }

    fun registerNewUser(newUser: NewUser) {
        val supabaseClient = createSupabaseClient(
            supabaseUrl = PROJECT_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Postgrest)
        }
        GlobalScope.launch {
            supabaseClient.from("users").insert(newUser)
        }
        // TODO: write a logic when there is the same user
    }
}