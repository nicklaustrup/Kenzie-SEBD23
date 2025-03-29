package com.kenzie.app.datastructures;


import java.util.ArrayList;
import java.util.HashMap;

public class WhichDataStructure {
    // DataStructureClass <Type(s)> name = new DataStructureClass<>();

    /**************************************************************************************
     * Write the test first to make sure the following is returned from this method
     * MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY
     * After, debug this method
     ***************************************************************************************/
    public static ArrayList<String> createDaysOfWeek() {
        ArrayList<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add("MONDAY");
        daysOfWeek.add("TUESDAY");
        daysOfWeek.add("WEDNESDAY");
        daysOfWeek.add("THURSDAY");
        daysOfWeek.add("FRIDAY");
        daysOfWeek.add("SATURDAY");
        daysOfWeek.add("SUNDAY");
        return daysOfWeek;
    }


    public static ArrayList<String> createDaysOfWeek2() {

        ArrayList<String> daysOfWeek = new ArrayList<>();

        daysOfWeek.add("SUNDAY");
        daysOfWeek.add("MONDAY");
        daysOfWeek.add("TUESDAY");
        daysOfWeek.add("WEDNESDAY");
        daysOfWeek.add("THURSDAY");
        daysOfWeek.add("frIDAY");
        daysOfWeek.add("SATURDAY");

        return daysOfWeek;
    }












    /**************************************************************************************
     * Alter this method to return the day to alarm lookup map
     * You'll need to alter the test to use this value
     ***************************************************************************************/
    public static HashMap<String, Integer> alarmTime() {

        HashMap<String, Integer> dayToAlarmTimeLookUp = new HashMap<>();
        dayToAlarmTimeLookUp.put("MONDAY", 6);
        dayToAlarmTimeLookUp.put("TUESDAY", 7);
        dayToAlarmTimeLookUp.put("WEDNESDAY", 8);
        dayToAlarmTimeLookUp.put("THURSDAY", 7);
        dayToAlarmTimeLookUp.put("FRIDAY", 9);
        dayToAlarmTimeLookUp.put("SATURDAY", 2);
        dayToAlarmTimeLookUp.put("SUNDAY", 10);

        return dayToAlarmTimeLookUp;
    }

    /**************************************************************************************
     * Write a method that looks up the alarm based on the day of the week passed in
     ***************************************************************************************/
    public static void lookupAlarm(String dayOfWeek) {

    }

    public static void main(String[] args) {
        lookupAlarm("MONDAY");
        lookupAlarm("monday");
        lookupAlarm("wednsday");
    }

}
