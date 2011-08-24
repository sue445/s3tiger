package net.sue445.s3junit4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;


@IgnoreServer
@RunWith(Slim3.class)
public class IgnoreServerClassTest {

	@Test
	public void test() throws Exception {
		if(AppEngineUtil.isServer()){
			fail("this test class is ignored on Server");
		}
	}
}
