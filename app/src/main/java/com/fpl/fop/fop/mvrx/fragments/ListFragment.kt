package com.fpl.fop.fop.mvrx.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PopupMenu
import android.util.Log
import android.view.*
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.fragmentViewModel
import com.fpl.fop.fop.mvrx.core.BaseFragment
import com.fpl.fop.fop.mvrx.core.simpleController
import com.fpl.fop.fop.mvrx.views.horizontalLoader
import com.fpl.fop.fop.mvrx.views.taskItemView
import com.fpl.fop.fop.mvrx.views.vegtallyHeader


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */

class ListFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        horizontalLoader {
            id("loader")
            loading(state.isLoading)
        }

        state.persons
                .forEach { task ->
                    taskItemView {
                        id(task.name)
                        title(task)
                        checked(true)
                        onClickListener { _ -> Log.e("Clicked", "Testing") }
                    }
                }
    }

    @Suppress("unused")
    private fun Unit.andTrue() = true
}
