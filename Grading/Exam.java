/**
 *
 * 	Exam.java
 *
 * 	A program which outlines the exam class, used to represent
 * 	each student's test and which can be used to calculate each
 * 	student's test score.
 *
 */
package Grading;

import java.util.ArrayList;

public class Exam{
	// DATA FIELDS
	private String courseName;
	private String studentName;
	private ArrayList<Integer> examQuestionScoreList;

	// CONSTRUCTOR 
	public Exam(String courseName, String studentName, ArrayList<Integer> examQuestionScoreList){
		this.courseName = courseName;
		this.studentName = studentName;
		this.examQuestionScoreList = examQuestionScoreList;
	}

	// METHODS
	// -- set/get methods
	public String getCourseName(){
		return courseName;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	public String getStudentName(){
		return studentName;
	}
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}

	public ArrayList<Integer> getExamQuestionScoreList(){
		return examQuestionScoreList;
	}
	public void setExamQuestionScoreList(ArrayList<Integer> examQuestionScoreList){
		this.examQuestionScoreList = examQuestionScoreList;
	}

	/**
	 *
	 * 	getExamScore
	 *
	 * 	Calculates exam score by returning the total number of correct answers
	 * 	in examQuestionScoreList.
	 *
	 * 	@return total number of correct answers as an int
	 *
	 */
	public int getExamScore(){
		int score = 0;

		for (var question: examQuestionScoreList){
			score += question;
		}
		
		return score;
	}
}
