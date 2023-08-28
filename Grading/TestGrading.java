/**
 *
 * 	TestGrading.java
 *
 * 	A program which grades all of the student exams in the StudentAnswers folder
 * 	using a key given by a Solutions.txt file before then printing out the results
 * 	to both the console and to a StudentScore.txt file, making use of the Teacher
 * 	and Exam classes included in this package to do so.
 *
 */
package Grading;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;

public class TestGrading{
	/**
	 *
	 * 	printFormattedLine
	 *
	 * 	Takes in 3 strings and prints a formatted line of text using printf
	 * 	and a predefined width for each element.
	 *
	 * 	@param a the first string that should be printed
	 * 	@param b the second string that should be printed
	 * 	@param c the third and final string to be printed
	 *
	 */
	private static void printFormattedLine(String a, String b, String c){
		System.out.printf("%15s | %-15s | %-10s |\n", a, b, c);
		System.out.println("------------------------------------------------");
	}

	/**
	 *
	 * 	printToConsole
	 *
	 * 	Prints as output to the console a formatted table of information
	 * 	which displays each student's name, the name of the course, and
	 * 	the score which that student recieved on the exam.
	 *
	 * 	@param a An array list of exams representing each student's exam
	 *
	 */
	private static void printToConsole(ArrayList<Exam> a){
		printFormattedLine("Student Name", "Course Name", "Score");

		for (var item: a){
			printFormattedLine(item.getStudentName(), item.getCourseName(),
					String.valueOf(item.getExamScore()));
		}
	}

	/**
	 *
	 * 	printToFile
	 *
	 * 	Prints as output to a file a formatted table of information
	 * 	which displays each student's name, the name of the course, and
	 * 	the score which that student recieved on the exam.
	 *
	 * 	@param path 	A string path to the desired output file
	 * 	@param a	An array list of exams representing each student's exam
	 *
	 */
	private static void printToFile(String path, ArrayList<Exam> a){
		File outputFile = new File(path);

		try {
			PrintWriter printer = new PrintWriter(outputFile);

			printer.printf("%15s | %-15s | %-10s |\n","Student Name", "Course Name", "Score");
			printer.println("------------------------------------------------");	

			for (var item: a){
				printer.printf("%15s | %-15s | %-10s |\n", item.getStudentName(),
						item.getCourseName(), String.valueOf(item.getExamScore()));
				printer.println("------------------------------------------------");	
			}

			printer.close();
		} catch (Exception e) {
			System.out.println("Error writing output to file.");
			System.exit(-1);
		}
	}

	public static void main(String[] args){
		final String SOLUTION_FILEPATH = "Grading/Solutions.txt";
		final String STUDENT_EXAM_ANSWER_DIRECTORY = "Grading/StudentAnswers";
		final String OUTPUT_FILE = "Grading/StudentScore.txt";
		
		Teacher teacher = new Teacher(SOLUTION_FILEPATH, STUDENT_EXAM_ANSWER_DIRECTORY);
		
		ArrayList<Exam> tests = teacher.gradingAllStudents();

		printToConsole(tests);

		printToFile(OUTPUT_FILE, tests);

		System.out.println("Grading is done.");
	}
}
