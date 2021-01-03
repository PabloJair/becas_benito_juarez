package com.s10plus.core_application.ui.horizontal_carrousel

import android.view.LayoutInflater
import android.view.ViewGroup
import com.s10plus.core_application.base_ui.BaseViewHolder
import com.s10plus.core_application.databinding.BasicHorizontalCarruselVhBinding
import com.squareup.picasso.Picasso

class HorizontalCarruselViewHolder(viewGroup:ViewGroup):BaseViewHolder<BasicHorizontalCarruselVhBinding,BasicHorizontalCarruselModel>(
BasicHorizontalCarruselVhBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
) {
    override fun setup(item: BasicHorizontalCarruselModel) {

        if(item.url_Image.isNotEmpty())
        Picasso.get().load(item.url_Image).into(binding.imageView)

        binding.carrouselText.text = item.text


    }


}