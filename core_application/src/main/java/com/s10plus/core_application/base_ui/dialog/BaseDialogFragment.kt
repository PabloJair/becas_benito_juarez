package com.s10plus.core_application.base_ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<T: ViewDataBinding>(@LayoutRes private var layoutId:Int): DialogFragment( ) {
    lateinit var binding: T

    abstract fun setupView()

    abstract fun init()

    open fun setupArguments(arguments:Bundle?){}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = DataBindingUtil.inflate(LayoutInflater.from(context),layoutId,null,false)
        setupArguments(arguments)
        setupView()
        init()
        return binding.root
    }


    override fun onStart() {
        super.onStart()

        val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
        val heigth = LinearLayout.LayoutParams.WRAP_CONTENT

        dialog!!.window!!.setLayout(width,heigth)


    }
}