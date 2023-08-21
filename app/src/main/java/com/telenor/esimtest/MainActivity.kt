package com.telenor.esimtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testLpa = "1\$prod.smdp-plus.rsp.goog\$052X-UFXS-CQIY-PNGL"
        EsimSystemLpaStrategy(this).activate(testLpa)
    }
}