package com.alejandrorios.teamlist.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.core.models.TeamData
import com.alejandrorios.teamlist.R

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class TeamAdapter(
    private val teamClickListener: TeamClickListener,
    private val teams: ArrayList<TeamData> = ArrayList()
) :
    RecyclerView.Adapter<TeamItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamItemViewHolder {
        return TeamItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.team_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamItemViewHolder, position: Int) {
        holder.bind(teams[position], teamClickListener)
    }

    fun add(teams: List<TeamData>) {
        this.teams.clear()
        this.teams.addAll(teams)
        notifyDataSetChanged()
    }
}
