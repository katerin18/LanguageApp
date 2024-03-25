package com.example.languageapp.signUpIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val mutLiveDataUser = MutableLiveData<UserModel>()
    val userData: LiveData<UserModel> get() = mutLiveDataUser

    fun setRegistrationData(user: UserModel) {
        mutLiveDataUser.value = user
    }

    fun combineWithPassword(password: String): UserModel? {
        val currentData = mutLiveDataUser.value
        return currentData?.copy(password = password)
    }
}