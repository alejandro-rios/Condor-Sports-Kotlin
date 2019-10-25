package com.alejandrorios.condorsports.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.alejandrorios.teamlist.deeplink.TeamListDeepLinkModule
import com.alejandrorios.teamlist.deeplink.TeamListDeepLinkModuleLoader

/**
 * Created by Alejandro Rios on 2019-10-25
 */
@DeepLinkHandler(
    TeamListDeepLinkModule::class
)
class DeepLinkHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLinkDelegate = DeepLinkDelegate(
            TeamListDeepLinkModuleLoader()
        )

        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
