package com.s10plus.becas.feature_main.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.s10plus.becas.feature_main.databinding.ItemMenuBinding
import com.s10plus.core_application.base_ui.BaseViewHolder
import com.s10plus.core_application.models.ItemMenu

class MenuAdapter: RecyclerView.Adapter<BaseViewHolder<*,ItemMenu>>() {


    var onClickItem:((item:ItemMenu, view: ViewDataBinding)->Unit)?=null

    var  items: List<ItemMenu> = arrayListOf()
    set(value) {

        field = if(value.isNotEmpty()) {
            notifyDataSetChanged()
            value
        } else arrayListOf()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BaseViewHolder<*,ItemMenu> {

        return ItemMenuViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<*,ItemMenu>, position: Int){
        holder.setup(item = items[position])

        holder.onClickItem = onClickItem


    }
}