package com.telenor.esimtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.telephony.euicc.EuiccManager
import android.telephony.euicc.EuiccManager.EMBEDDED_SUBSCRIPTION_RESULT_ERROR
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

class EsimSystemLpaStrategy(private val activity: FragmentActivity) {

    companion object {
        private var currentLpa: String? = null
        private const val TAG = "EsimSystemLpaStrategy"

        fun getCurrentLpa(): String? {
            val lpaLocal = currentLpa
            currentLpa = null
            return lpaLocal
        }
    }

    private val esimInstallLauncher = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                Log.v(TAG, "OK")
            }
            EMBEDDED_SUBSCRIPTION_RESULT_ERROR -> {
                Log.v(TAG, "EMBEDDED_SUBSCRIPTION_RESULT_ERROR")
            }
            else -> {
                Log.v(TAG, "ELSE ERROR ${result.resultCode}")
            }
        }
    }

    fun activate(lpa: String) {
        if (!(activity.getSystemService(Context.EUICC_SERVICE) as EuiccManager).isEnabled) {
            throw IllegalStateException("eSim not support")
        }
        currentLpa = lpa
        try {
            val intent = Intent(EuiccManager.ACTION_START_EUICC_ACTIVATION)
            intent.putExtra(EuiccManager.EXTRA_USE_QR_SCANNER, false)
            esimInstallLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(activity, "SystemLpaLaunch ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}