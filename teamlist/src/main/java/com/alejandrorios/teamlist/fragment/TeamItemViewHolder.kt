package com.alejandrorios.teamlist.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alejandrorios.teamlist.R
import com.alejandrorios.teamlist.domain.models.TeamData

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class TeamItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imgTeamBadge = itemView.findViewById<ImageView>(R.id.imgTeamBadge)
    private val tvTeamName = itemView.findViewById<TextView>(R.id.txtTeamName)
    private val tvTeamStadium = itemView.findViewById<TextView>(R.id.txtTeamStadium)

    fun bind(team: TeamData, teamClickListener: TeamClickListener) {
        tvTeamName?.text = team.strTeam
        tvTeamStadium?.text = team.strStadium

        team.strTeamBadge?.let { url ->
            imgTeamBadge?.load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_soccer_ball)
                error(R.drawable.ic_no_img)
            }
        }

        itemView.setOnClickListener {
            teamClickListener.onTeamClick(team)
        }
    }
}
