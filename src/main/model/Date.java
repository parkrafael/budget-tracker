//package model;
//
//import org.json.JSONObject;
//import persistence.Writable;
//
//// TODO: class descriptor
//public class Date {
//
//    int day;
//    int year;
//    int month;
//
//    // REQUIRES: day has the range of all positive numbers between [1-31]
//    //           month has the range of all positive numbers between [1-12]
//    //           year has the range of all positive non-zero numbers
//    // MODIFIES: this
//    // EFFECTS: instantiates Date with variables provided
//    public Date(int day, int month, int year) {
//        this.day = day;
//        this.month = month;
//        this.year = year;
//    }
//
//    // getters:
//    public int getDay() {
//        return day;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//
//
////    public JSONObject toJson() {
////        JSONObject json = new JSONObject();
////
////        json.put("day", day);
////        json.put("month", month);
////        json.put("year", year);
////
////        return json;
////    }
//
//}
