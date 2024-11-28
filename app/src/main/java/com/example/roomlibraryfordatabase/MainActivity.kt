package com.example.roomlibraryfordatabase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val editname = findViewById<EditText>(R.id.editText1)
        val editnumber = findViewById<EditText>(R.id.editText2)
        val save = findViewById<Button>(R.id.button)


        //save when user click to save button
        save.setOnClickListener {
            // get input name from user
            val username: String = editname.text.toString()

            //get input number from user
            val userno: String = editnumber.text.toString()

            //validate Input (optional but recommended)

            if (username.isBlank() || userno.isBlank()) {
                Toast.makeText(this, "Please enter both name and number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //get an instance of databaseHelper
                val dbhelper: DatabaseHelper = DatabaseHelper.getInstance(this@MainActivity)

            //perform database operation in a background thread
            //Thread Safety: Database operations should not be performed on the main thread as they can block the UI.
           // Thread {}: The block runs a background operation.
            //runOnUiThread {} is a method in Android used to execute code on the main (UI) thread. This is particularly useful when you need to update the UI from a background thread, as UI changes are not allowed directly from background threads.
            //You wrap the code you want to run on the UI thread inside runOnUiThread {}. This ensures that UI updates are handled safely.


            Thread {
                dbhelper.detailDao().addTx(Details(username= username, usernumber = userno))
                 val arrUserDetails =  dbhelper.detailDao().getAllDetails()
                for( e in arrUserDetails){
                    Log.d("User Details ","user Id ${e.userId } User Name is : ${e.username} User phone number is :  ${e.usernumber}" )

            }

                runOnUiThread {
                    Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show()
                }

            }.start()


        }
        }
    }
