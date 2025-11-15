package org.thiha.thant.sin.foa.core.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilderAndroid(appContext: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = appContext.getDatabasePath("food_order.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}