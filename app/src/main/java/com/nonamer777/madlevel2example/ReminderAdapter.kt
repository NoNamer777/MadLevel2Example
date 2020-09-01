package com.nonamer777.madlevel2example.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nonamer777.madlevel2example.databinding.ItemReminderBinding
import com.nonamer777.madlevel2example.model.Reminder

class ReminderAdapter(private val reminders: List<Reminder>):
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = ItemReminderBinding.bind(itemView)

        fun dataBind(reminder: Reminder) {
            binding.tvReminder.text = reminder.reminderText
        }
    }

    /**
     * Creates and returns a ViewHolder object,
     * inflating a standard layout called `simple_list_item_1`.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
    )

    /** Returns the size of the list. */
    override fun getItemCount(): Int = reminders.size

    /** Called by RecyclerView to display the data at the specified position. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.dataBind(reminders[position])
}
