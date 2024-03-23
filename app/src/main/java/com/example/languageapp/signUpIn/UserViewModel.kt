package com.example.languageapp.signUpIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}