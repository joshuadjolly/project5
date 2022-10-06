package com.example.bitfit

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namespace.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val newFoodActivityRequestCode = 1
    private lateinit var itemViewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvFood)
        val adapter = FoodAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        itemViewModel.allItems.observe(this, Observer { food ->
            food?.let { adapter.setFood(it) }
        })

        findViewById<Button>(R.id.btnAdd).setOnClickListener{
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivityForResult(intent, newFoodActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newFoodActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val food = FoodItem(0,data.getStringExtra(AddFoodActivity.EXTRA_REPLY), data.getStringExtra(AddFoodActivity.EXTRA_CAL).toString().toInt())
                itemViewModel.insert(food)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }



}