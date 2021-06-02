package com.example.category.Repository

import android.app.Application
import com.example.category.Dao.CustomDao
import com.example.category.Database.CustomDatabase
import com.example.category.Model.CustomItem

class CustomRepository(application: Application) {
    private val customDao: CustomDao

    init {
        val db = CustomDatabase.getDatabase(application)
        customDao = db.customDao()
    }

    suspend fun insertAllProduct(product: List<CustomItem>) = customDao.insertAllProduct(product)

    fun getAllProduct() = customDao.getAllProduct()

    fun getProductForCategory(category: Int) = customDao.getProductForCategory(category)

    fun getAllBookmark() = customDao.getAllBookmark()

    suspend fun updateBookmark(product: CustomItem) {
        val id = product.id
        val test = customDao.updateBookmark(id)
        if (test) customDao.isNotChecked(id)
        else customDao.isChecked(id)

    }


}