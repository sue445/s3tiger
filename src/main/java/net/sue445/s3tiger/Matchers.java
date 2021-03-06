package net.sue445.s3tiger;

import java.util.Comparator;

import net.sue445.s3tiger.matcher.ComparableOrderMatcher;
import net.sue445.s3tiger.matcher.EmptyStringMatcher;
import net.sue445.s3tiger.matcher.OrderMatcher;

import org.hamcrest.Matcher;

/**
 * tiny useful Matcher
 * @author sue445
 *
 */
public final class Matchers {
	private Matchers(){

	}

	/**
	 * assert collection is order by ascending
	 * <pre>
	 * {@code assertThat(list, is(ascending(someComparator)));}
	 * </pre>
	 * @param <T>			type of collection element
	 * @param comparator
	 * @return
	 */
	public static <T> Matcher<Iterable<T>> ascending(Comparator<T> comparator){
		return OrderMatcher.ascending(comparator);
	}

	/**
	 * assert collection is order by descending
	 * <pre>
	 * {@code assertThat(list, is(descending(someComparator)));}
	 * </pre>
	 * @param <T>			type of collection element
	 * @param comparator
	 * @return
	 */
	public static <T> Matcher<Iterable<T>> descending(Comparator<T> comparator){
		return OrderMatcher.descending(comparator);
	}

	/**
	 * assert collection is order by ascending
	 * <pre>
	 * {@code assertThat(list, is(ascending()));}
	 * </pre>
	 * @param <T>			type of collection element
	 * @return
	 */
	public static <T extends Iterable<? extends Comparable<?>>> Matcher<T> ascending(){
		return ComparableOrderMatcher.ascending();
	}

	/**
	 * assert collection is order by descending
	 * <pre>
	 * {@code assertThat(list, is(descending()));}
	 * </pre>
	 * @param <T>			type of collection element
	 * @return
	 */
	public static <T extends Iterable<? extends Comparable<?>>> Matcher<T> descending(){
		return ComparableOrderMatcher.descending();
	}

	/**
	 * assert string is null or empty (equals to StringUtils.isEmpty)
	 * <pre>
	 * {@code assertThat(str, is(emptyString()));}
	 * </pre>
	 * @return
	 */
	public static Matcher<String> emptyString(){
		return new EmptyStringMatcher();
	}
}
