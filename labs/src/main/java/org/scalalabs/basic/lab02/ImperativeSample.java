package org.scalalabs.basic.lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This piece of java code depicts an imperative approach
 * of grouping a list of Person objects based on their age group.
 * 
 * Rewrite the groupAdultsPerAgeGroup method in Scala using higher order functions
 * in @see {@link BasicCollectionExercise.groupAdultsPerAgeGroup}
 * 
 */
public class ImperativeSample {

	private static final Person jack = new Person(14, "Jack");
	private static final Person duke = new Person(32, "Duke");
	private static final Person jeniffer = new Person(34, "Jeniffer");
	private static final Person erik = new Person(24, "Erik");
	private static final Person susy = new Person(40, "Susy");

	public final static List<Person> persons = Arrays.asList(jack, duke,
			jeniffer, susy, erik);

	@SuppressWarnings({ "serial" })
	public static final Map<Integer, List<Person>> expected = new TreeMap<Integer, List<Person>>() {
		{
			put(20, Arrays.asList(erik));
			put(30, Arrays.asList(duke, jeniffer));
			put(40, Arrays.asList(susy));
		}
	};
	
	/**
	 * The grouping algorithm works as follows:
	 * 1. filter out all adults of the list of persons
	 * 2. sort the list by name
	 * 3. group each person by their age group, e.g. 30 -> List<duke, jeniffer>
	 * @return grouped adults
	 */
	public static Map<Integer, List<Person>> groupAdultsPerAgeGroup(List<Person> persons) {

		// filter adults
		List<Person> adults = new ArrayList<>();
		for (Person p : persons) {
			if (p.getAge() >= 18) {
				adults.add(p);
			}
		}

		// sort
		Collections.sort(adults, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				return p1.getName().compareTo(p2.getName());
			}
		});

		// group by age group
		Map<Integer, List<Person>> adultsPerAgeGroup = new TreeMap<>();
		for (Person person : adults) {
			int ageGroup = person.getAge() / 10 * 10;
			List<Person> ageGroupPersons = adultsPerAgeGroup.get(ageGroup);
			if (ageGroupPersons == null) {
				ageGroupPersons = new ArrayList<Person>();
			}
			ageGroupPersons.add(person);
			adultsPerAgeGroup.put(ageGroup, ageGroupPersons);
		}
		return adultsPerAgeGroup;
	}

	public static void main(String[] args) {
		Map<Integer, List<Person>> adultsPerAgeGroup = groupAdultsPerAgeGroup(persons);
		assert(expected.equals(adultsPerAgeGroup));
	}

	static class Person {
		private int age;
		private String name;

		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		public int getAge() { 
			return age;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return String.format("Person [age=%s, name=%s]", age, name);
		}

	}

}
