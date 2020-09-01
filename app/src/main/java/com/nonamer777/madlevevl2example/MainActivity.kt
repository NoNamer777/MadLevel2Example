package com.nonamer777.madlevevl2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nonamer777.madlevevl2example.databinding.ActivityMainBinding
import com.nonamer777.madlevevl2example.model.Reminder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()
    }

    /** Initialize the activity view */
    private fun initViews() {
        // Create an onClickListener associated with the button.
        binding.btnAddReminder.setOnClickListener {
            val reminder = binding.etReminder.text.toString()

            addReminder(reminder)
        }

        // Initialize the recycler view with a linear layout manager, adapter.
        binding.rvReminders.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL,
            false
        )
        binding.rvReminders.adapter = reminderAdapter
        binding.rvReminders.addItemDecoration(
            DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
        )
    }

    /** Adds a Reminder to the list of Reminders. */
    private fun addReminder(reminderText: String) {
        if (reminderText.isNotBlank()) {
            reminders.add(Reminder(reminderText))
            reminderAdapter.notifyDataSetChanged()

            binding.etReminder.text?.clear()
        } else {
            Snackbar.make(etReminder, "you must fill in the input field!", Snackbar.LENGTH_LONG)
                .show()
        }
    }
}
