package com.example.adhipcalendar.ui.monthView

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adhipcalendar.R
import com.example.adhipcalendar.ui.monthView.adapter.CalendarAdapter
import com.example.adhipcalendar.ui.monthView.adapter.OnItemListener
import com.example.adhipcalendar.databinding.FragmentMonthBinding
import com.example.adhipcalendar.models.AddTaskRequestBody
import com.example.adhipcalendar.models.Event
import com.example.adhipcalendar.models.Task
import com.example.adhipcalendar.utils.constansts.inputMethodManager
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.UUID

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class MonthViewFragment : Fragment(R.layout.fragment_month), OnItemListener {

    private var selectedDate: LocalDate? = null
    private var message: String = ""
    private val events = mutableMapOf<LocalDate, List<Event>>()

    private val viewModel: MonthViewModel by viewModels()
    private var _binding: FragmentMonthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedDate = LocalDate.now()
        setMonthView()
        setButtons()
        setupObservers()
    }

    private fun setMonthView() {
        binding.monthYearTV.text = selectedDate?.let { monthYearFromDate(it) }
        val daysInMonth: ArrayList<String>? = selectedDate?.let { daysInMonthArray(it) }
        val calendarAdapter = daysInMonth?.let { CalendarAdapter(it, this) }
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 7)
        binding.calendarRecyclerView.layoutManager = layoutManager
        binding.calendarRecyclerView.adapter = calendarAdapter
    }

    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = java.util.ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun setButtons(){
        binding.backtv.setOnClickListener {
            selectedDate = selectedDate!!.minusMonths(1)
            setMonthView()
        }

        binding.fwdtv.setOnClickListener {
            selectedDate = selectedDate!!.plusMonths(1)
            setMonthView()
        }
    }

    override fun onItemClick(position: Int, dayText: String?) {
        if (dayText != "") {
            message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate!!)
            inputDialog.show()
        }
    }

    private val inputDialog by lazy {
        val editText = AppCompatEditText(requireContext())
        val layout = FrameLayout(requireContext()).apply {
            // Setting the padding on the EditText only pads the input area
            // not the entire EditText so we wrap it in a FrameLayout.
            val padding = com.example.adhipcalendar.utils.constansts.dpToPx(20, requireContext())
            setPadding(padding, padding, padding, padding)
            addView(editText, FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ))
        }
        AlertDialog.Builder(requireContext())
            .setTitle(message)
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                saveEvent(editText.text.toString())
                // Prepare EditText for reuse.
                editText.setText("")
            }
            .setNegativeButton("Close", null)
            .create()
            .apply {
                setOnShowListener {
                    // Show the keyboard
                    editText.requestFocus()
                    context.inputMethodManager
                        .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                }
                setOnDismissListener {
                    // Hide the keyboard
                    context.inputMethodManager
                        .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                }
            }
    }

    private fun saveEvent(text: String) {
        if (text.isBlank()) {
            Toast.makeText(requireContext(), "Text is empty", Toast.LENGTH_LONG).show()
        } else {
            selectedDate?.let {
                events[it] =
                    events[it].orEmpty().plus(Event(UUID.randomUUID().toString(), text, it))
                //updateAdapterForDate(it)
            }
            viewModel.addTask(
                AddTaskRequestBody(
                   userId = Math.random().toInt(),
                    task = Task(
                        title = message,
                        description = ""
                    )
                )
            )
        }
    }

    private fun setupObservers(){
        viewModel.addTaskLiveData().observe(viewLifecycleOwner){bool ->
            if(bool){
                Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
        }
    }
}
