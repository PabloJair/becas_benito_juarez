package com.s10plus.core_application.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.LinearLayout
import androidx.core.content.ContextCompat

import com.s10plus.core_application.R
import com.s10plus.core_application.databinding.ButtonGreenBinding
import com.s10plus.core_application.databinding.ButtonNsBinding


class ButtonNSBecas(context: Context, var attrs: AttributeSet?=null): LinearLayout(context, attrs){

    private val binding:ButtonNsBinding  = ButtonNsBinding.inflate(LayoutInflater.from(context),this,true)

    var text = binding.text.text!!
    set(value) {
        binding.text.text = value
        field = value
    }
    public var model:Any? =null

    public fun setImageResource(idRes:Int)=binding.image.setImageResource(idRes)


    var showImage =true
        set(value) {
            field = value

            binding.image.visibility = if(field) View.VISIBLE else View.GONE
        }

    var onClick:((ns: NetworkSocial)->Unit)?=null
    var onClickVisibility:((view:View)->Unit)?=null
    init {
        try {


            orientation = LinearLayout.VERTICAL

            val ta = context.obtainStyledAttributes(attrs, R.styleable.green_button_attributes)
                .apply {
                    val referenceImg = getResourceId(R.styleable.green_button_attributes_image_button,R.drawable.ic_menu_gallery)
                    text =getText(R.styleable.green_button_attributes_text_button)?:""
                    binding.image.setImageResource(referenceImg)
                    binding.rootView.background=ContextCompat.getDrawable(context,R.drawable.button_ligth_blue)
                    binding.text.setTextColor(Color.WHITE)
                    binding.rootView.setOnClickListener {


                        binding.rs.visibility = if(binding.rs.visibility == GONE) VISIBLE else GONE
                        onClickVisibility?.invoke(binding.rs)
                    }

                    binding.facebook.setOnClickListener { onClick?.invoke(NetworkSocial.facebook) }
                    binding.twitter.setOnClickListener { onClick?.invoke(NetworkSocial.twitter) }
                    binding.youtube.setOnClickListener { onClick?.invoke(NetworkSocial.youtube) }

                    recycle()

                }

        }catch (e:Exception){}
    }

    enum class NetworkSocial{
        youtube,facebook,twitter
    }
    companion object {

        fun instance(context: Context,model:Any):ButtonNSBecas{

            return ButtonNSBecas(context).apply {



                this.model=model
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                    .apply {
                        setMargins(12,12,12,12)
                        requestLayout()
                    }


            }
        }
    }



}