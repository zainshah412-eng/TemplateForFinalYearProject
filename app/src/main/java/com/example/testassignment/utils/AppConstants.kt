package com.example.testassignment.utils;
import androidx.viewbinding.BuildConfig

class AppConstants {
    companion object {
        val SHOW_CONSOLE_LOGS = BuildConfig.DEBUG

        /**
         * Base and staging URLS
         */
        const val BASE_URL = "https://api.github.com/"

        /**
         * EndPoints
         */
        const val GET_SEARCH_DETAIL = BASE_URL  + "search/users"

    }
}