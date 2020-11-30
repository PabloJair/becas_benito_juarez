package com.s10plus.becas.benitojuarez.feature_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.s10plus.becas.benitojuarez.feature_login.databinding.ActivityLoginViewBinding
import com.s10plus.becas.benitojuarez.feature_login.di.injectFeature
import com.s10plus.becas.benitojuarez.feature_login.view_model.LoginViewModel
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.base_ui.BaseFethData
import com.s10plus.core_application.models.BaseModel
import com.s10plus.core_application.models.ItemMenu
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.ui.dialog.TypeDialog
import com.s10plus.core_application.utils.Constans.Companion.DATA_EXTRAS
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginView:BaseActivity<ActivityLoginViewBinding>(R.layout.activity_login_view),View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()
    lateinit var callbackManager: CallbackManager
    lateinit var mGoogleSignInClient:GoogleSignInClient
    private var email :String=""

    override fun setupView() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("811852281615-ngrnhig4j2a6ql730hdlcrbbj6dje9gc.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if(account != null){
            val googleEmail = account?.email ?: ""
            acceslog(true, googleEmail);

        }else if(isLoggedIn){

                acceslog(true, "");


        }
        binding.googleSign.setOnClickListener {
            signIn();
        }
        binding.login.setOnClickListener {

            val info =UserInformation(
                    email = "pablo.angeles@s10plus.com", modules = arrayListOf(
                    ItemMenu("", 1, text_item = "Menu Principal"),
                    ItemMenu("", 2, "Nosotros"),
                    ItemMenu("", 3, "Trasparencia"),
                    ItemMenu("", 4, "Controlaría Social")
            )
            )

            val intent = AppNavigation.openMainView(this).apply {
                putExtra(DATA_EXTRAS, info)

            }

            startActivity(intent)
            finish()
        }

        callbackManager = CallbackManager.Factory.create()

        binding.facebookButton.registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        Log.i("logFace", "a")
                        val accessToken = AccessToken.getCurrentAccessToken()

                        val request = GraphRequest.newMeRequest(
                                result?.accessToken
                        ) { `object`, response ->
                            // Application code
                            val fbName = response.jsonObject.getString("name")
                            val fbemail = response.jsonObject.getString("email")
                            Log.i("logFace", "a")
                            acceslog(true, fbemail);

                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id,name,email")
                        request.parameters = parameters
                        request.executeAsync()
                    }

                    override fun onCancel() {
                        Log.i("logFace", "a")
                        acceslog(true, "");


                    }

                    override fun onError(error: FacebookException?) {
                        Log.i("logFace", "a")
                        acceslog(true, "");


                    }


                })


    }
     fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 200)
    }
    fun acceslog(acces: Boolean, email: String){

        if(acces){
            val info =UserInformation(
                    email = email, modules = arrayListOf(
                    ItemMenu("", 1, text_item = "Menu Principal"),
                    ItemMenu("", 2, "Nosotros"),
                    ItemMenu("", 3, "Trasparencia"),
                    ItemMenu("", 4, "Controlaría Social")
            )
            )

            val intent = AppNavigation.openMainView(this).apply {
                putExtra(DATA_EXTRAS, info)

            }

            startActivity(intent)
            finish()

        }else{
            Toast.makeText(applicationContext, "No se Puede iniciar sesion", Toast.LENGTH_LONG).show()
        }
    }

    override fun setupViewModel() {

    }

    override fun setupObserver() {
        viewModel.liveDataManager.observe(this, Observer {
            when (it) {
                is BaseFethData.Error -> {
                    showDialog(
                            TypeDialog.ERROR,
                            "¡UPS!",
                            it.message,
                            ok = { dialog -> dialog.dismiss() })


                }
                is BaseFethData.Success -> {
                    when (it.data) {

                        is UserInformation -> {
                            val info = (it.data as UserInformation)

                            if (info != null) {

                                val intent = AppNavigation.openMainView(this).apply {
                                    putExtra(DATA_EXTRAS, info)

                                }

                                startActivity(intent)
                                finish()

                            }
                        }
                        is BaseModel<*> -> {
                            var dialogSendRecoveryPassword: DialogSendRecoveryPassword =
                                    DialogSendRecoveryPassword()
                            var info = it.data as BaseModel<String>

                            if (info.data == "1") {
                                showDialog(
                                        TypeDialog.SUCCESS,
                                        "¡Verifica tu correo!",
                                        info.message as String,
                                        ok = { dialog ->

                                            var dialogSendRecoveryPassword: DialogSendRecoveryPassword =
                                                    DialogSendRecoveryPassword()
                                            dialogSendRecoveryPassword.show(
                                                    supportFragmentManager,
                                                    "TAG"
                                            )
                                            dialogSendRecoveryPassword.onClick =
                                                    { cod, password, dialog ->

                                                        viewModel.sendPassword(email, password, cod)
                                                        dialog.dismiss()


                                                    }


                                        })
                            } else if (info.data == "2") {


                                showDialog(
                                        TypeDialog.SUCCESS,
                                        "Correcto!!",
                                        info.message as String,
                                        ok = {

                                            ModalGenericDialog ->
                                            ModalGenericDialog.dismiss()

                                        })
                            }


                        }
                    }

                }
                is BaseFethData.Loader -> {
                    if (it.isShow) showLoader() else hideLoader()
                }

            }
        })

    }

    override fun init() {

    }


    private fun validateForm():Boolean{

        var validate = true
        if(binding.correo.editText!!.text.toString().isNullOrEmpty()){
            binding.correo.error="Ingresa tu correo"
            validate = false
        }
        if(binding.password.editText!!.text.toString().isNullOrEmpty()){
            binding.correo.error="Ingresa tu password"
            validate = false
        }

        return validate

    }

    override fun setupInjection() {
        injectFeature()

    }

    override fun onClick(v: View?) {

        when(v){


        }

    }


    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                    ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID", googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)
            acceslog(true, googleEmail);
        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                    "failed code=", e.statusCode.toString()
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 200 ){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

}