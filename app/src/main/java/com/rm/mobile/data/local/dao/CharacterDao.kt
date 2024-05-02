package com.rm.mobile.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rm.mobile.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getCharacters(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun deleteCharacters()
}
