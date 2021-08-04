package com.example.yelpedapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.yelpedapp.databinding.ErrorLoadingViewBinding

class ErrorLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(
    context,
    attrs, defStyleAttr
) {
    private val binding = ErrorLoadingViewBinding.inflate(LayoutInflater.from(context), this)

    fun showLoading() {
        this.visibility = VISIBLE
        binding.progressBar.visibility = VISIBLE
        binding.errorMessageView.visibility = GONE
    }

    fun showError() {
        this.visibility = VISIBLE
        binding.progressBar.visibility = GONE
        binding.errorMessageView.visibility = VISIBLE
    }

    fun hide() {
        visibility = GONE
    }
}