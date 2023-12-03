import java.util.Comparator;

/**
 * This Comparator sorts students by their assignment average.
 * @author Java Foundations
 * @version 4.0
 */
public class AssignmentComparator implements Comparator<Student>
{
	/**
	 * Compares two Student objects by their assignment average.
	 */
	@Override
	public int compare(Student o1, Student o2)
	{
		Integer average1 = o1.getAssignmentAverage();
		Integer average2 = o2.getAssignmentAverage();

		return average1.compareTo(average2);
	}
}
