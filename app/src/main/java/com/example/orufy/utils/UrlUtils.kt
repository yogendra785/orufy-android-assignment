package com.example.orufy.utils

import android.util.Patterns

fun validateAndFormatUrl(input: String): String? {
    val trimmed = input.trim()
    if(trimmed.isEmpty()) return null

    val formattedUrl = if(!trimmed.startsWith("http://") && !trimmed.startsWith("https://")){
        "https://$trimmed"
    }else{
        trimmed
    }
    return if (Patterns.WEB_URL.matcher(formattedUrl).matches()){
        formattedUrl
    }else{
        null
    }
}

