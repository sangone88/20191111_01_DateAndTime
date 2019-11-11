package com.tjoeun.a20191111_01_dateandtime
//EditScheduleActivity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import com.tjoeun.a20191111_01_dateandtime.adapters.ScheduleTypeSpinnerAdapter
import com.tjoeun.a20191111_01_dateandtime.datas.ScheduleType
import kotlinx.android.synthetic.main.activity_edit_schedule.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditScheduleActivity : BaseActivity() {

    var scheduleTypeList = ArrayList<ScheduleType>()
    var scheduleTypeSpinnerAdapter : ScheduleTypeSpinnerAdapter? = null
    
//    시작 일자 / 시간을 모두 저장하고 있는 캘린더 변수.
//    변수가 만들어지는 현재 일시가 저장되게 됨.
    var startDateTimeCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_schedule)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        saveBtn.setOnClickListener {
            var alert = AlertDialog.Builder(this)

            alert.setTitle("저장확인")
            alert.setMessage("정말 일정을 저장하겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "일정을 저장했습니다.", Toast.LENGTH_SHORT).show()
            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }

        startDateBtn.setOnClickListener { 
            var datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

//                시작일시 변구에 선택값 반영
                        startDateTimeCalendar.set(year, month, dayOfMonth)

//                버튼에 2019-09-08 양식으로 출력
                var sdf = SimpleDateFormat("yyyy-MM-dd (EEE)")

//                버튼에 sdf를 이용해서 선택된 날짜를 String으로 변환
                startDateBtn.text = sdf.format(startDateTimeCalendar.time)

               },
                startDateTimeCalendar.get(Calendar.YEAR),
                startDateTimeCalendar.get(Calendar.MONTH),
                startDateTimeCalendar.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show()

            startTimeBtn.setOnClickListener {

                var timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    startDateTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    startDateTimeCalendar.set(Calendar.MINUTE, minute)

                    var sdf = SimpleDateFormat("a h시 m분")
                    startTimeBtn.text = sdf.format(startDateTimeCalendar.time)

                },
                    startDateTimeCalendar.get(Calendar.HOUR_OF_DAY),
                    startDateTimeCalendar.get(Calendar.MINUTE),
                    false)

                timePickerDialog.show()

            }
            
        }
    }

    override fun setValues() {

        addScheduleTypes()

        scheduleTypeSpinnerAdapter = ScheduleTypeSpinnerAdapter(this, scheduleTypeList)
        scheduleTypeSpinner.adapter = scheduleTypeSpinnerAdapter
    }

    fun addScheduleTypes() {
        scheduleTypeList.add(ScheduleType("개인 일정", "#FF0000"))
        scheduleTypeList.add(ScheduleType("동아리 모임", "#00FF00"))
        scheduleTypeList.add(ScheduleType("업무 일정", "#0000FF"))
        scheduleTypeList.add(ScheduleType("공식 행사", "#000000"))
    }
}
