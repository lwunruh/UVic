import java.util.Arrays;

/**
 * Demonstrates sorting using a Comparator.
 * @author Java Foundations
 * @version 4.0
 */
public class ComparatorDemo
{
	/**
	 * Creates several Student objects, then sorts them using three
	 * different Comparator objects.
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args)
	{
		Student[] students = new Student[5];

		students[0] = new Student("Mary", 97, 75);
		students[1] = new Student("James", 80, 80);
		students[2] = new Student("Mark", 75, 94);
		students[3] = new Student("Jolene", 95, 85);
		students[4] = new Student("Cassandra", 85, 75);

		// output students before sort
		for (int i = 0; i < students.length; i++)
			System.out.println(students[i]);

		Arrays.sort(students, new ExamComparator());

		// output students after sorting by exam average
		System.out.println();
		System.out.println("After sorting by exam average:");
		for (int i = 0; i < students.length; i++)
			System.out.println(students[i]);

		Arrays.sort(students, new AssignmentComparator());

		// output students after sorting by assignment average
		System.out.println();
		System.out.println("After sorting by assignment average:");
		for (int i = 0; i < students.length; i++)
			System.out.println(students[i]);

		Arrays.sort(students, new OverallComparator());

		// output students after sorting by overall average
		System.out.println();
		System.out.println("After sorting by overall average:");
		for (int i = 0; i < students.length; i++)
			System.out.println(students[i]);
	}
}