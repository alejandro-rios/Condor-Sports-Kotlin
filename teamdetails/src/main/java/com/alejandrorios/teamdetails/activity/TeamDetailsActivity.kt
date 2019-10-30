package com.alejandrorios.teamdetails.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.airbnb.deeplinkdispatch.DeepLink
import com.alejandrorios.core.constants.ARGUMENT_TEAM_DETAILS
import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.constants.TEAM_DETAILS_DEEP_LINK
import com.alejandrorios.teamdetails.domain.models.TeamEventData
import com.alejandrorios.core.viewmodels.ViewTeamData
import com.alejandrorios.teamdetails.R
import com.alejandrorios.teamdetails.base.BaseTeamDetailsActivity
import com.alejandrorios.teamdetails.di.TeamDetailsComponent
import com.alejandrorios.teamdetails.utils.ANIMATION_TIME
import com.alejandrorios.teamdetails.utils.ONE_ALPHA
import com.alejandrorios.teamdetails.utils.ZERO_ALPHA
import kotlinx.android.synthetic.main.activity_team_details.*
import javax.inject.Inject

/**
 * Created by Alejandro Rios on 2019-10-27
 */
@DeepLink(TEAM_DETAILS_DEEP_LINK)
class TeamDetailsActivity : BaseTeamDetailsActivity(), TeamDetailsContract.View {

    @Inject
    lateinit var presenter: TeamDetailsContract.Presenter

    private val shortAnimTime: Long = ANIMATION_TIME
    private var viewTeam: ViewTeamData? = null

    override fun injectActivityBuilder(builder: TeamDetailsComponent) {
        builder.inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
        viewTeam = intent?.extras?.getParcelable(ARGUMENT_TEAM_DETAILS)
            ?: throw RuntimeException()
        presenter.getTeamEvents(viewTeam!!)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        setSupportActionBar(toolbar)

        try {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showTeamEvents(events: List<TeamEventData>) {
        val resId: Int = R.anim.recycler_animation_falldown
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, resId)

        collapsing_toolbar.title = viewTeam?.strTeam
        tvTeamFormedYear.text = viewTeam?.intFormedYear
        tvTeamDesc.text = viewTeam?.strDescriptionEN

        imgTeam.load(viewTeam?.strTeamBadge) {
            crossfade(true)
            placeholder(R.drawable.ic_soccer_ball)
            error(R.drawable.ic_no_img)
        }

        imgTeamJersey.load(viewTeam?.strTeamJersey) {
            crossfade(true)
            placeholder(R.drawable.ic_soccer_ball)
            error(R.drawable.ic_no_img)
        }

        rvTeamEvents.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            layoutAnimation = animation
            adapter = EventsAdapter(events as ArrayList<TeamEventData>)
        }

        bottomNavTeam.itemIconTintList = null
        bottomNavTeam.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.navWebSite -> {
                viewTeam?.strWebsite?.let { website ->
                    openUrl(website)
                } ?: showError(resources.getString(R.string.team_details_no_web))
            }
            R.id.navFacebook -> {
                viewTeam?.strFacebook?.let { facebook ->
                    openUrl(facebook)
                } ?: showError(resources.getString(R.string.team_details_no_facebook))
            }
            R.id.navTwitter -> {
                viewTeam?.strTwitter?.let { twitter ->
                    openUrl(twitter)
                } ?: showError(resources.getString(R.string.team_details_no_twitter))
            }
            R.id.navInstagram -> {
                viewTeam?.strInstagram?.let { instagram ->
                    openUrl(instagram)
                } ?: showError(resources.getString(R.string.team_details_no_instagram))
            }
            R.id.navYouTube -> {
                viewTeam?.strYoutube?.let { youTube ->
                    openUrl(youTube)
                } ?: showError(resources.getString(R.string.team_details_no_youtube))
            }
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        startActivity(intent)
    }

    override fun showProgressDialog() {
        pbTeamEvents.visibility = View.VISIBLE
        pbTeamEvents.animate().setDuration(shortAnimTime).alpha((ONE_ALPHA))
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeamEvents.visibility = View.VISIBLE
                }
            })
    }

    override fun hideProgressDialog() {
        pbTeamEvents.visibility = View.GONE
        pbTeamEvents.animate().setDuration(shortAnimTime).alpha((ZERO_ALPHA))
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeamEvents.visibility = View.GONE
                }
            })
    }

    override fun showError(error: StringResourceId) {
        showError(getString(error))
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
