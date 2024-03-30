package com.example.roomdatabaseac

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contacts::class], version = 2)
@TypeConverters(Convertors::class)
abstract  class ContactDB : RoomDatabase(){

    abstract fun contactsDao() : ContactsDao

    companion object{

        // migration
        private val migration_1_2  = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE ContactsTable ADD COLUMN isActive INTEGER NOT NULL DEFAULT(0)")
            }

        }

        // creating Singleton instance
        private var INSTANCE : ContactDB? = null

        fun getDatabase(context: Context) : ContactDB{

            if(INSTANCE == null){
                synchronized(this){

                    INSTANCE = Room.databaseBuilder(context.applicationContext, ContactDB::class.java, "ContactsDB")
                        .addMigrations(migration_1_2).build()
                }

            }
            return INSTANCE!!
        }
    }
}