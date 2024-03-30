package com.example.roomdatabaseac

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Contacts::class], version = 1)
@TypeConverters(Convertors::class)
abstract  class ContactDB : RoomDatabase(){

    abstract fun contactsDao() : ContactsDao

    companion object{

        private var INSTANCE : ContactDB? = null


        fun getDatabase(context: Context) : ContactDB{

            if(INSTANCE == null){
                synchronized(this){

                    INSTANCE = Room.databaseBuilder(context.applicationContext, ContactDB::class.java, "ContactsDB").build()
                }

            }
            return INSTANCE!!
        }
    }
}