package client.petmooby.com.br.petmooby

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import client.petmooby.com.br.petmooby.actvity.BaseActivity
import client.petmooby.com.br.petmooby.application.Application
import client.petmooby.com.br.petmooby.util.Preference
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() {

    private var callBackManager:CallbackManager?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        callBackManager = CallbackManager.Factory.create()
        //KeyFileGen.genSH1Base64(this)
        checkLoginin()
        with(btnLoginFace) {
            setPermissions("email","public_profile")

            LoginManager.getInstance().registerCallback(callBackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    var profile = Profile.getCurrentProfile()
                    var name = "${profile?.firstName}  ${profile?.lastName}"
                    var accessToken = loginResult.accessToken
                    var userIdFB = profile.id
                    Preference.set(this@LoginActivity, Preference.USER_NAME,name)
                    Preference.set(this@LoginActivity,Preference.USER_TOKEN,accessToken.token)
                    Preference.set(this@LoginActivity,Preference.USER_ID,userIdFB)
                    startMainActivity()
                }

                override fun onCancel() {
                    Log.d("FACE","cancel")
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    Log.d("FACE",exception.message)
                }
            })
        }




    }

    private fun checkLoginin() {
        if(Application.IS_DEBUG){
            toast("AUTO-LOGIN by debug mode")
            Preference.set(this@LoginActivity, Preference.USER_NAME,"Rafael Rocha debug")
            Preference.set(this@LoginActivity,Preference.USER_TOKEN,Preference.getFacebookDebugToken())
            Preference.set(this@LoginActivity,Preference.USER_ID,Preference.getFacebookDebugUserId())
            startMainActivity()
        }else {
            var accessToken = AccessToken.getCurrentAccessToken()
            var isLoggedIn = accessToken != null && !accessToken.isExpired
            if (isLoggedIn) {
                //Toast.makeText(this, "Já está logado", Toast.LENGTH_SHORT).show()
                startMainActivity()
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}
