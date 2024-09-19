package edu.tcu.quynhpthai.age_in_minute
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.view.WindowInsetsController


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectDateButton: Button = findViewById(R.id.select_date_button)
        val selectedDateText: TextView = findViewById(R.id.date_text)
        val minutesText: TextView = findViewById(R.id.minutes_update)
        window.statusBarColor = getColor(R.color.statusbar)

        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
        window.setDecorFitsSystemWindows(false) // Let content extend behind the status and navigation bars

        selectDateButton.setOnClickListener {
            // Open DatePickerDialog to select date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedMonth+1}/$selectedDay/$selectedYear"
                selectedDateText.text = selectedDate

                // Calculate the difference in minutes
                val selectedDateCalendar = Calendar.getInstance()
                selectedDateCalendar.set(selectedYear, selectedMonth, selectedDay)

                val currentDate = (Calendar.getInstance().timeInMillis)/60000
                val differenceInMinutes = (currentDate - (selectedDateCalendar.timeInMillis/60000))

                minutesText.text = differenceInMinutes.toString()

            }, year, month, day)
            dpd.datePicker.maxDate=System.currentTimeMillis()
            dpd.show()
        }
    }
}
