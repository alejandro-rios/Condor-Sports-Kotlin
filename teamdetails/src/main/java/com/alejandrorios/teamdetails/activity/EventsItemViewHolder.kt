package com.alejandrorios.teamdetails.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.core.models.TeamEventData
import com.alejandrorios.teamdetails.R
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alejandro Rios on 2019-10-27
 */
class EventsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var date = Date()
    private val sf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private val tvHomeTeam = itemView.findViewById<TextView>(R.id.tvHomeTeam)
    private val tvAwayTeam = itemView.findViewById<TextView>(R.id.tvAwayTeam)
    private val eventDate = itemView.findViewById<TextView>(R.id.tvEventDate)

    fun bind(teamEventData: TeamEventData) {
        tvHomeTeam?.text = teamEventData.strHomeTeam
        tvAwayTeam?.text = teamEventData.strAwayTeam

        try {
            date = sf.parse(teamEventData.dateEvent)
            sf.applyPattern("dd MMMM yyyy")

            eventDate.text = sf.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
