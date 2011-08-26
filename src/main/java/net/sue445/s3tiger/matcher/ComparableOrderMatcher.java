package net.sue445.s3tiger.matcher;

import java.util.Iterator;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class ComparableOrderMatcher<T extends Iterable<? extends Comparable<?>>> extends TypeSafeMatcher<T> {
	/**
	 * description of expected value
	 */
	private String description;

	/**
	 * if true) asc
	 * if false) desc
	 */
	private boolean isAscending;


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean matchesSafely(T items) {
		Iterator<Comparable<Object>> it = (Iterator<Comparable<Object>>) items.iterator();

		if(!it.hasNext()){
			return true;
		}

		Comparable<Object> beforeItem = it.next();

		while(it.hasNext()){
			Comparable<Object> currentItem = it.next();

			if(isAscending){
				if(beforeItem.compareTo(currentItem) > 0){
					return false;
				}
			} else{
				if(beforeItem.compareTo(currentItem) < 0){
					return false;
				}
			}

			beforeItem = currentItem;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText(this.description);
	}

	/**
	 * ascending Matcher
	 * @param <T>			type of cellection element
	 * @return
	 */
	public static <T extends Iterable<? extends Comparable<?>>> Matcher<T> ascending(){
		ComparableOrderMatcher<T> matcher = new ComparableOrderMatcher<T>();

		matcher.isAscending = true;
		matcher.description = "ascending";

		return matcher;
	}

	/**
	 * descending Matcher
	 * @param <T>			type of cellection element
	 * @return
	 */
	public static <T extends Iterable<? extends Comparable<?>>> Matcher<T> descending(){
		ComparableOrderMatcher<T> matcher = new ComparableOrderMatcher<T>();

		matcher.isAscending = false;
		matcher.description = "descending";

		return matcher;
	}
}
