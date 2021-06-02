package com.example.category.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.category.CustomLoader.LoadService
import com.example.category.Model.CustomItem
import com.example.category.Repository.CustomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val repository = CustomRepository(application)

    private val mutableProductList = MutableLiveData<Int>()

    val filteredProductList = Transformations.switchMap(mutableProductList) {
        if (it == -1) repository.getAllBookmark()                               // For all bookmark button
        else repository.getProductForCategory(it)                               // For Category wise button
    }

    fun getProductForCategory(category: Int) {
        mutableProductList.value = category
    }

    fun getAllProduct(): LiveData<List<CustomItem>> {
        return repository.getAllProduct()
    }

    fun updateBookmark(product: CustomItem) {
        viewModelScope.launch {
            repository.updateBookmark(product)
        }
    }

    private fun insertAllProduct(product: List<CustomItem>) {
        viewModelScope.launch {
            repository.insertAllProduct(product)
        }
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val custom = LoadService.instance.getAllData().body()                   // body() for networking call
            if (custom != null) {
                this@CustomViewModel.insertAllProduct(custom)                       // Inserting product list in database
            }
        }
    }


}