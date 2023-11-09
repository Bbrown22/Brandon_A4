package com.coderscampus.A4;

import java.io.*;
import java.util.Arrays;

public class FileService {
	public Student[] readFromCSV(String filename) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    Student[] students = new Student[1000]; // assuming max 1000 students
	    String line;
	    int index = 0;
	    boolean isFirstLine = true;
	    while ((line = reader.readLine()) != null) {
	        if (isFirstLine) {
	            isFirstLine = false;
	            continue;
	        }
	        String[] values = line.split(",");
	        students[index++] = new Student(values[0], values[1], values[2], Integer.parseInt(values[3]));
	    }
	    reader.close();
	    return Arrays.copyOf(students, index); // trim the array to the actual size
	}

    public void writeToCSV(Student[] students, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("Student ID,Student Name,Course,Grade\n");
        for (Student student : students) {
            if (student != null) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getCourse() + "," + student.getGrade() + "\n");
            }
        }
        writer.close();
    }
}