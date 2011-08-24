package net.sue445.s3tiger;

import static org.junit.Assert.*;

import net.sue445.s3tiger.IgnoreJUnit;
import net.sue445.s3tiger.Slim3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;


@IgnoreJUnit
@RunWith(Slim3.class)
public class IgnoreJUnitClassTest {

	@Test
	public void test() throws Exception {
		if(!AppEngineUtil.isServer()){
			fail("this test class is ignored on JUnit");
		}
	}
}
