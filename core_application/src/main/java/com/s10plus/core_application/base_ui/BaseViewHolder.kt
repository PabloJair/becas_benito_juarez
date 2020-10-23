package com.s10plus.core_application.base_ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.s10plus.core_application.ui.dialog.ModalGenericDialog

abstract class BaseViewHolder<T:ViewDataBinding,U>(protected var binding:T):RecyclerView.ViewHolder(binding.root) {



     var onClickItem:((item:U,view:T)->Unit)?=null
     abstract fun setup(item:U);

}