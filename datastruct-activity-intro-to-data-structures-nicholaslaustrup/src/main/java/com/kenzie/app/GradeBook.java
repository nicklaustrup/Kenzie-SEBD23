package com.kenzie.app;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class GradeBook {
    //Define class properties here
    private String assignment;
    private int totalPoints;
    private HashMap<String, Integer> assignmentGradeMap;

    /************** Constructors ****************/
    public GradeBook() {
        //Constructor with no arguments
        this.assignmentGradeMap = new HashMap<String, Integer>();
    }

    public GradeBook(String assignment, int totalPoints){
        //Constructor with two arguments
        this.assignment = assignment;
        this.totalPoints = totalPoints;
        this.assignmentGradeMap = new HashMap<String, Integer>();
    }

    /***************** METHODS ***************/

    public void setAssignment(String assignment) {
        //fill out method
        this.assignment = assignment;
    }

    public String getAssignment() {
        //fill out method
        return this.assignment;
    }

    public void setTotalPoints(int totalPoints) {
        //fill out method
        this.totalPoints = totalPoints;
    }

    public int getTotalPoints() {
        //fill out method
        return this.totalPoints;
    }

    public HashMap<String, Integer> getAssignmentGradeMap() {
        return this.assignmentGradeMap;
    }

    public void addGrade(String name, int grade) {
        //fill out method
        this.assignmentGradeMap.put(name, grade);
    }

    public int checkGrade(String name){
        //fill out method
        return assignmentGradeMap.get(name);
    }

    public void updateGrade(String name, int grade){
        //fill out method
        this.assignmentGradeMap.put(name, grade);
    }

    public ArrayList<String> getGradesEqualOrBelow(int grade){
        //fill out method
        ArrayList<String> badStudents = new ArrayList<>();

// Source https://www.geeksforgeeks.org/how-to-iterate-hashmap-in-java/
        for (Map.Entry<String, Integer> set : assignmentGradeMap.entrySet()) {
            if (set.getValue() <= grade) {
                badStudents.add(set.getKey());
            }
        }

        return badStudents;
    }

    public ArrayList<String> getGradesEqualOrAbove(int grade){
        //fill out method
        ArrayList<String> goodStudents = new ArrayList<>();

        // source https://www.geeksforgeeks.org/how-to-iterate-hashmap-in-java/
        for (Map.Entry<String, Integer> set : assignmentGradeMap.entrySet()) {
            if (set.getValue() >= grade) {
                goodStudents.add(set.getKey());
            }
        }


        return goodStudents;
    }
}
