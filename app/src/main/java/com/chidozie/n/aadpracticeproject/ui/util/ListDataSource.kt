@file:Suppress("DEPRECATION")

package com.chidozie.n.aadpracticeproject.ui.util

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import java.util.concurrent.Executor

/**
 * This converts a list to a data source
 *
 * @param T model
 * @property items list
 */
class ListDataSource<T>(private val items: List<T>) : PositionalDataSource<T>() {

    val factory = DataSourceFactory(items)

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        callback.onResult(items, 0, items.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        val start = params.startPosition
        val end = params.startPosition + params.loadSize
        callback.onResult(items.subList(start, end))
    }

    internal fun toPageList(pageSize: Int = 50): PagedList<T> {
        return PagedList.Builder(this, pageSize)
            .setNotifyExecutor(UiThreadExecutor())
            .setFetchExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            .build()
    }

    inner class UiThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            handler.post(command)
        }
    }

    inner class DataSourceFactory(private val list: List<T>) : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> {
            return ListDataSource(list)
        }
    }
}
