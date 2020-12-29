package com.s10plus.feature_home

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.forEach
import com.s10plus.core_application.analytics.AnalyticsViewModel
import com.s10plus.core_application.base_ui.ActivityUtils
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.feature_home.Url.BEB_2
import com.s10plus.feature_home.databinding.FragmentBebBinding
import com.s10plus.feature_home.models.DetailsModel
import com.s10plus.feature_home.models.MenuButtonsModel
import kotlinx.android.synthetic.main.fragment_beb.*
import kotlinx.android.synthetic.main.header_layout.view.*
import kotlin.properties.Delegates

class FragmentDetailMenu :BaseFragment<FragmentBebBinding>(R.layout.fragment_beb){

    lateinit var text:DetailsModel
    var showButtonBack by Delegates.notNull<Boolean>()
    lateinit var headerText: String
    lateinit var analyticsViewModel: AnalyticsViewModel

    override fun setupView() {
        text = requireArguments().getParcelable(DATA)?: DetailsModel()
        showButtonBack = requireArguments().getBoolean(FragmentMenu.BACK_BUTTON,false)
        headerText = requireArguments().getString(FragmentMenu.HEADER_TEXT,"")


        binding.header.image.visibility = View.GONE

        binding.header.text.text = headerText

        text.texts.forEach {

            binding.body.addView(TextView(requireContext()).apply {

                text = HtmlCompat.fromHtml(it.text,HtmlCompat.FROM_HTML_MODE_COMPACT)
                setOnClickListener { view ->
                    if (it.url.isNotEmpty()) {
                        analyticsViewModel.sendClicks(it.id,it.moreInformation)

                        ActivityUtils.openWebView(requireContext(), it.url)

                    }else if(it.email.isNotEmpty()){
                        ActivityUtils.openEmail(requireContext(), it.email)

                    }


                    analyticsViewModel.sendClicks(it.id,it.moreInformation)


                }

            })
        }



        binding.backButton.onClick={
            activity.onBackPressed()
        }

    }

    override fun setupObserver() {
    }

    override fun setupViewModel() {
        analyticsViewModel = BaseViewModel.getViewModel(this,AnalyticsViewModel::class.java)
    }

    override fun init() {
    }

    companion object{
        const val DATA ="DATA"
        const val BACK_BUTTON ="BACK_BUTTON"
        const val HEADER_TEXT ="HEADER_TEXT"
        fun newInstance(texts:DetailsModel, showButtonBack:Boolean =false, headerText:String=""): FragmentDetailMenu {
            val fragment = FragmentDetailMenu()
            val args = Bundle().also {
                it.putParcelable(DATA, texts)
                it.putBoolean(BACK_BUTTON, showButtonBack)
                it.putString(HEADER_TEXT, headerText)
            }

            fragment.arguments = args
            return fragment
        }

    }
}