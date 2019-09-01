package com.fpl.fop.fop.mvrx

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.fpl.fop.fop.MvpStarterApplication
import com.fpl.fop.fop.data.DataManager
import com.fpl.fop.fop.data.model.response.PlayerTallyItem
import com.fpl.fop.fop.data.model.response.TallyItem
import com.fpl.fop.fop.mvrx.core.MvRxViewModel
import com.fpl.fop.fop.mvrx.data.TasksDataSource
import com.fpl.fop.fop.util.rx.scheduler.SchedulerUtils
import java.lang.RuntimeException

data class PersonsState(
        val persons: List<TallyItem> = emptyList(),
        val isLoading: Boolean = true
) : MvRxState

class PersonsViewModel(initialState: PersonsState) : MvRxViewModel<PersonsState>(initialState) {

    init {
        refreshTasks(true)
    }

    fun refreshTasks(isAll: Boolean) {
        val newPersons: MutableList<TallyItem> = mutableListOf<TallyItem>()
        var tallyItem:TallyItem = TallyItem()
        setState { copy(persons = newPersons, isLoading = true) }


        val dataManager: DataManager = MvpStarterApplication.dataManager
        val subscribe1 = dataManager
                .getPokemonList(10)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        { res ->
                            val gw : Int =res.get(0).game_week.game_week.fpl_gw
                            val url: String;
                            if (isAll) {
                                url = "/hfh/api/veg_tally/" + gw.toString() +"?all=True"
                            } else {
                                url = "/hfh/api/veg_tally/" + gw.toString()
                            }
                            dataManager
                                    .getFullVegList(url)
                                    .compose(SchedulerUtils.ioToMain<List<PlayerTallyItem>>())
                                    .subscribe(
                                            { tallysitems ->
                                                for (i in 0..tallysitems.size  - 1){
                                                    for (j in 0..tallysitems.get(i).players.size  - 1) {
                                                        addPlayer(newPersons, i, tallysitems.get(i).players.get(j));
                                                    }
                                                }
                                                setState { copy(persons = newPersons, isLoading = false) }
                                            },
                                            { throwable ->
                                                setState { copy(persons = newPersons, isLoading = false) }
                                            })
                        },
                        { throwable ->
                            setState { copy(persons = newPersons, isLoading = false) }
                        })
    }

    companion object : MvRxViewModelFactory<PersonsState> {
        @JvmStatic override fun create(activity: FragmentActivity, state: PersonsState): BaseMvRxViewModel<PersonsState> {
            return PersonsViewModel(state)
        }
    }

    fun addPlayer(newPersons: MutableList<TallyItem>, position: Int, player: TallyItem) {
        var isAvailable = false
        for (i in 0 until newPersons.size){
            if (newPersons[i].name.equals(player.name)) {
                isAvailable = true
                addCount(newPersons[i], position, player.count)
                break
            }
        }
        if (!isAvailable){
            newPersons.add(player)
            addCount(newPersons.get(newPersons.size - 1), position, player.count)
        }
    }

    fun addCount(player: TallyItem, position: Int, count: Float) {
        when (position) {
            0 -> player.whiteWalker = count
            1 -> player.brotherhood = count
            2 -> player.dothraki = count
            3 -> player.valyrians = count
            4 -> player.targaryens = count
            5 -> player.nightsWatch = count
            6 -> player.lannisters = count
            7 -> player.starks = count
            8 -> player.facelessMen = count
            9 -> player.kingslayers = count
        }
    }
}

