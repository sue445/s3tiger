package net.sue445.s3tiger;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;


@RunWith(Slim3.class)
public class IgnoreMethodTest {

	@IgnoreNotServer
	@Test
	public void testIgnoreNotServer() throws Exception {
		if(!AppEngineUtil.isServer()){
			fail("this test class is ignored on NotServer");
		}
	}

	@IgnoreServer
	@Test
	public void testIgnoreServer() throws Exception {
		if(AppEngineUtil.isServer()){
			fail("this test class is ignored on Server");
		}
	}

	@IgnoreDevelopment
	@Test
	public void testIgnoreDevelopment() throws Exception {
		if(AppEngineUtil.isDevelopment()){
			fail("this test class is ignored on development server.");
		}
	}

	@IgnoreProduction
	@Test
	public void testIgnoreProduction() throws Exception {
		if(AppEngineUtil.isProduction()){
			fail("this test class is ignored on production server.");
		}
	}

	@Ignore
	@Test
	public void testIgnore() throws Exception {
		fail("this test class is ignored");
	}
}
