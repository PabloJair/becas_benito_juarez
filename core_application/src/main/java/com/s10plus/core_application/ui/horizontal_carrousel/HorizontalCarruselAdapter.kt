package com.s10plus.core_application.ui.horizontal_carrousel

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s10plus.core_application.base_ui.BaseAdapter
import com.s10plus.core_application.base_ui.BaseViewHolder
import com.s10plus.core_application.databinding.BasicHorizontalCarruselVhBinding

class BasicHorizontalCarrouselAdapter:BaseAdapter<BasicHorizontalCarruselModel,BaseViewHolder<BasicHorizontalCarruselVhBinding,BasicHorizontalCarruselModel>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BasicHorizontalCarruselVhBinding, BasicHorizontalCarruselModel>  = HorizontalCarruselViewHolder(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<BasicHorizontalCarruselVhBinding, BasicHorizontalCarruselModel>, position: Int) {

        holder.setup(item = items[position])
    }


}