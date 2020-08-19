package com.example.diary;

public class date {
    int Year;
    int month;
    int day;

    public date(int year, int month, int day) {
        Year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
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
}

