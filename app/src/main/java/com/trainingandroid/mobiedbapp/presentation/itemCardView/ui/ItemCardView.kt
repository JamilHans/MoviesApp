package com.trainingandroid.mobiedbapp.presentation.itemCardView.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import com.trainingandroid.mobiedbapp.R

class ItemCardView(context: Context, attrs: AttributeSet? = null) : MaterialCardView(context,attrs) {
    private var logoView: ImageView
    private var textView: TextView
    private var ratingBar: AppCompatRatingBar
    private val minimumScore = 0f
    private val minimumStars = 0f
    private val maxStars = 5f
    private val maximumScore = 10f

    init {
        LayoutInflater.from(context).inflate(R.layout.item_card_view, this, true)
        logoView = findViewById(R.id.imgMovie)
        textView = findViewById(R.id.txtTitle)
        ratingBar = findViewById(R.id.ratingBar)
    }

    fun setTitle(name: String) {
        textView.text = name
    }

    fun setImg(urlCode: String) {
        Picasso.get().load(urlCode).error(R.drawable.ic_launcher_background).into(logoView)
    }

    fun setRatingBar(rating: Float) {
        val valorEstrellas = ((rating - minimumScore) / (maximumScore - minimumScore)) *
                (maxStars - minimumStars) + minimumStars
        ratingBar.rating = valorEstrellas
    }

}
