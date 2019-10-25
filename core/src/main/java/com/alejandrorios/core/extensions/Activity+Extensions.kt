package com.alejandrorios.core.extensions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejandrorios.core.constants.DeepLink

/**
 * Created by Alejandro Rios on 2019-10-23
 */
inline val Any?.isNotNull: Boolean get() = this != null

val Any?.isNull: Boolean get() = this == null

fun AppCompatActivity.startDeepLinkIntent(deepLink: DeepLink, arguments: Bundle? = null) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(deepLink)
    arguments?.let {
        intent.putExtras(it)
    }

    startActivity(intent)
}
