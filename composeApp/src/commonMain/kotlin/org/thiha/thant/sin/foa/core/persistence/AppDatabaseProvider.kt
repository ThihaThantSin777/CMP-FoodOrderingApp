package org.thiha.thant.sin.foa.core.persistence


import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

object AppDatabaseProvider {

    lateinit var appDatabase: AppDatabase

    fun initializeDatabase(databaseBuilder: RoomDatabase.Builder<AppDatabase>) {
        appDatabase = databaseBuilder.setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(dropAllTables = true).build()
    }
}