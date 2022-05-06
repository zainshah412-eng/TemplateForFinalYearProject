package com.example.testassignment.core.repo

import com.example.testassignment.data.remote.RemoteDataSource
import com.example.testassignment.data.remote.performGetOperation
import javax.inject.Inject

class AndroidTestAssignmentRepo@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    fun getSpecificSearchDetails(searchReference: String) =
        performGetOperation(
            networkCall = { remoteDataSource.getSpecificSearchDetails(searchReference) }
        )
}