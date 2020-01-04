package com.cis.listdialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("기본 다이얼로그")
            builder.setMessage("다이얼로그의 본문")
            builder.setIcon(R.mipmap.ic_launcher)

            var listener = object:DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which) {
                        DialogInterface.BUTTON_POSITIVE ->
                            tv.text = "기본 다이얼로그 : positive"
                        DialogInterface.BUTTON_NEGATIVE ->
                            tv.text = "기본 다이얼로그 : NEGATIVE"
                        DialogInterface.BUTTON_NEUTRAL ->
                            tv.text = "기본 다이얼로그 : NEUTRAL"
                    }
                }
            }

            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", listener)
            builder.setNeutralButton("중립", listener)

            builder.setCancelable(false)
            builder.show()
        }

        btn2.setOnClickListener { view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("커스텀 다이얼로그")
            builder.setIcon(R.mipmap.ic_launcher)

            var v1 = layoutInflater.inflate(R.layout.dialog, null)
            builder.setView(v1)

            var listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    var alert = dialog as AlertDialog
                    var et1: EditText? = alert.findViewById(R.id.et1)
                    var et2: EditText? = alert.findViewById(R.id.et2)

                    tv.text = "et1 : ${et1?.text}\n"
                    tv.append("et2 : ${et2?.text}")
                }
            }

            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)

            builder.show()
        }

        btn3.setOnClickListener { view ->
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    tv.text = "${year} 년 ${month+1} 월 ${dayOfMonth} 일"
                }
            }
            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }

        btn4.setOnClickListener { view ->
            var calendar = Calendar.getInstance()
            var hour = calendar.get(Calendar.HOUR)
            var minute = calendar.get(Calendar.MINUTE)

            var listener = object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    if (minute < 10){
                        tv.text = "${hourOfDay} 시 0${minute} 분"
                    } else {
                        tv.text = "${hourOfDay} 시 ${minute} 분"
                    }
                }
            }

            var picker = TimePickerDialog(this, listener, hour, minute, true)
            picker.show()
        }
    }
}
