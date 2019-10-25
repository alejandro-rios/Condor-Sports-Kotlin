package com.alejandrorios.teamlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.alejandrorios.core.constants.TEAM_LIST_DEEP_LINK
import com.alejandrorios.teamlist.fragment.TeamListFragment
import kotlinx.android.synthetic.main.activity_team_list.*

@DeepLink(TEAM_LIST_DEEP_LINK)
class TeamListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)

        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.clMain, TeamListFragment.newInstance(), TeamListFragment::class.java.name)
            .commitAllowingStateLoss()
    }
}
