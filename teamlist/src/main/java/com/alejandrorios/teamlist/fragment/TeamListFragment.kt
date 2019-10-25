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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.models.ViewTeamData
import com.alejandrorios.teamlist.R
import com.alejandrorios.teamlist.base.BaseTeamListFragment
import com.alejandrorios.teamlist.di.TeamListComponent
import com.alejandrorios.teamlist.utils.*
import kotlinx.android.synthetic.main.fragment_team_list.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class TeamListFragment : BaseTeamListFragment(), TeamListContract.View {

    @Inject
    lateinit var presenter: TeamListContract.Presenter

    private var decoration = SpacesItemDecoration(SPACE_DECORATOR)
    private var shortAnimTime: Long? = ZERO


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
            layoutManager = LinearLayoutManager(context)
            layoutAnimation = animation
            setHasFixedSize(true)
            adapter = TeamAdapter(presenter)
            addItemDecoration(decoration)
        }

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressDialog() {
        rvTeams.visibility = View.GONE
        rvTeams.animate().setDuration(shortAnimTime!!).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeams.visibility = View.GONE
                }
            })
        pbTeams.visibility = View.VISIBLE
        pbTeams.animate().setDuration(shortAnimTime!!).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeams.visibility = View.VISIBLE
                }
            })
    }

    override fun hideProgressDialog() {
        rvTeams.visibility = View.VISIBLE
        rvTeams.animate().setDuration(shortAnimTime!!).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeams.visibility = View.VISIBLE
                }
            })
        pbTeams.visibility = View.GONE
        pbTeams.animate().setDuration(shortAnimTime!!).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeams.visibility = View.GONE
                }
            })
    }

    companion object {
        fun newInstance(): TeamListFragment {
            return TeamListFragment()
        }
    }
}
