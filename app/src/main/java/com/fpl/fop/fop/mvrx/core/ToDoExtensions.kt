package com.fpl.fop.fop.mvrx.core

import android.support.annotation.StringRes
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewGroup

fun <T> List<T>.upsert(value: T, finder: (T) -> Boolean) = indexOfFirst(finder).let { index ->
    if (index >= 0) copy(index, value) else this + value
}

fun <T> List<T>.copy(i: Int, value: T): List<T> = toMutableList().apply { set(i, value) }

inline fun <T> List<T>.delete(filter: (T) -> Boolean): List<T> = toMutableList().apply { removeAt(indexOfFirst(filter)) }

fun CoordinatorLayout.showLongSnackbar(@StringRes stringRes: Int) {
    Snackbar.make(this, stringRes, Snackbar.LENGTH_LONG).show()
}

fun ViewGroup.asSequence(): Sequence<View> = (0..childCount).asSequence().map { getChildAt(it) }