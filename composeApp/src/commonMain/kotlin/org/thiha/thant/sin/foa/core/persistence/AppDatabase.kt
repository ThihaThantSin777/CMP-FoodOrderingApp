package org.thiha.thant.sin.foa.core.persistence

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.persistence.daos.AuthDao
import org.thiha.thant.sin.foa.home.data.vos.DeliveryAddressVO
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentMethodVO
import org.thiha.thant.sin.foa.home.persistence.daos.DeliveryAddressDao
import org.thiha.thant.sin.foa.home.persistence.daos.FoodItemDao
import org.thiha.thant.sin.foa.home.persistence.daos.PaymentMethodDao

@Database(
    entities = [AuthVO::class, FoodItemVO::class, PaymentMethodVO::class, DeliveryAddressVO::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): AuthDao

    abstract fun paymentMethodDao(): PaymentMethodDao

    abstract fun deliAddressDao(): DeliveryAddressDao

    abstract fun foodItemDao(): FoodItemDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}