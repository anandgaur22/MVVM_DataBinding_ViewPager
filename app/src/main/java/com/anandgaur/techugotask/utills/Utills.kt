package com.anandgaur.techugotask.utills


import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.anandgaur.techugotask.R
import com.anandgaur.techugotask.constants.Constants


object Utills {


    /**initialize progressbar and @return progressbar instance */

    fun initializeProgressBar(context: Context, style: Int): ProgressDialog {

        // return setProgressDialog(context, "Loading..")
        val progressDialog = ProgressDialog(context)
        progressDialog.setIndeterminateDrawable(context.getDrawable(R.drawable.bg_progress))
        progressDialog.setCancelable(false)
        progressDialog.setTitle(context.getString(R.string.msg_please_wait))
        return progressDialog

    }


    /** show internet error */

    fun showEnableInternetMessage(context: Context) = Toast.makeText(
        context,
        context.getString(R.string.msg_please_connect_to_internet),
        Toast.LENGTH_LONG
    ).show()

    /**
     * check device is connected to internet or not
     */

    fun verifyAvailableNetwork(activity: FragmentActivity): Boolean {

        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val isInternet = networkInfo != null && networkInfo.isConnected
        if (!isInternet) {
            showInternetAlertDailog(activity)
        }
        return isInternet
    }


    fun getTabTitile(position: Int, context: Context): String {

        var tabTitle = "3"
        if (position == 0) tabTitle = context.getString(R.string.offers)
        else if (position == 1) tabTitle = context.getString(R.string.details)

        return tabTitle

    }


    fun showInternetAlertDailog(context: Activity) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogTheme))

        builder.setTitle(context.getString(R.string.no_internet))
        builder.setMessage(context.getString(R.string.msg_please_connect))
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }

        builder.setCancelable(false)

        builder.show()
    }


    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }


    fun callPhoneNumber(requireActivity: Activity) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(
                        requireActivity,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        101
                    )
                    return
                }
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + Constants.SOS_NUMBER)
                requireActivity.startActivity(callIntent)
            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + Constants.SOS_NUMBER)
                requireActivity.startActivity(callIntent)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


}