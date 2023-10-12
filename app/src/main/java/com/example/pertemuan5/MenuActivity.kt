package com.example.pertemuan5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.pertemuan5.databinding.ActivityMenuBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class MenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding

    lateinit var kaloriSisa : PieChart

    lateinit var BBSisa : PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnInputData.setOnClickListener {
                val intentToSecondActivity =
                    Intent (this@MenuActivity, InputActivity::class.java)

                startActivity(intentToSecondActivity)
            }
        }

        with(binding){
            kaloriSisa = chartKaloriSisa
        }
        val list : ArrayList<PieEntry> = arrayListOf()

        list.add(PieEntry(100f,"target"))
        list.add(PieEntry(101f,"sisa"))

        val pieDataSet = PieDataSet(list,"")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize = 0f

        val pieData = PieData(pieDataSet)

        kaloriSisa.data = pieData
        kaloriSisa.description.text = ""
        kaloriSisa.centerText = "Kalori Sisa"
        kaloriSisa.animateY(2000)


        // Menginisialisasi PieChart kedua dengan ID yang berbeda (misalnya, chartKaloriKedua)
        var kaloriKedua = findViewById<PieChart>(R.id.chart_berat_badan)
        with(binding) {
            kaloriKedua = chartBeratBadan
        }
        val listKedua: ArrayList<PieEntry> = arrayListOf()
        listKedua.add(PieEntry(50f, "target"))
        listKedua.add(PieEntry(150f, "sisa"))
        val pieDataSetKedua = PieDataSet(listKedua, "")
// Anda dapat mengatur warna yang berbeda atau menggunakan ColorTemplate yang sama
        pieDataSetKedua.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        pieDataSetKedua.valueTextSize = 0f

        val pieDataKedua = PieData(pieDataSetKedua)

        kaloriKedua.data = pieDataKedua
        kaloriKedua.description.text = ""
        kaloriKedua.centerText = "Selisih BB"
        kaloriKedua.animateY(2000)



        val textView = findViewById<TextView>(R.id.latihan)

        val receivedText = intent.getStringExtra("textToAdd")

        if (!receivedText.isNullOrBlank()) {
            // Tambahkan teks ke EditText atau TextView
            textView.text = receivedText
        }

    }




}