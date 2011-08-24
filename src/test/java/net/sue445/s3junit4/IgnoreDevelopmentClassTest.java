package net.sue445.s3junit4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;


@IgnoreDevelopment
@RunWith(Slim3.class)
public class IgnoreDevelopmentClassTest {

	@Test
	public void test() throws Exception {
		if(AppEngineUtil.isDevelopment()){
			fail("this test class is ignored on development server.");
		}
	}
}
