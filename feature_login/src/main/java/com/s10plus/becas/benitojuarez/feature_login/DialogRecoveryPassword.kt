package com.s10plus.becas.benitojuarez.feature_login

import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.s10plus.becas.benitojuarez.feature_login.databinding.DialogRecoveryPasswordBinding
import com.s10plus.core_application.base_ui.dialog.BaseDialogFragment
import com.s10plus.core_application.databinding.DialogGenericBinding

class DialogRecoveryPassword : BaseDialogFragment<DialogRecoveryPasswordBinding>(R.layout.dialog_recovery_password){

    var onClick:((email:String,dialog: DialogFragment)->Unit)?=null
    override fun setupView() {


        binding.recovery.setOnClickListener {


            if(binding.correo.editText?.text.toString().isEmpty()){
                binding.correo.error = "Debes ingresar tu correo"
                return@setOnClickListener
            }
            onClick?.invoke(binding.correo.editText?.text.toString(),this)
        }
    }

    override fun onStart() {
        super.onStart()

        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val heigth = LinearLayout.LayoutParams.WRAP_CONTENT

        dialog!!.window!!.setLayout(width,heigth)


    }
    override fun init() {

    }


}