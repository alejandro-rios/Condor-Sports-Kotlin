package com.alejandrorios.condorsports.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.alejandrorios.teamdetails.deeplink.TeamDetailsDeepLinkModule
import com.alejandrorios.teamdetails.deeplink.TeamDetailsDeepLinkModuleLoader
import com.alejandrorios.teamlist.deeplink.TeamListDeepLinkModule
import com.alejandrorios.teamlist.deeplink.TeamListDeepLinkModuleLoader

/**
 * Created by Alejandro Rios on 2019-10-25
 */
@DeepLinkHandler(
    TeamListDeepLinkModule::class,
    TeamDetailsDeepLinkModule::class
)
class DeepLinkHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLinkDelegate = DeepLinkDelegate(
            TeamListDeepLinkModuleLoader(),
            TeamDetailsDeepLinkModuleLoader()
        )

        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
