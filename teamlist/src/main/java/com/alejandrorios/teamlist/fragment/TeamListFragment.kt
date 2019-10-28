package com.alejandrorios.teamlist.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alejandrorios.core.constants.ARGUMENT_TEAM_DETAILS
import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.constants.TEAM_DETAILS_DEEP_LINK
import com.alejandrorios.core.extensions.startDeepLinkIntent
import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.models.ViewTeamData
import com.alejandrorios.teamlist.R
import com.alejandrorios.teamlist.base.BaseTeamListFragment
import com.alejandrorios.teamlist.di.TeamListComponent
import com.alejandrorios.teamlist.utils.*
import com.leinardi.android.speeddial.SpeedDialActionItem
import kotlinx.android.synthetic.main.fragment_team_list.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class TeamListFragment : BaseTeamListFragment(), TeamListContract.View {

    @Inject
    lateinit var presenter: TeamListContract.Presenter

    private var decoration = SpacesItemDecoration(SPACE_DECORATOR)
    private var shortAnimTime: Long = ANIMATION_TIME

    override fun injectFragmentBuilder(builder: TeamListComponent) {
        builder.inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return
        val llm = StaggeredGridLayoutManager(
            SPAN_COUNT,
            StaggeredGridLayoutManager.VERTICAL
        )
        val resId: Int = R.anim.recycler_animation_falldown
        val animation: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, resId)
        llm.gapStrategy =
            StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        rvTeams?.apply {
            layoutManager = llm
            layoutAnimation = animation
            adapter = TeamAdapter(presenter)
            addItemDecoration(decoration)
        }

        setUpFab()

        presenter.onViewCreated()
    }

    override fun showTeams(teams: List<TeamData>) {
        val adapter = rvTeams?.adapter as? TeamAdapter ?: return
        adapter.add(teams)
    }

    override fun showError(error: StringResourceId) {
        showError(getString(error))
    }

    override fun showError(error: String) {
        val context = context ?: return
        Toast.makeText(
            context, error, Toast.LENGTH_LONG
        ).show()
    }

    override fun openTeamDetails(viewTeam: ViewTeamData) {
        (activity as? AppCompatActivity)?.startDeepLinkIntent(
            TEAM_DETAILS_DEEP_LINK,
            Bundle().apply {
                putParcelable(ARGUMENT_TEAM_DETAILS, viewTeam)
            })
    }

    override fun showProgressDialog() {
        rvTeams.visibility = View.GONE
        rvTeams.animate().setDuration(shortAnimTime).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeams.visibility = View.GONE
                }
            })
        pbTeams.visibility = View.VISIBLE
        pbTeams.animate().setDuration(shortAnimTime).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeams.visibility = View.VISIBLE
                }
            })
    }

    override fun hideProgressDialog() {
        rvTeams.visibility = View.VISIBLE
        rvTeams.animate().setDuration(shortAnimTime).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeams.visibility = View.VISIBLE
                }
            })
        pbTeams.visibility = View.GONE
        pbTeams.animate().setDuration(shortAnimTime).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeams.visibility = View.GONE
                }
            })
    }

    private fun setUpFab() {
        val context = context ?: return

        fabChangeLeague.apply {
            addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_uefa_champions_league,
                    R.drawable.ic_russian_league
                )
                    .setLabel(getString(R.string.russian_premier_league_title))
                    .setLabelBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPrimary,
                            context.theme
                        )
                    )
                    .create()
            )

            addActionItem(
                SpeedDialActionItem.Builder(R.id.fab_english_premier_league, R.drawable.ic_english)
                    .setLabel(getString(R.string.english_league_title))
                    .setLabelBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPrimary,
                            context.theme
                        )
                    )
                    .create()
            )

            addActionItem(
                SpeedDialActionItem.Builder(R.id.fab_spanish_league, R.drawable.ic_la_liga)
                    .setLabel(getString(R.string.spanish_league_title))
                    .setLabelBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPrimary,
                            context.theme
                        )
                    )
                    .create()
            )
        }

        fabChangeLeague.setOnActionSelectedListener(this)
    }

    override fun onActionSelected(actionItem: SpeedDialActionItem?): Boolean {
        when (actionItem?.id) {
            R.id.fab_spanish_league -> presenter.getTeamsFromLeague(getString(R.string.spanish_league_code))
            R.id.fab_english_premier_league -> presenter.getTeamsFromLeague(getString(R.string.english_league_code))
            R.id.fab_uefa_champions_league -> presenter.getTeamsFromLeague(getString(R.string.russian_premier_league_code))
        }
        return false
    }

    companion object {
        fun newInstance(): TeamListFragment {
            return TeamListFragment()
        }
    }
}
