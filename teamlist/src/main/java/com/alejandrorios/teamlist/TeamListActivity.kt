package com.alejandrorios.teamlist

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.alejandrorios.core.constants.TEAM_LIST_DEEP_LINK

import kotlinx.android.synthetic.main.activity_team_list.*

@DeepLink(TEAM_LIST_DEEP_LINK)
class TeamListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)
        setSupportActionBar(toolbar)


    }
}
