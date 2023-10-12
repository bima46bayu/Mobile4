package com.example.pertemuan5

import DatePicker
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.pertemuan5.databinding.ActivityInputBinding
import com.example.pertemuan5.databinding.ActivityStartedBinding
import com.example.pertemuan5.databinding.ActivityWelcomeBinding
import java.util.*

class StartedActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private lateinit var binding : ActivityStartedBinding

    private lateinit var tujuan_diet : Array<String>

    private lateinit var satuanBB : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tujuan_diet = resources.getStringArray(R.array.tujuan_diet)

        satuanBB = resources.getStringArray(R.array.satuanBB)

        val editText = findViewById<EditText>(R.id.BBsekarang)
        val editText2 = findViewById<EditText>(R.id.BBtarget)
        val btnStarted = findViewById<Button>(R.id.btn_started)

        btnStarted.setOnClickListener {
            // Mengambil teks dari EditText
            val textToAdd = editText.text.toString()
            val textToAdd2 = editText2.text.toString()

            // Membuat intent untuk kembali ke class sebelumnya (misalnya, MainActivity)
            val intent = Intent(this, MenuActivity::class.java)

            // Menambahkan teks sebagai data tambahan ke intent
            intent.putExtra("textToAdd", textToAdd)
            intent.putExtra("textToAdd2", textToAdd2)

            // Memulai aktivitas sebelumnya dengan intent
            startActivity(intent)
        }


        with(binding) {
            val adapterTujuanDiet = ArrayAdapter(this@StartedActivity,
                android.R.layout.simple_spinner_item, tujuan_diet)
            adapterTujuanDiet.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapterTujuanDiet
        }

        with(binding) {
            val adapterBB = ArrayAdapter(this@StartedActivity,
                android.R.layout.simple_spinner_item, satuanBB)
            adapterBB.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerBB.adapter = adapterBB
        }

        with(binding) {
            val adapterBBnanti = ArrayAdapter(this@StartedActivity,
                android.R.layout.simple_spinner_item, satuanBB)
            adapterBBnanti.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerBBnanti.adapter = adapterBBnanti
        }

        binding.btnShowCalender.setOnClickListener {
            val datePicker = DatePicker()
            datePicker.show(supportFragmentManager, "datePicker")
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


    }



    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val selectedDate = "$dayOfMonth,${month + 1},$year"
        Toast.makeText(this@StartedActivity, selectedDate, Toast.LENGTH_SHORT).show()
        Log.d("SELECTED DATE", selectedDate)
    }
}
