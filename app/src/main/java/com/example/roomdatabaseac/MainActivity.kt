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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var database: ContactDB

    @OptIn(DelicateCoroutinesApi::class)
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

        // Create the Singleton Instance
        database = ContactDB.getDatabase(this)



        // insert data
        GlobalScope.launch {
            database.contactsDao().insertContacts(Contacts(0, "Mark","432362563453654", Date()))
        }


        // view data
        binding.text.setOnClickListener{

            database.contactsDao().getAllContacts().observe(this, Observer {
                Log.d("ContactsDB", it.toString())
            })

        }




    }
}