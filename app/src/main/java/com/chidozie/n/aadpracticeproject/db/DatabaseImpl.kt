package com.chidozie.n.aadpracticeproject.db

import android.app.Application
import androidx.room.Room

object DatabaseImpl {

    private const val DB = "aad_db"

    lateinit var instance: Database
        private set

    fun inject(context: Application) {
        instance = Room.databaseBuilder(context, Database::class.java, DB)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun mock(context: Application) {
        instance = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries()
            .build()
    }

}
