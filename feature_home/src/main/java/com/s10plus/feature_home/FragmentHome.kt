package com.s10plus.feature_home

import com.blankj.utilcode.util.GsonUtils
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.core_application.mocks.Mocks
import com.s10plus.core_application.models.*
import com.s10plus.core_application.ui.ButtonS10Plus
import com.s10plus.core_application.ui.FactoryUI
import com.s10plus.core_application.ui.ImageViewS10Plus
import com.s10plus.core_application.ui.LinearLayoutS10Plus
import com.s10plus.core_application.ui.horizontal_carrousel.BasicHorizontalCarrouselAdapter
import com.s10plus.core_application.ui.horizontal_carrousel.BasicHorizontalCarruselModel
import com.s10plus.feature_home.databinding.FragmentHomeBinding

class FragmentHome:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val adapter: BasicHorizontalCarrouselAdapter by lazy { BasicHorizontalCarrouselAdapter() }


    override fun setupView() {


        /*Mocks.viewMock("Vista-uno").body.layout.forEach {

            var Layout = LinearLayoutS10Plus.createForModel(requireContext(),null,it,binding.rootView)




        }*/


    }

    override fun setupObserver() {
    }

    override fun setupViewModel() {
    }

    override fun init() {
    }
}