package org.thiha.thant.sin.foa.home.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import org.thiha.thant.sin.foa.home.data.vos.DeliveryAddressVO

@Dao
interface DeliveryAddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliveryAddress(address: DeliveryAddressVO)

    @Query("SELECT * FROM delivery_address")
    fun getAllDeliveryAddresses(): List<DeliveryAddressVO>

    @Update
    suspend fun updateDeliveryAddress(address: DeliveryAddressVO)

    @Query("DELETE FROM delivery_address")
    suspend fun clearAll()

    @Query("DELETE FROM payment_method WHERE id = :id")
    suspend fun deleteDeliveryAddressByID(id: Long)
}
