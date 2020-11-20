package com.s10plus.becas.feature_main.menu

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.s10plus.becas.feature_main.R
import com.s10plus.becas.feature_main.databinding.ItemMenuBinding
import com.s10plus.core_application.S10PlusApplication
import com.s10plus.core_application.base_ui.BaseViewHolder
import com.s10plus.core_application.models.ItemMenu

import com.squareup.picasso.Picasso

class ItemMenuViewHolder(itemBinding: ItemMenuBinding) :
    BaseViewHolder<ItemMenuBinding, ItemMenu>(itemBinding) {


    override fun setup(item: ItemMenu) {

        if (!(item.icon.contains("https:"))) {
            val uri = "@drawable/ic_${item.icon}"

            val iconModule: Int = S10PlusApplication.currentApplication.resources.getIdentifier(
                uri,
                null,
                S10PlusApplication.currentApplication.packageName
            )

            if (iconModule == 0) {
                binding.icon.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_whatshot))

            } else {
                binding.icon.setImageDrawable(ContextCompat.getDrawable(binding.root.context, iconModule))
            }

        } else {
            Picasso.get().load(item.icon).error(R.drawable.ic_whatshot)
                .placeholder(R.drawable.ic_whatshot).into(binding.icon)
        }


        binding.nameMenu.text = item.text_item;
        if(item.attributes.textColor.isNotEmpty())
            binding.nameMenu.setTextColor(Color.parseColor(item.attributes.textColor?:"#FFFFFFF"))

        binding.root.setOnClickListener {

            onClickItem?.invoke(item,this.binding)
        }


    }


}