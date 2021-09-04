package com.dev.practical.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.dev.practical.R
import com.dev.practical.databinding.ActivityAddTaskBinding
import com.dev.practical.extra.Keys
import com.dev.practical.extra.Utils
import com.dev.practical.firebase.FirebaseReferneces
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddTaskActivity : BaseActivity() {

    // BINDING
    lateinit var binding : ActivityAddTaskBinding

    // INTEGER
    var year : Int = 0
    var month : Int = 0
    var day : Int = 0
    var hour : Int = 0
    var minute : Int = 0

    // STRING
    var selectedDate : String = ""
    var selectedDueDate : String = ""
    var selectedTime : String = ""

    // ARRAY LIST
    var taskList : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)

        // INIT DATA
        initData()
    }

    // INIT DATA
    private fun initData(){
        setToolbarText(context.resources.getString(R.string.text_add_task))

        onBackButtonClick()

        hideDrawerMenu()

        // INIT REMIND ME CLICK
        initRemindMeClick()

        // INIT DUE DATE CLICK
        initDueDateClick()

        // INIT ADD TASK CLICK
        initAddTaskClick()
    }

    // INIT ADD TASK BUTTON CLICK
    private fun initAddTaskClick(){
        binding.cvAddTask.setOnClickListener {
            checkValidation()
        }
    }

    // INIT REMIND ME DATE CLICK
    private fun initRemindMeClick(){
        binding.edRemindMe.setOnClickListener {
            showDatePickerDialog()
        }
    }

    // INIT DUE DATE CLICK
    private fun initDueDateClick(){
        binding.edDueDate.setOnClickListener {
            showDueDatePickerDialog()
        }
    }

    // CHECK VALIDATION
    private fun checkValidation(){
        if (binding.edTaskTitle.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_task_title))
        } else if (binding.edTaskDescription.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_task_description))
        } else if (selectedDate == "" && selectedTime == ""){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_remind_me))
        } else if (selectedDueDate == ""){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_due_date))
        } else {
            isTasksExists()
        }
    }

    // IS TASK TABLE EXISTS
    private fun isTasksExists(){
        FirebaseReferneces.getDatabaseReference(Keys.firebaseTasks).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    if (snapshot.value is ArrayList<*>) {
                        val objectMap: ArrayList<Map<String, Any>?> = snapshot.value as ArrayList<Map<String, Any>?>
                        var userId : String = ""
                        for (i in 0 until objectMap.size) {
                            if (objectMap[i] != null) {
                                userId = objectMap[i]!!.getValue(Keys.firebaseTaskId) as String
                                taskList.add(userId)
                            }
                        }
                        if (taskList.size > 0){
                            val id = userId.toInt() + 1
                            FirebaseReferneces.createTask( id.toString(),
                                binding.edTaskTitle.text.toString(),
                                binding.edTaskDescription.text.toString(),
                                binding.edRemindMe.text.toString(),
                                binding.edDueDate.text.toString(), sessionManager.userId.toString())
                        }
                    } else {
                        FirebaseReferneces.createTask("0",
                            binding.edTaskTitle.text.toString(),
                            binding.edTaskDescription.text.toString(),
                            binding.edRemindMe.text.toString(),
                            binding.edDueDate.text.toString(), sessionManager.userId.toString())

                    }
                    Utils.showAlertCustom(context, context.resources.getString(R.string.task_added))
                } catch (e : Exception){
                    e.printStackTrace()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(
                    "Database Error->",
                    error.details
                )
            }

        })

    }

    // SHOW DATE PICKER DIALOG
    private fun showDatePickerDialog(){
        // Get Current Date
        val c = Calendar.getInstance()
        year = c[Calendar.YEAR]
        month = c[Calendar.MONTH]
        day = c[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val startSelectedDateCal = Calendar.getInstance()
                startSelectedDateCal.set(year, month, dayOfMonth)
                selectedDate = SimpleDateFormat("yyyy-MM-dd").format(
                    Date(
                        startSelectedDateCal.timeInMillis
                    )
                )
                selectedDate  = SimpleDateFormat("dd/MM/yyyy").format(
                    Date(
                        startSelectedDateCal.timeInMillis
                    )
                )
                showTimePickerDialog()
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = c.timeInMillis
        datePickerDialog.show()
    }

    // SHOW TIME PICKER DIALOG
    private fun showTimePickerDialog(){
        val c = Calendar.getInstance()
        hour = c[Calendar.HOUR_OF_DAY]
        minute = c[Calendar.MINUTE]

        // Launch Time Picker Dialog
        val timePickerDialog = TimePickerDialog(context,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                if (view.isShown) {
                    val myCalender = Calendar.getInstance()
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    myCalender.set(Calendar.MINUTE, minute)

                    selectedTime = SimpleDateFormat("HH:mm:ss").format(
                        Date(
                            myCalender.timeInMillis
                        )
                    )

                    binding.edRemindMe.setText("$selectedDate $selectedTime")
                }
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }


    // SHOW DATE PICKER DIALOG
    private fun showDueDatePickerDialog(){
        // Get Current Date
        val c = Calendar.getInstance()
        year = c[Calendar.YEAR]
        month = c[Calendar.MONTH]
        day = c[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val startSelectedDateCal = Calendar.getInstance()
                startSelectedDateCal.set(year, month, dayOfMonth)
                selectedDueDate = SimpleDateFormat("dd/MM/yyyy").format(
                    Date(
                        startSelectedDateCal.timeInMillis
                    )
                )
                binding.edDueDate.setText(selectedDueDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = c.timeInMillis
        datePickerDialog.show()
    }
}