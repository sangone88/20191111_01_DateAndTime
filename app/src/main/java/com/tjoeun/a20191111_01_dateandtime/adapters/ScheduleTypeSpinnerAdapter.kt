package com.tjoeun.a20191111_01_dateandtime.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tjoeun.a20191111_01_dateandtime.R
import com.tjoeun.a20191111_01_dateandtime.datas.ScheduleType

class ScheduleTypeSpinnerAdapter(context: Context, res:Int, list:ArrayList<ScheduleType>)
    : ArrayAdapter<ScheduleType>(context, res, list){

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list: ArrayList<ScheduleType>)
            : this(context, R.layout.schedule_type_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.schedule_type_list_item, null)
        }

        var row = tempRow!!

        var summaryTxt = row.findViewById<TextView>(R.id.summaryTxt)
        var typeTxt = row.findViewById<TextView>(R.id.typeTxt)

        var data = mList.get(position)

        typeTxt.text = data.title
        summaryTxt.setBackgroundColor(Color.parseColor("#000000"))

        return row

    }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {


        }

    }

}