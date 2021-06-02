package com.example.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.category.Adapters.CustomAdapter
import com.example.category.CustomLoader.LoadService
import com.example.category.ViewModel.CustomViewModel
import com.example.category.ViewModel.MyFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CustomViewModel

    private val adapter by lazy {                                                                       // Initializing adapter
        CustomAdapter(this){
            viewModel.updateBookmark(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRecyclerView()
        getDataFromViewModel()
        getButtons()
    }

    private fun getButtons() {

        btn_all_category.setOnClickListener {
            viewModel.getAllProduct().observe(this, Observer {
                adapter.submitList(it)
            })
        }

        btn_1.setOnClickListener {
            getProductForAllCategory(1)
        }

        btn_2.setOnClickListener {
            getProductForAllCategory(2)
        }

        btn_3.setOnClickListener {
            getProductForAllCategory(3)
        }

        btn_4.setOnClickListener {
            getProductForAllCategory(4)
        }

        btn_bookmark.setOnClickListener {
            getProductForAllCategory(-1)
        }

    }

    private fun getProductForAllCategory(category: Int) {
        viewModel.getProductForCategory(category)
    }

    private fun getDataFromViewModel() {
        viewModel = ViewModelProvider(this, MyFactory(application)).get(CustomViewModel::class.java)         // Initializing View model

        viewModel.getAllProduct().observe(this, Observer {
            adapter.submitList(it)                                                                                 // Showing all product in recycler view
        })

        viewModel.filteredProductList.observe(this, Observer {
            adapter.submitList(it)                                                                                 // getting filtered list for bookmark and all category
        })

        viewModel.makeApiCall()

    }

    private fun getRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)                                   // Setting recycler view
    }

}