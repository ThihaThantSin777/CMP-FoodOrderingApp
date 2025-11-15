package org.thiha.thant.sin.foa.home.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO

@Dao
interface FoodItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(item: FoodItemVO)

    @Query("SELECT * FROM food_item")
    fun getAllFoodItems(): List<FoodItemVO>

    @Query("DELETE FROM food_item")
    suspend fun clearAll()

    @Update
    suspend fun updateFoodItem(item: FoodItemVO)

    @Query("DELETE FROM food_item WHERE id = :foodId")
    suspend fun deleteFoodItem(foodId: Long)

}
