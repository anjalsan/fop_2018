package com.fpl.fop.fop.mvrx.data

import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface TasksDataSource {
    fun changeMode(id: String): Disposable
}