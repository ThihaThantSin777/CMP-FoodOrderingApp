package org.thiha.thant.sin.foa.home.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import org.thiha.thant.sin.foa.home.data.vos.PaymentMethodVO

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentMethod(method: PaymentMethodVO)

    @Query("SELECT * FROM payment_method")
    fun getAllPaymentMethods(): List<PaymentMethodVO>

    @Update
    suspend fun updatePaymentMethod(method: PaymentMethodVO)


    @Query("DELETE FROM payment_method")
    suspend fun clearAll()

    @Query("DELETE FROM payment_method WHERE id = :id")
    suspend fun deletePaymentMethodByID(id: Long)
}
