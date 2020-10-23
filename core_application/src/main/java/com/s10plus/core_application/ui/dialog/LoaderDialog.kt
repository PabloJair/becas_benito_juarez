package com.s10plus.core_application.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentManager
import com.s10plus.core_application.R
import com.s10plus.core_application.base_ui.dialog.BaseDialogFragment
import com.s10plus.core_application.databinding.DialogLoaderBinding

class
LoaderDialog :
    BaseDialogFragment<DialogLoaderBinding>(R.layout.dialog_loader){
    override fun setupView() {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    override fun init() {
        this.isCancelable = false
    }


    companion object{

        private var onlyInstance: LoaderDialog?=null;
        fun newInstance(): LoaderDialog =
            LoaderDialog()

        fun showDialog(supportFragmentManager: FragmentManager){

            if(onlyInstance !=null ) {
                onlyInstance!!.show(supportFragmentManager,"LOADER")
            } else {
                onlyInstance =
                    newInstance()

                if(!onlyInstance!!.isVisible)
                onlyInstance?.show(supportFragmentManager,"LOADER")

            }
        }

        fun hideDialog(){

            if(onlyInstance !=null ){

                if(onlyInstance!!.isVisible)
                    onlyInstance?.dismiss()
            }



        }
    }
}