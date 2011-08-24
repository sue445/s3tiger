package net.sue445.s3tiger;

import static org.junit.Assert.*;

import net.sue445.s3tiger.IgnoreProduction;
import net.sue445.s3tiger.Slim3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.util.AppEngineUtil;


@IgnoreProduction
@RunWith(Slim3.class)
public class IgnoreProductionClassTest {

	@Test
	public void test() throws Exception {
		if(AppEngineUtil.isProduction()){
			fail("this test class is ignored on production server.");
		}
	}
}
