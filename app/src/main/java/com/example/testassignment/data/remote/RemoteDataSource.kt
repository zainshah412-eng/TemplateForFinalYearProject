package com.example.testassignment.data.remote

import com.example.testassignment.data.remote.ApiService
import com.example.testassignment.data.remote.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) : BaseDataSource() {

    suspend fun getSpecificSearchDetails(searchReference: String) =
        getResult {
            apiService.getSpecificSearchDetails(searchReference)
        }
}