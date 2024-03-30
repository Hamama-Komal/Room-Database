package com.example.roomdatabaseac

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactsTable")
data class Contacts(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val phone: String
)
