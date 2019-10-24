package com.alejandrorios.teamlist.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alejandrorios.teamlist.R

/**
 * A placeholder fragment containing a simple view.
 */
class TeamListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }
}
