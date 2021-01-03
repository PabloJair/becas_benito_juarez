package com.s10plus.becas.benitojuarez.feature_login

import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.s10plus.becas.benitojuarez.feature_login.databinding.DialogSendRecoveryPasswordBinding
import com.s10plus.core_application.base_ui.dialog.BaseDialogFragment

class DialogSendRecoveryPassword:BaseDialogFragment<DialogSendRecoveryPasswordBinding>(R.layout.dialog_send_recovery_password) {

    var onClick:((cod:String,password:String,dialog: DialogFragment)->Unit)?=null

    override fun setupView() {

        binding.recovery.setOnClickListener {


            if(binding.codigo.editText?.text.toString().isEmpty()){
                binding.codigo.error = "Debes ingresar tu correo"
                return@setOnClickListener
            }
            if(binding.password.editText?.text.toString().isEmpty()){
                binding.password2.error = "Debes ingresar una contraseña nueva"
                return@setOnClickListener
            }
            if(binding.password2.editText?.text.toString().isEmpty()){
                binding.password2.error = "Este campo es obligatorio"
                return@setOnClickListener
            }

            if(binding.password2.editText?.text.toString()!=binding.password.editText?.text.toString()){
                binding.password2.error = "Las contraseñas no coninciden"
                return@setOnClickListener
            }
            onClick?.invoke(binding.codigo.editText?.text.toString(),binding.password.editText?.text.toString(),this)
        }
    }

    override fun init() {
    }

    override fun onStart() {
        super.onStart()

        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val heigth = LinearLayout.LayoutParams.WRAP_CONTENT

        dialog!!.window!!.setLayout(width,heigth)


    }
}