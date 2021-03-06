package client.petmooby.com.br.petmooby.extensions

import android.app.Activity
import android.app.ProgressDialog
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import client.petmooby.com.br.petmooby.R
import client.petmooby.com.br.petmooby.util.LogUtil
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton


/**
 * Created by Rafael Rocha on 25/07/18.
 */

inline fun Activity.defaultRecycleView(view: Activity, resId:Int): RecyclerView {
    var recycleView = view.findViewById<RecyclerView>(resId)
    recycleView?.layoutManager = LinearLayoutManager(this)
    recycleView?.itemAnimator = DefaultItemAnimator()
    recycleView?.setHasFixedSize(true)
    return recycleView
}
//@Deprecated("Please replace with setupToolbar(@IdRes id: Int, @IdRes idString: Int, upNavigation: Boolean = false) : ActionBar")
inline fun AppCompatActivity.setupToolbar(@IdRes id: Int,title:String?= null,upNavigation: Boolean = false, listener: View.OnClickListener? = null ) : ActionBar {
    val toolbar = findViewById<Toolbar>(id)

    LogUtil.logDebug("Set support Action Bar")
    setSupportActionBar(toolbar)
    if(listener != null) {
        LogUtil.logDebug("Has event to call")
        toolbar.setNavigationOnClickListener(listener)
    }
    if(title != null){
        supportActionBar?.title = title
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    supportActionBar?.setDisplayShowHomeEnabled(upNavigation)
    return supportActionBar!!
}

fun AppCompatActivity.setupToolbar(@IdRes id: Int,  idString: Int, upNavigation: Boolean = false) : ActionBar{
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if(title != null){
        supportActionBar?.title = resources.getString(idString)
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    return supportActionBar!!
}

fun AppCompatActivity.switchFragmentToMainContent(fragment: Fragment){
    supportFragmentManager.beginTransaction()
            ?.replace(R.id.mainFragment,fragment).commit()
}

fun AppCompatActivity.switchFragment(@IdRes id: Int, fragment: Fragment){
    supportFragmentManager.beginTransaction()
            ?.replace(id,fragment).commit()
}


fun AppCompatActivity.showLoadingDialog(message: String = getString(R.string.loading), title: String=getString(R.string.wait)): ProgressDialog {
    val dialog = indeterminateProgressDialog(message) {
        setProgressStyle(ProgressDialog.STYLE_SPINNER)
        setCancelable(false)
        show()
    }
    return dialog
}

fun Activity.showAlert(message:String){
    alert(message, getString(R.string.Advice)){
        okButton{ it.dismiss() }
    }.show()
}

fun Activity.showAlert(@StringRes title:Int, message:String){
    alert(message, getString(title)){
        okButton{ it.dismiss() }
    }.show()
}

fun Activity.showAlert(@StringRes idResource: Int){
    showAlert(getString(idResource))
}

fun Activity.showAlertError(message:String){
    alert(message, getString(R.string.Advice)){
        okButton{ it.dismiss() }
    }.show()
}

fun Activity.showAlertError(@StringRes idResource: Int){
    showAlertError(getString(idResource))
}

fun Activity.onFailedQueryReturn(dialog: ProgressDialog,message:String){
    dialog.dismiss()
    Log.d("FACE",message)
}

/**
 * The first item will be insert with the maximum width and the others in pair
 */
fun Activity.getDefaulLayoutManager(): GridLayoutManager{
    val layoutManager = GridLayoutManager(this,1)
//    layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
//        override fun getSpanSize(position: Int): Int {
//            return if (position == 0) 2 else 1
//        }
//
//    }
    return layoutManager

}

fun Activity.getDefaultLayoutManager(): GridLayoutManager
    = GridLayoutManager(this,1)

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view!!.windowToken, 0)

}