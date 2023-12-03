import java.util.Comparator;

/**
 * This Comparator sorts students by their overall average.
 * @author Java Foundations
 * @version 4.0
 */
public class OverallComparator implements Comparator<Student>
{
	/**
	 * Compares two Student objects by their overall average.
	 */
	@Override
	public int compare(Student o1, Student o2)
	{
		Integer average1 = o1.getOverallAverage();
		Integer average2 = o2.getOverallAverage();

		return average1.compareTo(average2);
	}
}
