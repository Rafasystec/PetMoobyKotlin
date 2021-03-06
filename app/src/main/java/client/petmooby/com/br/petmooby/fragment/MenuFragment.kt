package client.petmooby.com.br.petmooby.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import client.petmooby.com.br.petmooby.LoginActivity
import client.petmooby.com.br.petmooby.R
import client.petmooby.com.br.petmooby.actvity.VeterinaryPartnersListActivity
import client.petmooby.com.br.petmooby.extensions.callEmailHost
import client.petmooby.com.br.petmooby.extensions.setupToolbar
import client.petmooby.com.br.petmooby.util.NotificationWorkerUtil
import client.petmooby.com.br.petmooby.util.Preference
import client.petmooby.com.br.petmooby.util.VariablesUtil
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.login.LoginManager
import hotchemi.android.rate.AppRate
import kotlinx.android.synthetic.main.menu_fragment_content.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


/**
 * A simple [Fragment] subclass.
 * Email account for heroku: pwd: 88petymoo22&&
 * email: petmooby.heroku@gmail.com
 */
const val URL = "https://petmooby.herokuapp.com"
const val TERMS = "/pages/terms"
class MenuFragment : Fragment() {

    var tokenTrace = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?,
                                                 currentAccessToken: AccessToken?) {
            if (currentAccessToken == null) {
                //TODO clear preferences data , call login activity and close the main screen.
                try {
                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity!!.finish()
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnMenuLogout.setOnClickListener{logout()}
        btnMenuVeterinaryPartners.setOnClickListener { startActivity(Intent(activity,VeterinaryPartnersListActivity::class.java)) }
        btnMenuAppName.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
            startActivity(browserIntent)
        }
        btnMenuRateApp.setOnClickListener { AppRate.with(activity).showRateDialog(activity) }
        btnMenuTermsOfUse.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(URL+ TERMS))
            startActivity(browserIntent)
        }
        btnMenuContactUs.setOnClickListener {
            callEmailHost("contact.petmooby@gmail.com",getString(R.string.emailContatSubject),getString(R.string.emailBody),getString(R.string.emailContactTitle))
        }
        tokenTrace.startTracking()
        setupToolbar(R.id.toolbar,getString(R.string.Menu))
    }

    private fun logout(){
        activity!!.alert( R.string.logoutMessage,R.string.Logout) {
            yesButton { logoutYesButton() }
            noButton {}
        }.show()
    }

    private fun logoutYesButton(){
        LoginManager.getInstance().logOut()
        Preference.clear(activity!!)
        NotificationWorkerUtil().cancel(activity!!)
        startActivity(Intent(activity!!,LoginActivity::class.java))
        activity!!.finish()
        VariablesUtil.clear()
    }
}// Required empty public constructor
