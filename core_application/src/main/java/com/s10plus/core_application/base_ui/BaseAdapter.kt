package com.s10plus.core_application.base_ui

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.s10plus.core_application.models.UserInformation

abstract class BaseAdapter<T, I:BaseViewHolder<*,T>>:RecyclerView.Adapter<I>() {

    var onClickItem:((item: T,view: ViewDataBinding)->Unit)?=null
    private lateinit var recyclerView:RecyclerView
    private var  filter: ArrayList<T> = arrayListOf()
    var  items: ArrayList<T> = arrayListOf()
        set(value) {

            field = if(value.isNotEmpty()) {
                notifyDataSetChanged()
                filter = value

                value
            } else arrayListOf()

        }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView= recyclerView;
    }

    override fun getItemCount(): Int = items.size
    fun insertNewElement(addElement:T){
        items.add(addElement)
        notifyItemRangeInserted(items.size+1,items.size)
        recyclerView.smoothScrollToPosition(items.size+1)



    }
}