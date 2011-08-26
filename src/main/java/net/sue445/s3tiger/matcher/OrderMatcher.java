package net.sue445.s3tiger.matcher;

import java.util.Comparator;
import java.util.Iterator;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class OrderMatcher<T> extends TypeSafeMatcher<Iterable<T>> {
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
	 * comparator for sort
	 */
	private Comparator<T> comparator;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matchesSafely(Iterable<T> items) {
		Iterator<T> it = items.iterator();

		if(!it.hasNext()){
			return true;
		}

		T beforeItem = it.next();

		while(it.hasNext()){
			T currentItem = it.next();

			if(isAscending){
				if(comparator.compare(beforeItem, currentItem) > 0){
					return false;
				}
			} else{
				if(comparator.compare(beforeItem, currentItem) < 0){
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
	 * @param comparator
	 * @return
	 */
	public static <T> Matcher<Iterable<T>> ascending(Comparator<T> comparator){
		OrderMatcher<T> matcher = new OrderMatcher<T>();

		matcher.isAscending = true;
		matcher.comparator = comparator;
		matcher.description = "ascending";

		return matcher;
	}

	/**
	 * descending Matcher
	 * @param <T>			type of cellection element
	 * @param comparator
	 * @return
	 */
	public static <T> Matcher<Iterable<T>> descending(Comparator<T> comparator){
		OrderMatcher<T> matcher = new OrderMatcher<T>();

		matcher.isAscending = false;
		matcher.comparator = comparator;
		matcher.description = "descending";

		return matcher;
	}
}
