package com.example.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.category.Adapters.CustomAdapter
import com.example.category.Model.CustomItem
import kotlinx.android.synthetic.main.activity_detailed.*

class Detailed : AppCompatActivity() {

    private lateinit var custom: CustomItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        custom = intent.getParcelableExtra<CustomItem>(CustomAdapter.ITEM_KEY)!!

        setDataInDetailed()

    }

    private fun setDataInDetailed() {
        text_title.text = custom.title
        text_name.text = custom.founder_name
        text_Category.text = custom.category.toString()
        text_description.text = custom.description
        text_upvotes.text = custom.upvotes.toString()

        Glide.with(this)
            .load(custom.image)
            .into(image)

        webView.apply {
            settings.javaScriptEnabled = true
            settings.loadsImagesAutomatically = true
            webViewClient = WebViewClient()
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            loadUrl(custom.product_url)
        }

    }
}