package com.example.testassignment.utils.databindingutlis

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testassignment.core.adapters.SearchAdapter
import com.example.testassignment.core.model.ItemList
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

@BindingAdapter("searchList")
fun bindingRecyclerViewTerminalList(
    view: RecyclerView,
    dataList: ArrayList<ItemList>?
) {
    if (dataList?.isEmpty() == true)
        return
    val layoutManager = view.layoutManager
    if (layoutManager == null)
        view.layoutManager = LinearLayoutManager(view.context)
    var adapter = view.adapter
    if (adapter == null) {
        adapter = dataList?.let {
         //   it.sortBy { it.id }
            it.sortWith(compareBy { it.login })

            SearchAdapter(view.context, it)
        }
        view.adapter = adapter
    }
}