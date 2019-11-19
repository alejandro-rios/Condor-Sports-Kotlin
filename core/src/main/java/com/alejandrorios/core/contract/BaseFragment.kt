package com.alejandrorios.core.contract

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Alejandro Rios on 2019-10-24
 */
abstract class BaseFragment : Fragment() {

    abstract fun prepareFragmentBuilder()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        prepareFragmentBuilder()
    }
}
