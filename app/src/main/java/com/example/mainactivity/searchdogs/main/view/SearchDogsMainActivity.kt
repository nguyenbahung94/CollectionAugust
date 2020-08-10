package com.example.mainactivity.searchdogs.main.view

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mainactivity.R
import com.example.mainactivity.searchdogs.error.ResultWrapper
import com.example.mainactivity.searchdogs.extensions.showToast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search_dogs.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class SearchDogsMainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_dogs)
        recyclerDogs.adapter = adapter
        subscribeObservers()
        initListeners()
        viewModel.setSearchQuery("")
    }

    private fun initListeners() {

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.setSearchQuery(it) }
                return true
            }
        }
        )
        loadMore.setOnClickListener {
            viewModel.fetchDogsFlow()
        }
    }

    @ExperimentalCoroutinesApi
    private fun subscribeObservers() {
        viewModel.snackbar.observe(this, Observer { text ->
            text?.let {
                Snackbar.make(root_layout, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        })

        viewModel.dogListLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.liveDateFetch.observe(this, Observer {
            when (it) {
                is ResultWrapper.Loading -> {
                }
                is ResultWrapper.NetworkError -> {
                    showToast("NO internet")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
       // viewModel.fetchDogsFlow()
    }

}