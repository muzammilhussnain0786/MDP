package edu.miu.quizapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import edu.miu.quizapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var result: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun reset(view: View?) {
        result = 0
        binding.questionOneRadioGroup?.clearCheck()
        binding.cbQ2A.isChecked = false
        binding.cbQ2B.isChecked = false
        binding.cbQ2C.isChecked = false
    }

    private fun showResultDialog(title: String, message: String){
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("OK")
                { dialog, _ ->
                    reset(null)
                    result = 0
                    dialog.dismiss()
                }
            }
            builder.setMessage(message)
                .setTitle(title)
            builder.create()
        }
        alertDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitSolution(view: View) {
        if (binding.questionOneRadioGroup?.checkedRadioButtonId == R.id.radio_q1_b){
            result += 50
        }

        if (binding.cbQ2A.isChecked && binding.cbQ2B.isChecked && binding.cbQ2C.isChecked){
            result += 50
        }

        val current = LocalDateTime.now()

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        val date = current.format(dateFormatter)
        val time = current.format(timeFormatter)
        val message = if(result > 0)
            "Congratulations! You submitted on current $date and $time, Your achieved $result%"
        else "Please, try again."
        val title = "QuizApp Result"
        showResultDialog(title, message)
    }

}