package net.sue445.s3tiger.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * empty string matcher
 * @author sue445
 *
 */
public class EmptyStringMatcher extends BaseMatcher<String>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("empty string");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matches(Object item) {
		String str = (String)item;
		return str == null || str.length() == 0;
	}
}
