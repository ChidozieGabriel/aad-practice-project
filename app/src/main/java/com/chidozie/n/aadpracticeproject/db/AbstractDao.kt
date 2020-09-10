package com.chidozie.n.aadpracticeproject.db

import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy

abstract class AbstractDao<T : Any> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<T>)

    protected abstract fun dataSource(): DataSource.Factory<Int, T>

    protected abstract fun deleteAll()

}
