/**
 *
 * 	Teacher.java
 *
 * 	A program which outlines the Teacher class, used to grade exams
 * 	by comparing each line of the test key to each line of the student's
 * 	answer sheet.
 *
 */
package Grading;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Teacher{
	// DATA FIELDS
	private String solutionFilePath;
	private String studentExamAnswerDirectory;
	private ArrayList<String> solutionList;

	// CONSTRUCTOR
	public Teacher(String solutionFilePath, String studentExamAnswerDirectory){
		this.solutionFilePath = solutionFilePath;
		this.studentExamAnswerDirectory = studentExamAnswerDirectory;
		this.solutionList = readDataFromFile(solutionFilePath);
	}

	// METHODS
	// -- set/get methods
	public String getSolutionFilePath(){
		return solutionFilePath;
	}
	public void setSolutionFilePath(String solutionFilePath){
		this.solutionFilePath = solutionFilePath;
	}

	public String getStudentExamAnswerDirectory(){
		return studentExamAnswerDirectory;
	}
	public void setStudentExamAnswerDirectory(String studentExamAnswerDirectory){
		this.studentExamAnswerDirectory = studentExamAnswerDirectory;
	}

	public ArrayList<String> getSolutionList(){
		return solutionList;
	}
	public void setSolutionList(ArrayList<String> solutionList){
		this.solutionList = solutionList;
	}

	/**
	 *
	 * 	readDataFromFile
	 *
	 * 	Loads a file using File and then uses Scanner to read every
	 * 	line in the file and add it to an ArrayList<String>
	 *
	 * 	@param filePath the file you wish to read data from
	 *
	 * 	@return String ArrayList containing all the lines of the file as separate elements
	 *
	 */
	public ArrayList<String> readDataFromFile(String filePath){
		ArrayList<String> output = new ArrayList<>();

		File file = new File(filePath);
		try {
			Scanner scnr = new Scanner(file);

			while (scnr.hasNextLine()){
				// force all lines to be read in as lowercase for
				// easier use later
				output.add(scnr.nextLine().toLowerCase());
			}

			scnr.close();
		} catch (Exception e){
			System.out.println("Error reading data from file.");
			System.exit(-1);
		}

		return output;
	}

	/**
	 *
	 *	gradingOneExam
	 *
	 *	Loops through every line of the specified exam and compares each line to the
	 *	corresponding line in the solutions file, adding a 1 to an integer ArrayList
	 *	if the two lines match or a 0 if they don't
	 *
	 *	@param studentAnswerFilePath the path to where the specified exam exists
	 *
	 *	@return An ArrayList<Integer> which represents whether or not each question 
	 *	on the exam is correct or incorrect with either a 1 or a 0 respectively
	 *
	 */
	public ArrayList<Integer> gradingOneExam(String studentAnswerFilePath){
		ArrayList<Integer> output = new ArrayList<>();

		studentAnswerFilePath = studentExamAnswerDirectory + "/" + studentAnswerFilePath;
	
		ArrayList<String> studentAnswers = readDataFromFile(studentAnswerFilePath);

		for(int i = 0; i < studentAnswers.size(); i++){
			if (studentAnswers.get(i).equals(solutionList.get(i))){
				output.add(1);
			}
			else {
				output.add(0);
			}
		}

		return output;
	}

	/**
	 *
	 *	gradingAllStudents
	 *
	 *	Loops through all the tests in the studentExamAnswerDirectory and uses
	 *	split to figure out the courseName and studentName from the file name
	 *	before then calling gradingOneExam to generate an integer ArrayList
	 *	in order to generate an Exam object for each of the tests in the
	 *	aforementioned directory
	 *
	 *	@return ArrayList<Exam>, with each representing one of the student's tests
	 *
	 */
	public ArrayList<Exam> gradingAllStudents(){
		ArrayList<Exam> output = new ArrayList<>();

		File dir = new File(studentExamAnswerDirectory);
		File[] tests = dir.listFiles();

		for (var test: tests){
			String[] testData = test.getName().split("[()]");
			
			String courseName = testData[1];
			String studentName = testData[0];
			ArrayList<Integer> examQuestionScoreList = gradingOneExam(test.getName());

			output.add(new Exam(courseName, studentName, examQuestionScoreList));
		}

		return output;
	}
}
