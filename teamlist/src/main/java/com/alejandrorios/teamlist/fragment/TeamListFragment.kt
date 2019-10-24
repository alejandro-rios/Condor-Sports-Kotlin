package com.alejandrorios.teamlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.models.ViewTeamData
import com.alejandrorios.teamlist.R
import com.alejandrorios.teamlist.base.BaseTeamListFragment
import com.alejandrorios.teamlist.di.TeamListComponent
import kotlinx.android.synthetic.main.fragment_team_list.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class TeamListFragment : BaseTeamListFragment(), TeamListContract.View {

    @Inject
    lateinit var presenter: TeamListContract.Presenter

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
        rvTeams?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = TeamAdapter(presenter)
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

    }

    override fun hideProgressDialog() {

    }

    companion object {
        fun newInstance(): TeamListFragment {
            return TeamListFragment()
        }
    }
}
