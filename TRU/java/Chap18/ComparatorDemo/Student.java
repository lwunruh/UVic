
/**
 * Represents a student in the Comparator example.
 * @author Java Foundations
 * @version 4.0
 */
public class Student
{   
	private String id;
	private Integer examAverage;
	private Integer assignmentAverage;
	private Integer overallAverage;

	/**
	 * Constructor for Student object.
	 * @param id
	 * @param examAverage
	 * @param assignmentAverage
	 */
	public Student(String id, Integer examAverage,
			Integer assignmentAverage)
	{
		this.id = id;
		this.examAverage = examAverage;
		this.assignmentAverage = assignmentAverage;
		this.overallAverage = (examAverage + assignmentAverage) / 2;
	}

	/**
	 * Getter for examAverage
	 * @return examAverage 
	 */
	public Integer getExamAverage()
	{
		return examAverage;
	}

	/**
	 * Getter for assignmentAverage
	 * @return assignmentAverage 
	 */
	public Integer getAssignmentAverage()
	{
		return assignmentAverage;
	}

	/**
	 * Getter for overallAverage
	 * @return overallAverage 
	 */
	public Integer getOverallAverage()
	{
		return overallAverage;
	}

	/**
	 * Provides a String representation of a Student object.
	 * @return String representation of this Student object 
	 */
	public String toString()
	{
		return (id + " examAverage: " + examAverage +
				" assignmentAverage: " + assignmentAverage +
				" overallAverage: " + overallAverage );
	}
}