package net.sue445.s3tiger;

import static net.sue445.s3tiger.matcher.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;


public class MatchersTest {
	private static final Comparator<Integer> comparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer value1, Integer value2) {
			return value1 - value2;
		}
	};

	@Test
	public void ascending_Asc(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4);

		assertThat(list, is(ascending(comparator)));
		assertThat(list, is(ascending()));
	}

	@Test
	public void ascending_NotAsc(){
		List<Integer> list = Arrays.asList(1, 3, 2, 4);

		assertThat(list, is(not(ascending(comparator))));
		assertThat(list, is(not(ascending())));
	}

	@Test
	public void descending_Desc(){
		List<Integer> list = Arrays.asList(4, 3, 2, 1);

		assertThat(list, is(descending(comparator)));
		assertThat(list, is(descending()));
	}

	@Test
	public void descending_NotDesc(){
		List<Integer> list = Arrays.asList(4, 2, 3, 1);

		assertThat(list, is(not(descending(comparator))));
		assertThat(list, is(not(descending())));
	}

	@Test
	public void emptyString_Empty(){
		assertThat("", is(emptyString()));
	}

	@Test
	public void emptyString_Null(){
		assertThat(null, is(emptyString()));
	}

	@Test
	public void emptyString_NotEmpty(){
		assertThat("aaa", is(not(emptyString())));
	}
}
