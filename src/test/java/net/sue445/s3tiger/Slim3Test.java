package net.sue445.s3tiger;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sue445.s3tiger.inner.IgnoreType;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;

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
	public void hasCurrentEnvironment_Contains() throws Exception {
		if(AppEngineUtil.isServer()){
			return;
		}

		List<IgnoreType> list = new ArrayList<IgnoreType>();
		list.add(IgnoreType.DEVELOPMENT);
		list.add(IgnoreType.NOT_SERVER);
		assertThat(Slim3.hasCurrentEnvironment(list), is(true));
	}

	@Test
	public void containsSystemProperties(){
		assertThat(System.getProperty("java.util.logging.config.file"), is("WEB-INF/classes/logging.properties"));
	}

}
