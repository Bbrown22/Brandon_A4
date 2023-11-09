package com.coderscampus.A4;

import java.io.IOException;
import java.util.Arrays;

public class CourseSorter {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        try {
            Student[] students = fileService.readFromCSV("student-master-list.csv");

            Arrays.sort(students, (s1, s2) -> {
                int courseComparison = getCourseIndex(s1.getCourse()) - getCourseIndex(s2.getCourse());
                if (courseComparison != 0) {
                    return courseComparison;
                } else {
                    return s2.getGrade() - s1.getGrade();
                }
            });

            int currentCourseIndex = getCourseIndex(students[0].getCourse());
            int start = 0;
            for (int i = 1; i < students.length; i++) {
                int courseIndex = getCourseIndex(students[i].getCourse());
                if (courseIndex != currentCourseIndex) {
                    fileService.writeToCSV(Arrays.copyOfRange(students, start, i), "Course" + (currentCourseIndex + 1) + ".csv");
                    start = i;
                    currentCourseIndex = courseIndex;
                }
            }
            fileService.writeToCSV(Arrays.copyOfRange(students, start, students.length), "Course" + (currentCourseIndex + 1) + ".csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCourseIndex(String courseName) {
        String trimmedCourseName = courseName.trim();
        if (trimmedCourseName.startsWith("COMPSCI")) {
            return 0;
        } else if (trimmedCourseName.startsWith("APMTH")) {
            return 1;
        } else if (trimmedCourseName.startsWith("STAT")) {
            return 2;
        } else {
            return -1;
        }
    }
}