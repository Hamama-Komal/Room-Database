package com.example.roomdatabaseac

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface ContactsDao {

    @Insert
    suspend fun insertContacts(contacts: Contacts)

    @Update
    suspend fun updateContacts(contacts: Contacts)

    @Delete
    suspend fun deleteContacts(contacts: Contacts)

    @Query("SELECT * FROM contactstable")
    fun getAllContacts() : LiveData<List<Contacts>>


}