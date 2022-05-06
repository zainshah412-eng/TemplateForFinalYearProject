package com.example.testassignment.core.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.android.BR
import com.example.android.databinding.SearchListItemBinding
import com.example.testassignment.core.model.ItemList
import kotlin.collections.ArrayList

class SearchAdapter(
    private val context: Context,
    private var dataList: ArrayList<ItemList>
) :
    RecyclerView.Adapter<SearchAdapter.BindViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder {

        val rootView: ViewDataBinding =
            SearchListItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return BindViewHolder(rootView)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(viewHolder: BindViewHolder, position: Int) {
        val item = dataList[position]
        viewHolder.itemBinding.setVariable(BR.dataList, item)
        viewHolder.itemBinding.executePendingBindings()
    }


    class BindViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }



}