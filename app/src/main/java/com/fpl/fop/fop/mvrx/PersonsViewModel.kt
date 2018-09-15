package com.fpl.fop.fop.mvrx

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.fpl.fop.fop.data.model.response.TallyItem
import com.fpl.fop.fop.mvrx.core.MvRxViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

data class PersonsState(
        val persons: List<TallyItem> = emptyList(),
        val isLoading: Boolean = true
) : MvRxState

class PersonsViewModel(initialState: PersonsState) : MvRxViewModel<PersonsState>(initialState) {

    init {
        refreshTasks()
    }

    fun refreshTasks() {
        val newPersons: MutableList<TallyItem> = mutableListOf<TallyItem>()
        var tallyItem:TallyItem = TallyItem()
        tallyItem.name = "Blah Blah Blah"
        tallyItem.count = 4
        tallyItem.id = 324
        newPersons.add(tallyItem)

        val subscribe = Observable.timer(1000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { setState { copy(persons = newPersons, isLoading = false) } }
    }

    companion object : MvRxViewModelFactory<PersonsState> {
        @JvmStatic override fun create(activity: FragmentActivity, state: PersonsState): BaseMvRxViewModel<PersonsState> {
            return PersonsViewModel(state)
        }
    }
}

