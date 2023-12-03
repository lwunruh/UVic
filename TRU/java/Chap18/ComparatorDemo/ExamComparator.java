import java.util.Comparator;

/**
 * This Comparator sorts students by their exam average.
 * @author Java Foundations
 * @version 4.0
 */
public class ExamComparator implements Comparator<Student>
{
	/**
	 * Compares two Student objects by their exam average.
	 */
	@Override
	public int compare(Student o1, Student o2)
	{
		Integer average1 = o1.getExamAverage();
		Integer average2 = o2.getExamAverage();

		return average1.compareTo(average2);
	}
}
