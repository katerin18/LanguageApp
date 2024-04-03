package com.example.languageapp

import android.graphics.Bitmap
import android.net.Uri

interface UserImageInterface {
    suspend fun loadImageUriToSupabase(userImage: ByteArray?, userEmail: String): String
    suspend fun getBitmapFromUri(imageUri: Uri): Bitmap
}