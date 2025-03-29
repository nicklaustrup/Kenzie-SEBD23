package com.kenzie.app;

import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    public static GradeBook createHomeworkGradeBook(){
        // TODO: fill in the method
        GradeBook gradeBook = new GradeBook("Homework 1", 100);

        gradeBook.addGrade("Lisa", 100);
        gradeBook.addGrade("Bart", 80);
        gradeBook.addGrade("Maggie", 0);
        gradeBook.addGrade("Homer", 75);
        //change to return GradeBook when complete

        return gradeBook;
    }

    public static GradeBook changeGrade(GradeBook gradeBook, String name, int points){
        // TODO: fill in the method
        int newGrade = gradeBook.checkGrade(name) + points;
        if ((gradeBook.checkGrade(name) != 0)
                && (gradeBook.checkGrade(name) + points <= gradeBook.getTotalPoints())) {
            gradeBook.updateGrade(name, newGrade);
        } else if ((gradeBook.checkGrade(name) != 0)
        && (gradeBook.checkGrade(name) + points >= gradeBook.getTotalPoints())){
            gradeBook.updateGrade(name, gradeBook.getTotalPoints());
        }
        //change to return GradeBook when complete
        return gradeBook;
    }

    public static void main(String[] args) {
        GradeBook homeworkGrades = createHomeworkGradeBook();
        if (homeworkGrades != null) {
            System.out.println(homeworkGrades.getAssignmentGradeMap().entrySet());
            homeworkGrades = changeGrade(homeworkGrades,"Bart",10);
            System.out.println("Add 10 points to Bart: " + homeworkGrades.getAssignmentGradeMap().entrySet());
            homeworkGrades = changeGrade(homeworkGrades,"Bart",20);
            System.out.println("Add 20 points to Bart, max rule: " + homeworkGrades.getAssignmentGradeMap().entrySet());
            homeworkGrades = changeGrade(homeworkGrades,"Homer",-10);
            System.out.println("Minus 10 points to Homer: " + homeworkGrades.getAssignmentGradeMap().entrySet());
            homeworkGrades = changeGrade(homeworkGrades,"Maggie",20);
            System.out.println("Did not add 20 points to Maggie, zero rule: " + homeworkGrades.getAssignmentGradeMap().entrySet());
            homeworkGrades = changeGrade(homeworkGrades,"Lisa",20);
            System.out.println("Did not add 20 points to Lisa, total point rule: " + homeworkGrades.getAssignmentGradeMap().entrySet());
            System.out.println("These students have grades 50 or below:");
            System.out.println(homeworkGrades.getGradesEqualOrBelow(50));
            System.out.println("These students have grades 70 or above:");
            System.out.println(homeworkGrades.getGradesEqualOrAbove(70));
        }
    }
}
