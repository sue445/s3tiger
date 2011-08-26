package net.sue445.s3tiger;

import static net.sue445.s3tiger.matcher.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;


public class CopyOfMatchersTest {
	private static final Comparator<Integer> comparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer obj1, Integer obj2) {
			return obj1 - obj2;
		}
	};

	@Test
	public void testAscending1(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		assertThat(list, is(ascending(comparator)));
		assertThat(list, is(ascending()));
	}

	@Test
	public void testAscending2(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(4);

		assertThat(list, is(not(ascending(comparator))));
		assertThat(list, is(not(ascending())));
	}

	@Test
	public void testDescending1(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);

		assertThat(list, is(descending(comparator)));
		assertThat(list, is(descending()));
	}

	@Test
	public void testDescending2(){
		List<Integer> list = new ArrayList<Integer>();

		list.add(4);
		list.add(2);
		list.add(3);
		list.add(1);

		assertThat(list, is(not(descending(comparator))));
		assertThat(list, is(not(descending())));
	}

	@Test
	public void testEmptyString1(){
		assertThat("", is(emptyString()));
	}

	@Test
	public void testEmptyString2(){
		assertThat(null, is(emptyString()));
	}

	@Test
	public void testEmptyString3(){
		assertThat("aaa", is(not(emptyString())));
	}
}
