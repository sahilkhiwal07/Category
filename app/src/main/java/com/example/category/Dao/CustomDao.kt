package com.example.category.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.category.Model.CustomItem

@Dao
interface CustomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProduct(product: List<CustomItem>)

    @Query("select * from CustomItem")
    fun getAllProduct(): LiveData<List<CustomItem>>

    // Selecting product with category

    @Query("select * from CustomItem where category = :category")
    fun getProductForCategory(category: Int):LiveData<List<CustomItem>>

    // Bookmark

    @Query("select * from CustomItem where isSelected = 1")
    fun getAllBookmark(): LiveData<List<CustomItem>>

    @Query("select isSelected from CustomItem where id = :id")
    suspend fun updateBookmark(id: Int): Boolean

    @Query("update CustomItem set isSelected = 1 where id = :id")
    suspend fun isChecked(id: Int)

    @Query("update CustomItem set isSelected = 0 where id = :id")
    suspend fun isNotChecked(id: Int)

}