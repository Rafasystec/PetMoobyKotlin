package client.petmooby.com.br.petmooby.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.*

/**
 * Created by Rafael Rocha on 19/07/2019.
 */
class DateTimePickerDialog  {

    companion object {
        fun showTimePicker(context:Context){
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)
            val tpd = TimePickerDialog(context,TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
                Toast.makeText(context, "$h : $m : ", Toast.LENGTH_LONG).show()
            }),hour,minute,false)
            tpd.show()
        }

        fun showDatePicker(context:Context, component: View, datePar: Date) {
            val c       = Calendar.getInstance()
            if(datePar != null){
                c.time = datePar
            }
            val year    = c.get(Calendar.YEAR)
            val month   = c.get(Calendar.MONTH)
            val day     = c.get(Calendar.DAY_OF_MONTH)
            val date    = DatePickerDialog(context,DatePickerDialog.OnDateSetListener(function = { view, year, month,day ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY,0)
                calendar.set(Calendar.MINUTE,0)
                calendar.set(Calendar.SECOND,0)
                calendar.set(year,month,day)
                datePar.time = calendar.time.time
                when(component){
                    is TextView ->{
                        component.text = DateTimeUtil.formatDateTime(datePar,"dd/MM/yyyy")
                    }
                }
            }),year,month,day)
            date.show()

        }
    }

}