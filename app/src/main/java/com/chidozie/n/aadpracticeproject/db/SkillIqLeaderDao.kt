package com.chidozie.n.aadpracticeproject.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader

@Dao
abstract class SkillIqLeaderDao : AbstractDao<SkillIqLeader>() {

    @Query("SELECT * FROM SkillIqLeader ORDER BY score DESC")
    public abstract override fun dataSource(): DataSource.Factory<Int, SkillIqLeader>

    @Query("DELETE FROM SkillIqLeader")
    public abstract override fun deleteAll()

}
