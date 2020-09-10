package com.chidozie.n.aadpracticeproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class LearningLeader(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("hours")
    val hours: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("badgeUrl")
    val badgeUrl: String
)
