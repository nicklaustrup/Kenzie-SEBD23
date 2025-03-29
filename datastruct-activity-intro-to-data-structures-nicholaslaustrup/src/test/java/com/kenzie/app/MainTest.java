package com.kenzie.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @DisplayName("GradeBook class can be instantiated with no arguments")
    @Test
    void canCreateGradeBook_DefaultConstructor() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        try {
            //check that required methods are defined
            @SuppressWarnings("unchecked")
            Constructor constructor = GradeBook.class.getConstructor();
            @SuppressWarnings("unchecked")
            GradeBook gradebook = (GradeBook) constructor.newInstance();

            @SuppressWarnings("unchecked")
            Method getAssignment = GradeBook.class.getMethod("getAssignment");
            @SuppressWarnings("unchecked")
            String assignmentValue = (String) getAssignment.invoke(gradebook);
            assertEquals(null, assignmentValue, "assignment value is not initialized to null");

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap assignmentGradeMapValue = (HashMap) getAssignmentGradeMap.invoke(gradebook);
            assertEquals(new HashMap<String, Integer>(), assignmentGradeMapValue, "assignmentGradeMap value is not initialized to empty HashMap");

            @SuppressWarnings("unchecked")
            Method getTotalPoints = GradeBook.class.getMethod("getTotalPoints");
            @SuppressWarnings("unchecked")
            int totalPointsValue = (int) getTotalPoints.invoke(gradebook);
            assertEquals(0, totalPointsValue, "Total points value is not initialized to 0");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("GradeBook and all setter/getter methods must be defined");
        }

    }

    @DisplayName("GradeBook class can be instantiated with two arguments")
    @Test
    void canCreateGradeBook_TwoArgConstructor() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        try {
            //check that required methods are defined
            @SuppressWarnings("unchecked")
            Constructor constructor = GradeBook.class.getConstructor(String.class, int.class);

            @SuppressWarnings("unchecked")
            GradeBook gradebook = (GradeBook) constructor.newInstance("homework", 75);

            @SuppressWarnings("unchecked")
            Method getAssignment = GradeBook.class.getMethod("getAssignment");
            @SuppressWarnings("unchecked")
            String assignmentValue = (String) getAssignment.invoke(gradebook);
            assertEquals("homework", assignmentValue, "assignment value is not initialized to null");

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap assignmentGradeMapValue = (HashMap) getAssignmentGradeMap.invoke(gradebook);
            assertEquals(new HashMap<String, Integer>(), assignmentGradeMapValue, "assignmentGradeMap value is not initialized to empty HashMap");

            @SuppressWarnings("unchecked")
            Method getTotalPoints = GradeBook.class.getMethod("getTotalPoints");
            @SuppressWarnings("unchecked")
            int totalPointsValue = (int) getTotalPoints.invoke(gradebook);
            assertEquals(75, totalPointsValue, "Total points value is not initialized to 75");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("GradeBook and all setter/getter methods must be defined");
        }

    }

    @DisplayName("GradeBook objects have been created")
    @Test
    public void testCreateHomeworkGradeBook_AssignmentsCreated() {
        try {
            @SuppressWarnings("unchecked")
            GradeBook homeworkGrades = Main.createHomeworkGradeBook();
            assertNotNull(homeworkGrades, "Reason: returned null instead of GradeBook object");

            @SuppressWarnings("unchecked")
            Method getAssignment = GradeBook.class.getMethod("getAssignment");
            @SuppressWarnings("unchecked")
            String assignmentValue = (String) getAssignment.invoke(homeworkGrades);
            assertEquals("Homework 1", assignmentValue, "Assignment value is not set. Expected value: Homework 1");

            @SuppressWarnings("unchecked")
            Method getTotalPoints = GradeBook.class.getMethod("getTotalPoints");
            @SuppressWarnings("unchecked")
            int totalPointsValue = (int) getTotalPoints.invoke(homeworkGrades);
            assertEquals(100, totalPointsValue, "Total points not set. Expected value: 100");

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap assignmentGradeMapValue = (HashMap) getAssignmentGradeMap.invoke(homeworkGrades);

            assertNotNull(assignmentGradeMapValue.get("Homer"), "Entry for key: Homer missing");
            assertNotNull(assignmentGradeMapValue.get("Maggie"), "Entry for key: Maggie missing");
            assertNotNull(assignmentGradeMapValue.get("Lisa"), "Entry for key: Lisa missing");
            assertNotNull(assignmentGradeMapValue.get("Bart"), "Entry for key: Bart missing");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("GradeBook and all setter/getter methods must be defined");
        }
    }

    @DisplayName("Gradebook objects setter/getters working")
    @Test
    public void testCreateHomeworkGradeBook_GradeBookObjectHasGetterSetterMethods() {
        try {
            @SuppressWarnings("unchecked")
            GradeBook homeworkGrades = Main.createHomeworkGradeBook();
            assertNotNull(homeworkGrades, "Reason: returned null instead of GradeBook object");

            //validate setters/getters working
            @SuppressWarnings("unchecked")
            Method setAssignment = GradeBook.class.getMethod("setAssignment", String.class);
            setAssignment.invoke(homeworkGrades, "Exam 1");
            @SuppressWarnings("unchecked")
            Method getAssignment = GradeBook.class.getMethod("getAssignment");
            @SuppressWarnings("unchecked")
            String assignmentValue = (String) getAssignment.invoke(homeworkGrades);
            assertEquals("Exam 1", assignmentValue, "Assignment value is not set. Expected value: Exam 1");

            @SuppressWarnings("unchecked")
            Method setTotalPoints = GradeBook.class.getMethod("setTotalPoints", int.class);
            setTotalPoints.invoke(homeworkGrades, 60);

            @SuppressWarnings("unchecked")
            Method getTotalPoints = GradeBook.class.getMethod("getTotalPoints");
            @SuppressWarnings("unchecked")
            int totalPointsValue = (int) getTotalPoints.invoke(homeworkGrades);
            assertEquals(60, totalPointsValue, "Total points not set. Expected value: 60");

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap assignmentGradeMapValue = (HashMap) getAssignmentGradeMap.invoke(homeworkGrades);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("All setter/getter methods must be defined");
        }
    }

    @DisplayName("GradeBook has correct grades")
    @Test
    public void testCreateHomeworkGradeBook_GradeBookObjectHasCorrectAssignmentGrades() {
        try {
            @SuppressWarnings("unchecked")
            GradeBook homeworkGrades = Main.createHomeworkGradeBook();
            assertNotNull(homeworkGrades, "Reason: returned null instead of GradeBook object");

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap assignmentGradeMapValue = (HashMap) getAssignmentGradeMap.invoke(homeworkGrades);
            @SuppressWarnings("unchecked")
            int homerGrade = (int) assignmentGradeMapValue.getOrDefault("Homer", -1);
            @SuppressWarnings("unchecked")
            int maggieGrade = (int) assignmentGradeMapValue.getOrDefault("Maggie", -1);
            @SuppressWarnings("unchecked")
            int lisaGrade = (int) assignmentGradeMapValue.getOrDefault("Lisa", -1);
            @SuppressWarnings("unchecked")
            int bartGrade = (int) assignmentGradeMapValue.getOrDefault("Bart", -1);

            assertEquals(75, homerGrade, "Test: Homer grade correct. Expected:75");
            assertEquals(0, maggieGrade, "Test: Maggie correct. Expected:0");
            assertEquals(80, bartGrade, "Test: Bart correct. Expected:80");
            assertEquals(100, lisaGrade, "Test: Lisa correct,Expected:100");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("All setter/getter methods must be defined");
        }
    }

    @DisplayName("Gradebook has correct grades after changeGrade")
    @Test
    public void testCreateHomeworkGradeBook_GradeBookObjectHasCorrectGradesAfterChange() {
        try {
            //Initialize gradebook -- does not rely on Main having correct initialization
            //check that required methods are defined
            @SuppressWarnings("unchecked")
            Constructor constructor = GradeBook.class.getConstructor();

            @SuppressWarnings("unchecked")
            GradeBook tempGrades = (GradeBook) constructor.newInstance();

            @SuppressWarnings("unchecked")
            Method setTotalPoints = GradeBook.class.getMethod("setTotalPoints", int.class);
            setTotalPoints.invoke(tempGrades, 100);

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap<String,Integer> assignmentGradeMapValue = (HashMap<String,Integer>) getAssignmentGradeMap.invoke(tempGrades);
            assertNotNull(assignmentGradeMapValue, "Reason: returned null instead of GradeBook object");

            @SuppressWarnings("unchecked")
            Method getAssignment = GradeBook.class.getMethod("getAssignment");
            @SuppressWarnings("unchecked")
            String assignmentValue = (String) getAssignment.invoke(tempGrades);

            //set test values
            ((HashMap<String,Integer>)assignmentGradeMapValue).put("Lisa", 100);
            assignmentGradeMapValue.put("Bart", 80);
            assignmentGradeMapValue.put("Maggie", 0);
            assignmentGradeMapValue.put("Homer", 75);

            Main.changeGrade(tempGrades, "Bart", 10);
            assertEquals(90, assignmentGradeMapValue.getOrDefault("Bart", -1), "Test: Points not added correctly");
            Main.changeGrade(tempGrades, "Bart", 20);
            assertEquals(100, assignmentGradeMapValue.getOrDefault("Bart", -1), "Test: Up to max points rule");
            Main.changeGrade(tempGrades, "Homer", -10);
            assertEquals(65, assignmentGradeMapValue.getOrDefault("Homer", -1), "Test: Minus points");
            Main.changeGrade(tempGrades, "Maggie", 20);
            assertEquals(0, assignmentGradeMapValue.getOrDefault("Maggie", -1), "Test: zero point rule");
            Main.changeGrade(tempGrades, "Lisa", 20);
            assertEquals(100, assignmentGradeMapValue.getOrDefault("Lisa", -1), "Test: max point rule");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("All setter/getter methods must be defined");
        }
    }


    @DisplayName("Gradebook return grades above or equal threshhold")
    @Test
    public void testCreateHomeworkGradeBook_GradeBookObjectReturnsAboveOrEqual() {
        try {
            //Initialize gradebook -- does not rely on Main having correct initialization
            //check that required methods are defined
            @SuppressWarnings("unchecked")
            Constructor constructor = GradeBook.class.getConstructor(String.class, int.class);

            @SuppressWarnings("unchecked")
            GradeBook tempGrades = (GradeBook) constructor.newInstance("Test Activity", 100);
            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap<String,Integer> assignmentGradeMapValue = (HashMap<String,Integer>) getAssignmentGradeMap.invoke(tempGrades);
            assertNotNull(assignmentGradeMapValue, "Reason: returned null instead of GradeBook object");

            assignmentGradeMapValue.put("Lisa", 100);
            assignmentGradeMapValue.put("Bart", 80);
            assignmentGradeMapValue.put("Maggie", 0);
            assignmentGradeMapValue.put("Homer", 75);

            @SuppressWarnings("unchecked")
            ArrayList<String> resultArray1 = new ArrayList<>();
            resultArray1.add("Bart");
            resultArray1.add("Lisa");

            @SuppressWarnings("unchecked")
            Method getGradesEqualOrAbove = GradeBook.class.getMethod("getGradesEqualOrAbove", int.class);
            @SuppressWarnings("unchecked")
            ArrayList<String> resultsArray2 = (ArrayList<String>) getGradesEqualOrAbove.invoke(tempGrades, 80);
            assertNotNull(assignmentGradeMapValue, "Reason: returned null instead of GradeBook object");
            assertEquals(resultArray1, resultsArray2, "Test:Equal or above 80. Expected two matches.");

            @SuppressWarnings("unchecked")
            ArrayList<String> resultsArray3 = new ArrayList<>();
            @SuppressWarnings("unchecked")
            ArrayList<String> resultsArray4 = (ArrayList<String>) getGradesEqualOrAbove.invoke(tempGrades, 200);
            assertEquals(resultsArray3, resultsArray4, "Test:Equal or above 200. Expected:no matches");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("All setter/getter methods must be defined");
        }
    }

    @DisplayName("Gradebook return grades below or equal threshhold")
    @Test
    public void testCreateHomeworkGradeBook_GradeBookObjectReturnsBelowOrEqual() {
        try {
            //Initialize gradebook -- does not rely on Main having correct initialization
            //check that required methods are defined
            @SuppressWarnings("unchecked")
            Constructor constructor = GradeBook.class.getConstructor(String.class, int.class);

            @SuppressWarnings("unchecked")
            GradeBook tempGrades = (GradeBook) constructor.newInstance("Test Activity", 100);

            @SuppressWarnings("unchecked")
            Method getAssignmentGradeMap = GradeBook.class.getMethod("getAssignmentGradeMap");
            @SuppressWarnings("unchecked")
            HashMap<String,Integer> assignmentGradeMapValue = (HashMap<String,Integer>) getAssignmentGradeMap.invoke(tempGrades);
            assertNotNull(assignmentGradeMapValue, "Reason: returned null instead of GradeBook object");

            assignmentGradeMapValue.put("Lisa", 100);
            assignmentGradeMapValue.put("Bart", 80);
            assignmentGradeMapValue.put("Maggie", 0);
            assignmentGradeMapValue.put("Homer", 75);

            @SuppressWarnings("unchecked")
            ArrayList<String> resultsArray1 = new ArrayList<>();
            resultsArray1.add("Homer");
            resultsArray1.add("Maggie");

            @SuppressWarnings("unchecked")
            Method getGradesEqualOrBelow = GradeBook.class.getMethod("getGradesEqualOrBelow", int.class);
            @SuppressWarnings("unchecked")
            ArrayList<String> resultsArray2 = (ArrayList<String>) getGradesEqualOrBelow.invoke(tempGrades, 75);
            assertNotNull(assignmentGradeMapValue, "Reason: returned null instead of GradeBook object");
            assertEquals(resultsArray1, resultsArray2, "Test:Equal or below 80. Expected two matches.");

            @SuppressWarnings("unchecked")
            ArrayList<String> resultArray3 = new ArrayList<>();
            @SuppressWarnings("unchecked")
            ArrayList<String> resultsArray4 = (ArrayList<String>) getGradesEqualOrBelow.invoke(tempGrades, -1);

            assertEquals(resultArray3, resultsArray4, "Test: Equal or below no matches");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("All setter/getter methods must be defined");

        }
    }

}
