package com.s10plus.core_application.ui.dialog

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.FragmentManager
import com.s10plus.core_application.R
import com.s10plus.core_application.base_ui.dialog.BaseDialogFragment
import com.s10plus.core_application.databinding.DialogGenericBinding


class ModalGenericDialog:BaseDialogFragment<DialogGenericBinding>(R.layout.dialog_generic) {

    private lateinit var data:DialogData
    private var onClickButton1:((ModalGenericDialog)->Unit)?=null
    private var onClickButton2:((ModalGenericDialog)->Unit)?=null

    override fun setupView() {
        binding.apply {

            if(data.showIcon){
                icon.visibility = VISIBLE
                icon.setBackgroundResource(data.icon!!)

                iconLottie.visibility = GONE
            }else{
                icon.visibility = GONE
                iconLottie.setAnimation(data.lottieFile!!)
                iconLottie.visibility = VISIBLE
            }


            btn1.visibility = if(data.button1DialogData.show){
                btn1.text   = data.button1DialogData.text
                VISIBLE
            }else{
                GONE
            }
            btn2.visibility = if(data.button2DialogData.show){
                btn2.text   = data.button2DialogData.text
                VISIBLE
            }else{
                GONE
            }

            titleDialog.text        = data.title
            messageDialog.text      = data.message

            btn1.setOnClickListener {
                onClickButton1?.invoke(this@ModalGenericDialog) }
            btn2.setOnClickListener { onClickButton2?.invoke(this@ModalGenericDialog) }

        }

                // this.dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_background_rounded)
    }

    override fun init() {

        binding.iconLottie.playAnimation()

    }

    override fun setupArguments(arguments: Bundle?) {


        data = arguments?.getParcelable(DIALOG_DATA)?: DialogData()

    }
    companion object{
        val DIALOG_DATA ="DIALOG_DATA"
        val TYPE_DIALOG ="TYPE_DIALOG"
        val TAG_DIALOG_INFO ="TAG_DIALOG_INFO"
        val TAG_DIALOG_ERROR ="TAG_DIALOG_ERROR"
        val TAG_DIALOG_SUCCESS="TAG_DIALOG_SUCCESS"
        val TAG_DIALOG_WARMING ="TAG_DIALOG_WARMING"

        private val instance:ModalGenericDialog?=null
        fun showInformationDialog(fragmentManager: FragmentManager,
                                  title:String="",
                                  message:String="",
                                  textCancel:String ="Cancelar",textYes:String="Aceptar",
                                  onClickButton1:((ModalGenericDialog)->Unit)?=null,  onClickButton2:((ModalGenericDialog)->Unit)?=null){

            val data = DialogData(title, message,
                ButtonDialogData("Cancel",show = true),
                ButtonDialogData("OK",R.drawable.ic_check,true),
                lottieFile =  R.raw.information_lottie)


            // Supply num input as an argument.

            // Supply num input as an argument.

            ModalGenericDialog().apply {

                arguments = Bundle().apply {
                    putParcelable(DIALOG_DATA,data)
                }
                show(fragmentManager, TAG_DIALOG_INFO)
                this.onClickButton1 = onClickButton1
                this.onClickButton2 = onClickButton2


            }





        }


        fun showSuccessDialog(fragmentManager: FragmentManager,
                                  title:String="",
                                  message:String="",
                                  textCancel:String ="Cancelar",textYes:String="Acepter",
                              onClickButton1:((ModalGenericDialog)->Unit)?=null,  onClickButton2:((ModalGenericDialog)->Unit)?=null){

            val data = DialogData(title, message,
                ButtonDialogData("Cancel",show = false),
                ButtonDialogData("OK",R.drawable.ic_check,true),
                lottieFile =  R.raw.success_lottie)


            // Supply num input as an argument.

            // Supply num input as an argument.

            ModalGenericDialog().apply {

                arguments = Bundle().apply {
                    putParcelable(DIALOG_DATA,data)
                }
                show(fragmentManager, TAG_DIALOG_INFO)
                this.onClickButton1 = onClickButton1
                this.onClickButton2 = onClickButton2

            }





        }



        fun showErrorDialog(fragmentManager: FragmentManager,
                              title:String="",
                              message:String="",
                              textCancel:String ="Cancelar",textYes:String="Acepter",
                            onClickButton1:((ModalGenericDialog)->Unit)?=null,  onClickButton2:((ModalGenericDialog)->Unit)?=null){

            val data = DialogData(title, message,
                ButtonDialogData("Cancel",show = false),
                ButtonDialogData("OK",R.drawable.ic_check,true),
                lottieFile =  R.raw.error_lottie)


            // Supply num input as an argument.

            // Supply num input as an argument.

            ModalGenericDialog().apply {

                arguments = Bundle().apply {
                    putParcelable(DIALOG_DATA,data)
                }
                show(fragmentManager, TAG_DIALOG_INFO)
                this.onClickButton1 = onClickButton1
                this.onClickButton2 = onClickButton2

            }





        }


        fun showWarmingDialog(fragmentManager: FragmentManager,
                            title:String="",
                            message:String="",
                            textCancel:String ="Cancelar",textYes:String="Acepter",
                              onClickButton1:((ModalGenericDialog)->Unit)?=null,  onClickButton2:((ModalGenericDialog)->Unit)?=null){

            val data = DialogData(title, message,
                ButtonDialogData("Cancel",show = false),
                ButtonDialogData("OK",R.drawable.ic_check,true),
                lottieFile =  R.raw.warming_lottie)


            // Supply num input as an argument.

            // Supply num input as an argument.

            ModalGenericDialog().apply {

                arguments = Bundle().apply {
                    putParcelable(DIALOG_DATA,data)
                }
                show(fragmentManager, TAG_DIALOG_INFO)
                this.onClickButton1 = onClickButton1
                this.onClickButton2 = onClickButton2

            }





        }
    }

}