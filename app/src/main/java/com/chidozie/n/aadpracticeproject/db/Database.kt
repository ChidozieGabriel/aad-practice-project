package com.chidozie.n.aadpracticeproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chidozie.n.aadpracticeproject.model.LearningLeader
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader

@Database(
    entities = [
        LearningLeader::class,
        SkillIqLeader::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun learningLeaderDao(): LearningLeaderDao
    abstract fun skillIqLeaderDao(): SkillIqLeaderDao

}
