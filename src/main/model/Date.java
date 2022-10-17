package model;

import java.time.format.DateTimeFormatter;

public class Date {

    int day;
    int year;
    int month;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // getters:
    public int getDay() {
        return this.day;
    }
    public int getMonth() {
        return this.month;
    }
    public int getYear() {
        return this.year;
    }

}
