package com.example.languageapp

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.languageapp.signUpIn.EMAIL_PATTERN
import com.example.languageapp.signUpIn.UserModel
import com.example.languageapp.signUpIn.USERNAME_PATTERN
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern

class UserDataLogicImpl : UserDataInterface {
    override suspend fun getExistedUser(email: String, password: String): List<UserModel> =
        withContext(Dispatchers.IO) {
            val supabaseClient = createSupabaseClient(
                supabaseUrl = PROJECT_URL,
                supabaseKey = SUPABASE_KEY
            ) { install(Postgrest) }

            val existUser: List<UserModel> = async {
                supabaseClient.from("users")
                    .select(
                        columns = Columns.list(
                            "firstname",
                            "lastname",
                            "userImage",
                            "email",
                            "password",
                            "score"
                        )
                    ) {
                        filter {
                            eq("email", email)
                            eq("password", hashedPass(password))
                        }
                    }.decodeList<UserModel>()
            }.await()

            existUser
        }

    override fun onBoardingCompleted(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_COMPLETE_NAME, Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putBoolean(FINISHED_STATE_KEY, true)
            .apply()
    }

    override fun registerNewUser(userModel: UserModel) {
        val supabaseClient = createSupabaseClient(
            supabaseUrl = PROJECT_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Postgrest)
        }
        GlobalScope.launch {
            supabaseClient.from("users").insert(userModel)
        }
        // TODO: write a logic when there is the same user
    }

    override fun makeAuthSharedFlag(context: Context, email: String, password: String) {
        val sharedPref = context.getSharedPreferences(PREF_AUTH_NAME, Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putBoolean(AUTHORIZED_STATE_KEY, true)
            .putString(EMAIL_KEY, email)
            .putString(PASSWORD_KEY, password)
            .apply()
    }

    override fun getAuthorizedEmailPass(requireActivity: FragmentActivity): List<String?> {
        val sharedPref = requireActivity.getSharedPreferences(PREF_AUTH_NAME, Context.MODE_PRIVATE)

        return listOf(
            sharedPref.getString(EMAIL_KEY, ""), sharedPref.getString(PASSWORD_KEY, "")
        )
    }

    override fun isValidData(data: String, needPattern: String): Boolean {
        val pattern = Pattern.compile(needPattern)
        return pattern.matcher(data).matches() && data.isNotEmpty()
    }

    override fun isEverythingOkay(userData: UserModel): Boolean {
        return isValidData(userData.firstname, USERNAME_PATTERN) &&
                isValidData(userData.lastname, USERNAME_PATTERN) &&
                isValidData(userData.email, EMAIL_PATTERN)
    }

    override fun isOnBoardingCompleted(requireActivity: FragmentActivity): Boolean {
        val sharedPref =
            requireActivity.getSharedPreferences(PREF_COMPLETE_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(FINISHED_STATE_KEY, false)
    }

    override fun isAnyoneAuthorized(requireActivity: FragmentActivity): Boolean {
        val sharedPref = requireActivity.getSharedPreferences(PREF_AUTH_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(AUTHORIZED_STATE_KEY, false)
    }

    override fun hashedPass(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    override suspend fun getTopUsers(): List<UserModel> =
        withContext(Dispatchers.IO) {
            val supabaseClient = createSupabaseClient(
                supabaseUrl = PROJECT_URL,
                supabaseKey = SUPABASE_KEY
            ) { install(Postgrest) }

            val userData: List<UserModel> = async {
                supabaseClient.from("users")
                    .select(
                        columns = Columns.list(
                            "firstname",
                            "lastname",
                            "userImage",
                            "email",
                            "password",
                            "score"
                        )
                    )
                    .decodeList<UserModel>()
            }.await()

            userData.sortedByDescending { it.score }.take(3)
        }

}