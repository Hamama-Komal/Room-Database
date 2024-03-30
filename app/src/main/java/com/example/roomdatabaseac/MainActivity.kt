package com.example.roomdatabaseac

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.roomdatabaseac.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var database: ContactDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Create the instance of database just for testing
        database = Room.databaseBuilder(applicationContext, ContactDB::class.java, "ContactsDB").build()

        // insert data
        GlobalScope.launch {
            database.contactsDao().insertContacts(Contacts(0, "Mark","432362563453654"))
        }

        // update data
        GlobalScope.launch {
            database.contactsDao().updateContacts(Contacts(1, "Jack","432362563453654"))
        }

        // delete data
        GlobalScope.launch {
            database.contactsDao().deleteContacts(Contacts(1, "Jack","432362563453654"))
        }

        // view data
        database.contactsDao().getAllContacts().observe(this, Observer {
            Log.d("ContactsDB", it.toString())
        })



    }
}