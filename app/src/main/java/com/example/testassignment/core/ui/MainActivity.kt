package com.example.testassignment.core.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.example.android.databinding.ActivityMainBinding
import com.example.testassignment.core.model.ItemList
import dagger.hilt.android.AndroidEntryPoint
import getString
import hideSoftKeyboard
import isNumber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    fun initView() {
        binding.btnSearch.setOnClickListener(this)
        binding.edtSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideSoftKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSearch -> {
                if (binding.edtSearch.getString().isNullOrBlank() || isNumber(binding.edtSearch.getString())) {
                    binding.searchError.visibility = View.VISIBLE
                } else {
                    binding.searchError.visibility = View.GONE
                    val intent = Intent(this, SearchListActivity::class.java)
                    intent.putExtra("searchWord", binding.edtSearch.getString().trim())
                    startActivity(intent)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        Log.d("OnResume","Called")
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}