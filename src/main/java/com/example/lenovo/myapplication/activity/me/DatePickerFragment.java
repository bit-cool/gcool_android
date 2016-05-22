package com.example.lenovo.myapplication.activity.me;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by lenovo on 2016/3/3.
 */
public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String birthday=year+"-"+(month+1)+"-"+day;
        Message message=Message.obtain();
        message.obj=birthday;
        message.what=Me_Edit.BIRTHDAY;
        Me_Edit.MEHandler.sendMessage(message);
    }
}
