package com.kenzie.classes;

public class Clock {
    //class properties
    private int hour;    //track hour
    private int minute; //track minute
    private String timeZone;    //track timezone
    private String period;    //track AM/PM

    //constructors
    public Clock(){
        this.hour = 12;
        this.minute = 0;
        this.period = "AM";
        this.timeZone = "Eastern";
    }
    public Clock (int hour,
                       int minute,
                       String period,
                       String timeZone){
        if ((hour >=1) && (hour <= 12)){this.hour = hour;}
            else {this.hour = 12;}
        if ((minute >= 0) && (minute <= 59)) {this.minute = minute;}
            else {this.minute = 0;}
        if (period.equals("AM") || period.equals("PM")){this.period = period;}
            else {this.period = "AM";}
        if (timeZone.contains("Eastern") ||
                timeZone.contains("Central") ||
                timeZone.contains("Mountain") ||
                timeZone.contains("Pacific")) {this.timeZone = timeZone;}
            else {this.timeZone = "Eastern";}
    }

    //Methods
    public void setHour(int hour){
       if (hour < 1 || hour > 12){
           hour = 12;
       } else {
           this.hour=hour;
       }
    }
    public int getHour(){
        return this.hour;
    }
    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) {
        } else {
            this.minute = minute;
        }
    }

    public int getMinute(){
        return this.minute;
    }
    public boolean setPeriod(String period){
        if (period.equals("AM") || period.equals("PM")){
            this.period = period;
            return true;
        } else {return false;}
    }
    public String getPeriod(){
        return this.period;
    }
    public boolean setTimeZone(String timeZone){
        if (timeZone.contains("Eastern") ||
                timeZone.contains("Central") ||
                timeZone.contains("Mountain") ||
                timeZone.contains("Pacific")) {
            this.timeZone = timeZone;
            return true;
        } else {return false;}
    }
    public String getTimeZone(){
       return this.timeZone;
    }


}
