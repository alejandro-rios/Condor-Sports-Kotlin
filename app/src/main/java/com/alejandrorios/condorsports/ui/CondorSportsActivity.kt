package com.alejandrorios.condorsports.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejandrorios.condorsports.R
import com.alejandrorios.core.constants.TEAM_LOGIN_DEEP_LINK
import com.alejandrorios.core.extensions.startDeepLinkIntent

class CondorSportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        startDeepLinkIntent(TEAM_LOGIN_DEEP_LINK)
        finish()
    }
}
