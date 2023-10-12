package com.example.pertemuan5

import DatePicker
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.example.pertemuan5.databinding.ActivityInputBinding
import java.util.Calendar

class InputActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityInputBinding

    private lateinit var kaloriIn : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowCalender.setOnClickListener {
            val datePicker = DatePicker()
            datePicker.show(supportFragmentManager, "datePicker")
        }

        binding.btnShowTime.setOnClickListener {
            val timePicker = TimePicker()
            timePicker.show(supportFragmentManager,"timePicker")
        }

        binding.btnShowTime2.setOnClickListener {
            val timePicker = TimePicker()
            timePicker.show(supportFragmentManager,"timePicker")
        }

        val selectedDateTextView = findViewById<TextView>(R.id.btn_show_calender)
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                selectedDateTextView.text = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        selectedDateTextView.setOnClickListener {
            datePickerDialog.show()
        }

        val selectedTimeTextView = findViewById<TextView>(R.id.btn_show_time)
        val time = Calendar.getInstance()

        val timePickerDialog = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                selectedTimeTextView.text = selectedTime
            },
            time.get(Calendar.HOUR_OF_DAY),
            time.get(Calendar.MINUTE),
            true // Gunakan `true` jika Anda ingin format 24 jam, atau `false` jika Anda ingin format 12 jam
        )

        selectedTimeTextView.setOnClickListener {
            timePickerDialog.show()
        }

        val selectedTimeTextView2 = findViewById<TextView>(R.id.btn_show_time2)
        val time2 = Calendar.getInstance()

        val timePickerDialog2 = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                selectedTimeTextView2.text = selectedTime
            },
            time2.get(Calendar.HOUR_OF_DAY),
            time2.get(Calendar.MINUTE),
            true // Gunakan `true` jika Anda ingin format 24 jam, atau `false` jika Anda ingin format 12 jam
        )

        selectedTimeTextView2.setOnClickListener {
            timePickerDialog2.show()
        }

        kaloriIn = resources.getStringArray(R.array.kaloriIn)
        with(binding) {
            val adapterKaloriIn = ArrayAdapter(this@InputActivity,
                android.R.layout.simple_spinner_item, kaloriIn)
            adapterKaloriIn.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            jenisKaloriIn.adapter = adapterKaloriIn
        }

        val editText = findViewById<EditText>(R.id.kaloriMin)
        val backButton = findViewById<Button>(R.id.backButton)

        backButton.setOnClickListener {
            // Mengambil teks dari EditText
            val textToAdd = editText.text.toString()

            // Membuat intent untuk kembali ke class sebelumnya (misalnya, MainActivity)
            val intent = Intent(this, MenuActivity::class.java)

            // Menambahkan teks sebagai data tambahan ke intent
            intent.putExtra("textToAdd", textToAdd)

            // Memulai aktivitas sebelumnya dengan intent
            startActivity(intent)
        }

    }




    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = String.format("%02d:%02d",hourOfDay,minute)
        Toast.makeText(this@InputActivity,selectedTime,Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val selectedDate = "$dayOfMonth,${month + 1},$year"
        Toast.makeText(this@InputActivity, selectedDate, Toast.LENGTH_SHORT).show()
        Log.d("SELECTED DATE", selectedDate)
    }
}
