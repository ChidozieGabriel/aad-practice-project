package com.chidozie.n.aadpracticeproject.util

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource

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

    inner class DataSourceFactory(private val list: List<T>) : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> {
            return ListDataSource(list)
        }
    }
}
