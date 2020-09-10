package com.chidozie.n.aadpracticeproject.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.chidozie.n.aadpracticeproject.model.LearningLeader

@Dao
abstract class LearningLeaderDao : AbstractDao<LearningLeader>() {

    @Query("SELECT * FROM LearningLeader ORDER BY hours DESC")
    public abstract override fun dataSource(): DataSource.Factory<Int, LearningLeader>

    @Query("DELETE FROM LearningLeader")
    public abstract override fun deleteAll()

}
