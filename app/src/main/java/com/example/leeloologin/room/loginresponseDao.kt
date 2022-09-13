package com.example.leeloologin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.leeloologin.model.LoginResponse

/**
 * Created by Satish V on 18-July-2022.
 * Company : HighOnSwift Pvt ltd
 * Email   : iamsatishema@gmail.com
 */
@Dao
interface loginresponseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert( login_response : LoginResponse)

    @Query("SELECT * FROM login_response")
    fun getAll(): List<LoginResponse>

}