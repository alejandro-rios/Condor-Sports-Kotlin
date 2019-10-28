package com.alejandrorios.condorsports.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.alejandrorios.login.deeplink.LoginDeepLinkModule
import com.alejandrorios.teamdetails.deeplink.TeamDetailsDeepLinkModule
import com.alejandrorios.teamdetails.deeplink.TeamDetailsDeepLinkModuleLoader
import com.alejandrorios.teamlist.deeplink.TeamListDeepLinkModule
import com.alejandrorios.teamlist.deeplink.TeamListDeepLinkModuleLoader

/**
 * Created by Alejandro Rios on 2019-10-25
 */
@DeepLinkHandler(
    LoginDeepLinkModule::class,
    TeamListDeepLinkModule::class,
    TeamDetailsDeepLinkModule::class
)
class DeepLinkHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLinkDelegate = DeepLinkDelegate(
            LoginDeepLinkModuleLoader(),
            TeamListDeepLinkModuleLoader(),
            TeamDetailsDeepLinkModuleLoader()
        )

        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
