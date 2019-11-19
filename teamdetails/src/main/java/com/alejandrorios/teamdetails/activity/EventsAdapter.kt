package com.alejandrorios.teamdetails.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.teamdetails.domain.models.TeamEventData
import com.alejandrorios.teamdetails.R

/**
 * Created by Alejandro Rios on 2019-10-27
 */
class EventsAdapter(
    private val teamEvents: ArrayList<TeamEventData> = ArrayList()
) :
    RecyclerView.Adapter<EventsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsItemViewHolder {
        return EventsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return teamEvents.size
    }

    override fun onBindViewHolder(holder: EventsItemViewHolder, position: Int) {
        holder.bind(teamEvents[position])
    }
}
