package com.s10plus.core_application.base_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment <T:ViewDataBinding>(@LayoutRes val layoutId:Int):Fragment(){

    open var name=""

    lateinit var binding:T

    abstract fun setupView()
    abstract fun setupObserver()
    abstract fun setupViewModel()
    open  lateinit var  activity:BaseActivity<*>

    open fun setupInjection(){}

    abstract fun init()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = setupInflatedView(inflater, container)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupConfigFragment()
        setupInjection()
        setupViewModel()
        setupObserver()
        setupView()
        init()

    }

    private fun setupConfigFragment(){

        activity = getActivity() as BaseActivity<*>

    }

    protected open fun setupInflatedView(inflater: LayoutInflater,container: ViewGroup?):View{

        binding = DataBindingUtil.inflate(inflater,layoutId,container,false)
        return binding.root
    }

}