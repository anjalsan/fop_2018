package com.fpl.fop.fop.mvrx.core

import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.CallSuper
import android.support.annotation.IdRes
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRx
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.activityViewModel
import com.fpl.fop.fop.R
import com.fpl.fop.fop.data.model.response.TallyItem
import com.fpl.fop.fop.mvrx.PersonsState
import com.fpl.fop.fop.mvrx.PersonsViewModel

abstract class BaseFragment : BaseMvRxFragment() {

    protected val viewModel by activityViewModel(PersonsViewModel::class)

    protected lateinit var coordinatorLayout: CoordinatorLayout
    protected lateinit var recyclerView: EpoxyRecyclerView
    protected val epoxyController by lazy {epoxyController() }
    // Used to keep track of task changes to determine if we should show a snackbar.
    private var oldTasks: List<TallyItem>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_base, container, false).apply {
                coordinatorLayout = findViewById(R.id.coordinator_layout)
                recyclerView = findViewById(R.id.recycler_view)
                recyclerView.setController(epoxyController)
            }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.selectSubscribe(PersonsState::persons) { persons ->
            if (oldTasks == null) {
                oldTasks = persons
                return@selectSubscribe
            }

            oldTasks = persons
        }
    }

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

    abstract fun epoxyController(): ToDoEpoxyController

    protected fun navigate(@IdRes id: Int, args: Parcelable? = null) {
        findNavController().navigate(id, Bundle().apply { putParcelable(MvRx.KEY_ARG, args) })
    }
}