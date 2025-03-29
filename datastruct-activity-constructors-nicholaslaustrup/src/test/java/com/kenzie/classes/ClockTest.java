package com.kenzie.classes;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

    Object [] clock1 = {};
    Object [] clock2 = {3, 15, "PM", "Central"};
    Object [] clock3 = {15, 65, "morning", "new york"};

    public static Class<?> [] paramTypes = {int.class, int.class, String.class, String.class};

    @Test
    void canCreateDefaultClock() {

        try {
            Constructor clockConstructor = Clock.class.getConstructor();
            Clock clock = (Clock) clockConstructor.newInstance(clock1);

            Method getHour = Clock.class.getMethod("getHour");
            int hourValue  = (int) getHour.invoke(clock);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    assertEquals(12, hourValue, "Constructor default test: Hour=12");

            Method getMinute = Clock.class.getMethod("getMinute");
            int minuteValue  = (int) getMinute.invoke(clock);
            assertEquals(0, minuteValue, "Constructor default test: minute=0");

            Method getPeriod = Clock.class.getMethod("getPeriod");
            String periodValue  = (String) getPeriod.invoke(clock);
            assertEquals("AM", periodValue, "Constructor default test: period = AM");

            Method getTimeZone = Clock.class.getMethod("getTimeZone");
            String timeZoneValue  = (String) getTimeZone.invoke(clock);
            assertEquals("Eastern", timeZoneValue, "Constructor default test: time zone = Eastern");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }
    }

    @Test
    void canCreateClock() {

        try {
            Constructor clockConstructor = Clock.class.getConstructor(paramTypes);
            Clock clock = (Clock) clockConstructor.newInstance(clock2);

            //Clock 2
            Method getHour = Clock.class.getMethod("getHour");
            int hourValue  = (int) getHour.invoke(clock);
            assertEquals(3, hourValue, "Constructor value test: Hour=3");
            Method getMinute = Clock.class.getMethod("getMinute");
            int minuteValue  = (int) getMinute.invoke(clock);
            assertEquals(15, minuteValue, "Constructor value test: minute=15");
            Method getPeriod = Clock.class.getMethod("getPeriod");
            String periodValue  = (String) getPeriod.invoke(clock);
            assertEquals("PM", periodValue, "Constructor default test: period = PM");
            Method getTimeZone = Clock.class.getMethod("getTimeZone");
            String timeZoneValue  = (String) getTimeZone.invoke(clock);
            assertEquals("Central", timeZoneValue, "Constructor default test: time zone = Central");


            Clock otherClock = (Clock) clockConstructor.newInstance(clock3);

            //Clock 3
            hourValue  = (int) getHour.invoke(otherClock);
            assertEquals(12, hourValue, "Constructor value test: Hour = 12");
            minuteValue  = (int) getMinute.invoke(otherClock);
            assertEquals(0, minuteValue, "Constructor value test: minute = 0");
            periodValue  = (String) getPeriod.invoke(otherClock);
            assertEquals("AM", periodValue, "Constructor default test: period = AM");
            timeZoneValue  = (String) getTimeZone.invoke(otherClock);
            assertEquals("Eastern", timeZoneValue, "Constructor invalid test: time zone = Eastern");

        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }

    }


    @Test
    void canSetHour() {
        try {
            Constructor clockConstructor = Clock.class.getConstructor();
            Clock clock = (Clock) clockConstructor.newInstance(clock1);

            Method setHour = Clock.class.getMethod("setHour", int.class);
            setHour.invoke(clock, 10);
            Method getHour = Clock.class.getMethod("getHour");
            int hourValue  = (int) getHour.invoke(clock);
            assertEquals(10, hourValue, "setHour test: hour valid set to 10");

            setHour.invoke(clock, 0);
            hourValue  = (int) getHour.invoke(clock);
            assertEquals(10, hourValue, "setHour test: invalid remains 10");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }
    }

    @Test
    void canSetMinute() {
        try {
            Constructor clockConstructor = Clock.class.getConstructor();
            Clock clock = (Clock) clockConstructor.newInstance(clock1);

            Method setMinute = Clock.class.getMethod("setMinute", int.class);
            setMinute.invoke(clock, 10);
            Method getMinute = Clock.class.getMethod("getMinute");
            int minuteValue  = (int) getMinute.invoke(clock);
            assertEquals(10, minuteValue, "setMinute test: minute valid set to 10");

            setMinute.invoke(clock, 0);
            minuteValue  = (int) getMinute.invoke(clock);
            assertEquals(0, minuteValue, "setMinute test: minute valid set to 0");

            setMinute.invoke(clock, 100);
            minuteValue = (int) getMinute.invoke(clock);
            assertEquals(0, minuteValue, "setMinute test: minute invalid remains 0" );

        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }
    }


    @Test
    void canSetPeriod() {
        try {
            Constructor clockConstructor = Clock.class.getConstructor();
            Clock clock = (Clock) clockConstructor.newInstance(clock1);

            Method setPeriod = Clock.class.getMethod("setPeriod", String.class);
            Method getPeriod = Clock.class.getMethod("getPeriod");

            setPeriod.invoke(clock, "PM");
            String periodValue = (String) getPeriod.invoke(clock);
            assertEquals("PM", periodValue, "setPeriod test: period valid set to PM");

            setPeriod.invoke(clock, "AM");
            periodValue = (String) getPeriod.invoke(clock);
            assertEquals("AM", periodValue, "setPeriod test: period valid set to AM");

            setPeriod.invoke(clock, "night time");
            periodValue = (String) getPeriod.invoke(clock);
            assertEquals("AM", periodValue, "setPeriod test: period invalid remains AM");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }
    }


    @Test
    void canSetTimeZone() {

        try {
            Constructor clockConstructor = Clock.class.getConstructor();
            Clock clock = (Clock) clockConstructor.newInstance(clock1);

            Method setTimeZone = Clock.class.getMethod("setTimeZone", String.class);
            Method getTimeZone = Clock.class.getMethod("getTimeZone");

            setTimeZone.invoke(clock, "Mountain");
            String timeZone = (String) getTimeZone.invoke(clock);
            assertEquals("Mountain", timeZone, "setTimeZone test: timeZone valid set to Mountain");

            setTimeZone.invoke(clock, "Central");
            timeZone = (String) getTimeZone.invoke(clock);
            assertEquals("Central", timeZone, "setTimeZone test: timeZone valid set to Central");

            setTimeZone.invoke(clock, "Pacific");
            timeZone = (String) getTimeZone.invoke(clock);
            assertEquals("Pacific", timeZone, "setTimeZone test: timeZone valid set to Pacific");

            setTimeZone.invoke(clock, "Eastern");
            timeZone = (String) getTimeZone.invoke(clock);
            assertEquals("Eastern", timeZone, "setTimeZone test: timeZone valid set to Eastern");

            setTimeZone.invoke(clock, "Greenwich");
            timeZone = (String) getTimeZone.invoke(clock);
            assertEquals("Eastern", timeZone, "setTimeZone test: timeZone invalid remains Eastern");
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("Clock and all setter/getter methods must be defined");
        }

    }
}