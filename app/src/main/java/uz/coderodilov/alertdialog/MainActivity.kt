package uz.coderodilov.alertdialog

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import uz.coderodilov.alertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val colorsTitle = arrayOf("White", "Yellow", "Red", "Blue", "Green", "Gray")
    private val checkedItems = booleanArrayOf(false, false, false, false, false, false)
    private val colors =
        arrayOf(Color.WHITE, Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.GRAY)
    private var index = 0
    private var lastSubmitted = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAlertDialog.setOnClickListener { showAlertDialog() }
        binding.btnAlertDialogSingleChoice.setOnClickListener { showSingleChoiceAlertDialog() }
        binding.btnAlertDialogMultiChoice.setOnClickListener { showMultiChoiceAlertDialog() }
    }

    //Regular AlertDialog
    private fun showAlertDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Exit")
            .setIcon(R.drawable.baseline_exit_to_app_24)
            .setMessage("Do you wanna exit?")
            .setPositiveButton("Yes") { _, _ ->
                finish()
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setCancelable(false)
        dialog.create()
        dialog.show()
    }


    //Single choice AlertDialog
    private fun showSingleChoiceAlertDialog() {
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("Select color")
            .setIcon(R.drawable.baseline_color_lens_24)
            .setSingleChoiceItems(colorsTitle, lastSubmitted) { _, which ->
                index = which
            }
            .setPositiveButton("Select") { _, _ ->
                lastSubmitted = index
                binding.parentLayout.setBackgroundColor(colors[index])
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setCancelable(false)
        dialog.create()
        dialog.show()
    }

    //Multi choice AlertDialog
    private fun showMultiChoiceAlertDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Choose color")
            .setIcon(R.drawable.baseline_color_lens_24)
            .setMultiChoiceItems(colorsTitle, checkedItems) { _, which, isChecked ->
                if (isChecked) {
                    checkedItems[which] = true
                }
            }
            .setPositiveButton("Submit") { _, _ ->
                val builder = StringBuilder()
                for (i in checkedItems.indices) {
                    if (checkedItems[i]) {
                        builder.append(colorsTitle[i] + "\n")
                    }
                }
                binding.tvResult.text = builder.toString()
            }
            .setCancelable(false)
        dialog.create()
        dialog.show()
    }

}