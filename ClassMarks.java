// Copyright (c) 2022 Mel Aguoth All rights reserved.
//
// Created By: Mel Aguoth
// Date: January 5, 2022
// Obtains student names and assignments from two different text files, and adds them
// to a 2D array along with random numbers for their marks.

// Import modules.
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class ClassMarks {
  public static String[][] generateMarks(String[] students, String[] assignments) throws Exception {

    // Declare constants and variables.
    Random random = new Random();
    String[][]markArray = new String[assignments.length + 1][students.length + 1];

    // Add the student names, assignments, and marks to the array.
    markArray[0][0] = "Student";
    for (int assignColumnCounter = 1; assignColumnCounter < markArray.length;
         ++assignColumnCounter) {
      markArray[assignColumnCounter][0] = assignments[assignColumnCounter - 1];
    }
    for (int nameRowCounter = 1; nameRowCounter < markArray[0].length; ++nameRowCounter) {
      markArray[0][nameRowCounter] = students[nameRowCounter - 1];
    }
    for (int columnCounter = 1; columnCounter < markArray.length; ++columnCounter) {
      for (int rowCounter = 1; rowCounter < markArray[0].length; ++rowCounter) {
        double randomDouble = 50 + (Math.random() * 50);
        markArray[columnCounter][rowCounter] = String.valueOf(Math.round(randomDouble));
      }
    }

    // Return the array.
    return markArray;
  }

  public static void main(String[] args) throws Exception {

    // Declare the lists.
    ArrayList<String> assignList = new ArrayList<>();
    ArrayList<String> studentList = new ArrayList<>();

    // Introduce the program.
    System.out.print("This program takes student names and assignments from two different"
                   + " text files and outputs them to a CSV file called “marks”"
                   + " along with their grades.");

    // Read the assignments from their text file into their list.
    File assignFile = new File("/home/ubuntu/ICS4U/Unit1/Unit1-07/Unit1-07-Java/assignments");
    Scanner sc = new Scanner(assignFile);
    while (sc.hasNextLine()) {
      assignList.add(sc.nextLine());
    }
    sc.close();

    // Read the student names from their text file into their list.
    File studentFile = new File("/home/ubuntu/ICS4U/Unit1/Unit1-07/Unit1-07-Java/students");
    Scanner sc2 = new Scanner(studentFile);
    while (sc2.hasNextLine()) {
      studentList.add(sc2.nextLine());
    }
    sc2.close();

    // Transfer the assignments and student names from their lists to their respective arrays.
    String[] assignArray = new String[assignList.size()];
    for (int assignArrayCounter = 0; assignArrayCounter < assignList.size(); ++assignArrayCounter) {
      assignArray[assignArrayCounter] = assignList.get(assignArrayCounter);
    }
    String[] studentArray = new String[studentList.size()];
    for (int studentArrayCounter = 0; studentArrayCounter < studentList.size();
           ++studentArrayCounter) {
      studentArray[studentArrayCounter] = studentList.get(studentArrayCounter);
    }

    // Call generateMarks.
    String[][] marks = generateMarks(studentArray, assignArray);

    // Add the 2D array to a csv file.
    StringBuilder builder = new StringBuilder();
    for (int rows = 0; rows < marks[0].length; ++rows) {
      for (int columns = 0; columns < marks.length; ++columns) {
        builder.append(marks[columns][rows]);
        builder.append("       ");
      }
      builder.append("\n");
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter(
                            "/home/ubuntu/ICS4U/Unit1/Unit1-07/Unit1-07-Java/marks.csv"));
    writer.write(builder.toString());
    writer.close();
    System.out.print("\n" + "Marks written.");
    System.out.print("\n");
  }
}
