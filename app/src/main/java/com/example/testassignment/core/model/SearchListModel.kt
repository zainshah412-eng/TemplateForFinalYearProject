package com.example.testassignment.core.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SearchListModel(
    @SerializedName("total_count")
    var totalCount: Int = 0,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false,
    @SerializedName("items")
    var items: ArrayList<ItemList> = ArrayList()
) : Parcelable