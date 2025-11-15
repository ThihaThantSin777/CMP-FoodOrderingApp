package org.thiha.thant.sin.foa.auth.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: AuthVO)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): AuthVO?

    @Query("DELETE FROM users")
    suspend fun clearAll()
}
