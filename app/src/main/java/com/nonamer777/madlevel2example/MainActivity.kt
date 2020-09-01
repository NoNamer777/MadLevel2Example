package com.nonamer777.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nonamer777.madlevel2example.databinding.ActivityMainBinding
import com.nonamer777.madlevel2example.model.Reminder
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

        createItemTouchHelper().attachToRecyclerView(rvReminders)
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

    /**
     * Creates a touch helper to recognize when a user swipes an item from the recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is preforming these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        /* Callback which is used to create the ItemTouchHelper. Only enables left swipe.
         * Use ItemTouchHelper.SimpleCallBack(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
         * to also enable right swiping. */
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            // Enables or disables the ability to move items up or down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}
