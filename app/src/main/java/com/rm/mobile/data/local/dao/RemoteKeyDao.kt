package com.rm.mobile.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rm.mobile.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeysById(id: Int): RemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeyEntity>)

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllRemoteKeys()

    @Query("Select created_at From remote_keys Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}
