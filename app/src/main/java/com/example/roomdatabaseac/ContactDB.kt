package com.example.roomdatabaseac

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 1)
abstract  class ContactDB : RoomDatabase(){

    abstract fun contactsDao() : ContactsDao
}