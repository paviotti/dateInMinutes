package com.paviotti.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.paviotti.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //declarar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //declarar
        //setContentView(R.layout.activity_main) //remover
        setContentView(binding.root) //declarar

        binding.btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get((Calendar.DAY_OF_MONTH))
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                binding.tvSelectDate.text = selectedDate //era .setText(selectDate)
                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
                val theDate = sdf.parse(selectedDate) //converte para data
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //pega o formato
                val currentDateInMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                binding.tvSelectDateMinutes.text = differenceInMinutes.toString()
            }, year, month, day
        )
        dpd.datePicker.maxDate = Date().time-86400000 //era .setMaxDate(Date().time-8640000)
        dpd.show()
    }
}