package com.rm.mobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rm.mobile.data.local.dao.CharacterDao
import com.rm.mobile.data.local.dao.RemoteKeyDao
import com.rm.mobile.data.local.entity.CharacterEntity
import com.rm.mobile.data.local.entity.RemoteKeyEntity

@Database(
    entities = [
        CharacterEntity::class,
        RemoteKeyEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class Database : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeyDao
}
