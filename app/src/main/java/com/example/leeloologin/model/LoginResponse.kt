package com.example.leeloologin.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_response")
data class LoginResponse(
    val ok: Boolean,
    @PrimaryKey val user_id: Long,
    val name: String,
    val name_f: String,
    val name_l: String,
    val email: String,
    val login: String,
//    val subscriptions: Subscriptions,
//    val categories: HashMap<String, String>,
//    val groups: List<Any?>,
//    val resources: List<String>
)
