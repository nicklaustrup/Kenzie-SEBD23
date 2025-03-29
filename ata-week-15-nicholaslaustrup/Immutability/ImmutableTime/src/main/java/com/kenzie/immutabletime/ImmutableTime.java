package com.kenzie.immutabletime;

import java.math.BigDecimal;


public final class ImmutableTime {

    public static final int MAX_MINUTES_IN_HOUR = 60;
    public static final int MAX_HOURS_IN_DAY = 24;
    public static final int MIN_MINUTES_IN_HOUR = 0;
    public static final int MIN_HOURS_IN_DAY = 0;

    private final int hour;
    private final int minute;

    public ImmutableTime(int hour, int minute) {
        if (hour > (MAX_HOURS_IN_DAY - 1) || hour < MIN_HOURS_IN_DAY ||
                minute < MIN_MINUTES_IN_HOUR || minute > (MAX_MINUTES_IN_HOUR - 1)) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public ImmutableTime plusMinutes(int addMinutes){
        int rawHour = (hour * 60) + minute + addMinutes;
        int newHour = rawHour / 60;
        int newMinute = rawHour % 60;
        return new ImmutableTime(newHour, newMinute);
    }
    public ImmutableTime minusMinutes(int minusMinutes){
        int rawHour = (hour * 60 + minute) - minusMinutes;
        int newHour = rawHour / 60;
        int newMinute = rawHour % 60;
        return new ImmutableTime(newHour, newMinute);
    }
    public ImmutableTime plusHours(int addHours){
        int rawHour = (hour+addHours) * 60 + minute;
        int newHour = rawHour / 60;
        if (newHour > MAX_HOURS_IN_DAY){
            newHour = (rawHour / 60) - 24;
        }
        int newMinute = rawHour % 60;
        return new ImmutableTime(newHour, newMinute);
    }
    public ImmutableTime minusHours(int minusHours){
        int rawHour = (hour - minusHours) * 60 + minute;
        int newHour = rawHour / 60;
        if (newHour < MIN_HOURS_IN_DAY){
            newHour = 24 + newHour;
        }
        int newMinute = rawHour % 60;
        return new ImmutableTime(newHour, newMinute);
    }
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(hour < 10 ? "0" : "").append(hour);
        buf.append(minute < 10 ? ":0" : ":").append(minute);
        return buf.toString();
    }
}