package com.example.aroundegypt.utilitis

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object Utils {
    fun openUrlWithChromeTab(url: String?, context: Context) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        if (url != null) {
            if (url.isNotEmpty() && url.isNotBlank()) {
                try {
                    val customTabsIntent: CustomTabsIntent = builder.build()
                    customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    customTabsIntent.launchUrl(context, Uri.parse(url))
                } catch (e: java.lang.Exception) {
                    val customTabsIntent: CustomTabsIntent = builder.build()
                    customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    customTabsIntent.launchUrl(context, Uri.parse("http://$url"))
                }
            }
        }
    }
}