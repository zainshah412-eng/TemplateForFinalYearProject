package com.example.testassignment.data.remote

import com.example.testassignment.core.model.SearchListModel
import com.example.testassignment.utils.AppConstants
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET(AppConstants.GET_SEARCH_DETAIL)
    suspend fun getSpecificSearchDetails(
        @Query("q") searchReference: String,
    ): Response<SearchListModel>

}