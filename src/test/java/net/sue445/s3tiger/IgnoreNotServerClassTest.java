package net.sue445.s3tiger;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;


@IgnoreNotServer
@RunWith(Slim3.class)
public class IgnoreNotServerClassTest {

	@Test
	public void test() throws Exception {
		if(!AppEngineUtil.isServer()){
			fail("this test class is ignored on NotServer");
		}
	}
}
