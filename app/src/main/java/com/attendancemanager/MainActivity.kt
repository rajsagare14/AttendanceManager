package com.attendancemanager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var lecturesAttended : EditText
    lateinit var lecturesTotal : EditText
    lateinit var calculateBtn : Button
    lateinit var calculatedResult : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculateBtn = findViewById(R.id.calculate)
        lecturesAttended = findViewById(R.id.lecturesAttended)
        lecturesTotal = findViewById(R.id.lecturesTotal)
        calculatedResult = findViewById(R.id.result)
        var p : Double
        var t : Double
        var resStr = ""
        calculateBtn.setOnClickListener{
            try{
                p = lecturesAttended.text.toString().toInt().toDouble()
                t = lecturesTotal.text.toString().toInt().toDouble()
                var res = (p/t).toDouble()*100
                var a :Double
                resStr = ""
//                calculatedResult.text = p.toString() + "\n" + t.toString() + "\n" +res.toString()
//                calculatedResult.text = t.toString()
//                calculatedResult.text = res.toString()
                if(res == 75.00){
                    resStr = "Attendance Exactly $res%!\nDont Miss Lectures"
                }
                else if (res > 75){
                    if(res>100){
                        resStr = "Incorrect Data Entered!!"
                        return@setOnClickListener
                    }
                    resStr =
                        "$resStr Congratulations!!!\nAttendance is $res%\nYou can afford to Bunk"
                    val ot = t
                    while (res > 75){
                        t = t + 1
                        res = (p/t).toDouble()*100
//                    calculatedResult.text = res.toString()
//                    resStr = resStr + "\n" + p.toString() + " " + t.toString() + " " +res.toString() + "\n"
                    }
                    a = t-ot-1
                    resStr = resStr + " " + a + " Lectures"
                }
                else{
                    resStr = resStr.plus(" Alert!!!\nAttendance below 75%\n Attendance is $res%\nYou cant afford to Bunk any Lectures\nYou must attend the next")
                    val op = p
                    while(res<=75){
                        p = p+1
                        t = t+1
                        res = (p/t).toDouble()*100
                    }
                    a = p-op
//                resStr = resStr + "\n" + p.toString() + " " + t.toString() + " " +res.toString() + "\n"
                    resStr = resStr + " " + a + " Lectures to save yourself from getting debarred"
                }
            } catch (e : Exception){
                Toast.makeText(applicationContext,e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            calculatedResult.text = resStr
        }
    }
}