package com.lapoushko.navigation.util

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * @author Lapoushko
 */
val StringNavType = object : NavType<String>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): String {
        return bundle.getString(key) ?: throw IllegalStateException("Missing value for key $key")
    }

    override fun parseValue(value: String): String {
        val decodedValue = URLDecoder.decode(value, "UTF-8")
        return Json.decodeFromString(decodedValue)
    }

    override fun put(bundle: Bundle, key: String, value: String) {
        bundle.putString(key, value)
    }

    override fun serializeAsValue(value: String): String {
        val json = Json.encodeToString(value)
        return URLEncoder.encode(json, "UTF-8").replace("%", "%25")
    }

    override val name: String = String::class.java.name
}