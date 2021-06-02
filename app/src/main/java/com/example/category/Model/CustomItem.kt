package com.example.category.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CustomItem(
    val category: Int,
    val description: String,
    val founder_name: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val product_url: String,
    val title: String,
    val upvotes: Int,

    val isSelected: Boolean = false
): Parcelable