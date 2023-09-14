package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var tvageinminutes: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvageinminutes=findViewById(R.id.your_age_in_minutes)


        val selectdate=findViewById<Button>(R.id.select_date)

        selectdate.setOnClickListener(){
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val mycalender=Calendar.getInstance()
        val year=mycalender.get(Calendar.YEAR)
        val month=mycalender.get(Calendar.MONTH)
        val day=mycalender.get(Calendar.DAY_OF_MONTH)

      val dpd=  DatePickerDialog(this, {_,Selectedyear,Selectedmonth,Selectedday ->

          val date="$Selectedday/${Selectedmonth+1}/$Selectedyear"
          val selectedDate=findViewById<TextView>(R.id.date)
          selectedDate.text = date

          val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

          val thedate=sdf.parse(date)

          thedate?.let {
              val selectedDateinMinutes=thedate.time/60000

              val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

              currentDate?.let {
                  val CurrentDateinMinutes=currentDate.time/60000

                  val differenceInMillis = currentDate.time - thedate.time

                  val differenceInMinutes = differenceInMillis / (60 * 1000)

                  tvageinminutes?.text=differenceInMinutes.toString()
              }

          }

      },
          year,
          month,
          day)

        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}