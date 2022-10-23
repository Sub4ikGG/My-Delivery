package ru.spoonbill.app.viewbinding.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, uri: String?) {
    if(uri != null )
        Glide.with(view.context)
            .load(uri)
            .into(view)
}