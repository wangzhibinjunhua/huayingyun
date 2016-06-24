package com.tentinet.healthy.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * 利用DialogFragment 实现日历功能
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public interface LoginInputListener {
        void onLoginInputComplete(int year, int month, int day);
    }
    private  int year,month,day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, getYear(), getMonth(), getDay());
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        LoginInputListener listener = (LoginInputListener) getActivity();
        listener.onLoginInputComplete(year
                , month, day);
    }

}