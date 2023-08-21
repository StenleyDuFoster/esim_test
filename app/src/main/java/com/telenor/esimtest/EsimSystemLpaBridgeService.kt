package com.telenor.esimtest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.service.euicc.ICarrierEuiccProvisioningService
import android.service.euicc.IGetActivationCodeCallback

class EsimSystemLpaBridgeService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private val binder = object : ICarrierEuiccProvisioningService.Stub () {
        override fun getActivationCode(callback: IGetActivationCodeCallback?) {
            // you can write your own logic to fetch activation code from somewhere.
            callback?.onSuccess(EsimSystemLpaStrategy.getCurrentLpa() ?: "")
        }

        override fun getActivationCodeForEid(eid: String?, callback: IGetActivationCodeCallback?) {
            callback?.onSuccess(EsimSystemLpaStrategy.getCurrentLpa() ?: "")
        }
    }
}