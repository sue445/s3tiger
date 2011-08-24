package net.sue445.s3junit4;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Slim3.class)
public class Slim3Test {
	private static boolean isRunBeforeClass = false;

	private boolean isRunBefore = false;


	@BeforeClass
	public static void beforeClass() {
		isRunBeforeClass = true;
	}


	@Before
	public void before() {
		isRunBefore = true;
	}

	@Test
	public void testBeforeClass() throws Exception {
		assertThat(isRunBeforeClass, is(true));
	}

	@Test
	public void testBefore() throws Exception {
		assertThat(isRunBefore, is(true));
	}

	@Test
	public void test() throws Exception {

	}
}
