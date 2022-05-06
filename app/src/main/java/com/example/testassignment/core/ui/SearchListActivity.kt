package com.example.testassignment.core.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.BR
import com.example.android.R
import com.example.android.databinding.ActivitySearchListBinding
import com.example.testassignment.core.adapters.SearchAdapter
import com.example.testassignment.core.interfaces.SearchActivityInterface
import com.example.testassignment.core.model.ItemList
import com.example.testassignment.core.viewmodel.AndroidTestAssignmentViewModel
import com.example.testassignment.utils.Status
import com.kaopiz.kprogresshud.KProgressHUD
import dagger.hilt.android.AndroidEntryPoint
import getListRemainingSize
import showSnackBar
import toast
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SearchListActivity : AppCompatActivity(), SearchActivityInterface {

    private lateinit var binding: ActivitySearchListBinding
    private val viewModel: AndroidTestAssignmentViewModel by viewModels()
    open lateinit var loaderDialog: KProgressHUD
    private var isLoading: Boolean = false
    var searchAdapter: SearchAdapter? = null
    private var itemList = mutableListOf<ItemList>()
    private var itemListNew = ArrayList<ItemList>()
    var searchWord: String = ""
    private var handler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_list)
        binding.setVariable(BR.data, itemList)
        binding.setVariable(BR.onClick, this)

        searchWord = intent.getStringExtra("searchWord").toString()

        initView()
        doCallForSearch(searchWord)
        setUpObserver()

    }

    fun initView() {
        loaderDialog = KProgressHUD(this)
    }


    private fun doCallForSearch(search: String) {
        viewModel.setSearchReference(search)
    }

    private fun setUpRecyclerViewForSearch() {
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.searchRev.layoutManager = layoutManager
        loadData(true)
        binding.searchRev.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == itemListNew!!.size - 1) {
                        loadData()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun setUpObserver() {
        viewModel.androidTestAssignmentResp.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (loaderDialog.isShowing)
                        loaderDialog.dismiss()

                    if (it.data!!.totalCount > 0) {
                        itemList?.clear()
                        itemList = it.data?.items!!
                        itemList.sortWith(compareBy { it.login })
                        setUpRecyclerViewForSearch()
                    } else {
                        binding.noSearchLayout.root.visibility = View.VISIBLE
                    }
                }
                Status.ERROR -> {
                    if (loaderDialog.isShowing)
                        loaderDialog.dismiss()
                    it.message?.message?.let { it1 -> showSnackBar(it1, window.decorView.rootView) }
                    if (it.message?.message == "401" || it.message?.message?.isEmpty() == true) {

                    }
                }
                Status.LOADING -> {
                    if (!loaderDialog.isShowing) {
                        loaderDialog.show()
                    }
                }
            }
        }
    }


    fun loadData(isInitLoad: Boolean = false) {
        binding.progressCircular.visibility = View.VISIBLE
        if (isInitLoad) {
            if (itemList!!.size > 9) {
                for (obj in 0 until 9) {
                    itemListNew.add(itemList!!.get(obj))
                }
                updateDataList(itemListNew)
            } else {
                for (obj in 0 until itemList.size) {
                    itemListNew.add(itemList!!.get(obj))
                }
                updateDataList(itemListNew)
            }
            binding.progressCircular.visibility = View.GONE
        } else {
            handler.postDelayed(Runnable {
                val end = getListRemainingSize(itemListNew!!.size, itemList!!.size)
                itemListNew.clear()
                for (obj in 0 until end) {
                    itemListNew.add(itemList!!.get(obj))
                }
                updateDataList(itemListNew)
                isLoading = false
                binding.progressCircular.visibility = View.GONE
            }, 2000)
        }
    }

    fun updateDataList(newList: List<ItemList>) {
        binding.setVariable(BR.data, newList)
    }

    override fun onBackClick() {
        finish()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        Log.d("OnResume", "Called")
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}