package com.example.bitfit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.namespace.R

class AddFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val button = findViewById<Button>(R.id.btnAddNewFood)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(findViewById<EditText>(R.id.etAddFood).text)
             && TextUtils.isEmpty(findViewById<EditText>(R.id.etAddCal).text) ) {

                setResult(Activity.RESULT_CANCELED, replyIntent)

            } else {
                val food = findViewById<EditText>(R.id.etAddFood).text.toString()
                val calories = findViewById<EditText>(R.id.etAddCal).text.toString()


                replyIntent.putExtra(EXTRA_REPLY, food)
                setResult(Activity.RESULT_OK, replyIntent)

                replyIntent.putExtra(EXTRA_CAL, calories)
                setResult(Activity.RESULT_OK, replyIntent)

                /*    val extras = Bundle()
                    extras.putString("EXTRA_FOOD", food)
                    extras.putString("EXTRA_CALORIES", calories)
                    replyIntent.putExtra(EXTRA_REPLY, extras)

                    setResult(Activity.RESULT_OK, replyIntent)

                 */
            }
            finish()
        }
    }


    companion object {
        const val EXTRA_REPLY = "FOOD_EXTRA"
        const val EXTRA_CAL = "CAL_EXTRA"
    }

}