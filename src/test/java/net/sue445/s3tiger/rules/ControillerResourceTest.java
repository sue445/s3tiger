package net.sue445.s3tiger.rules;

import org.junit.Rule;
import org.junit.Test;
import org.slim3.memcache.Memcache;

public class ControillerResourceTest {
	@Rule
	public ControillerResource resource = new ControillerResource(ControillerResourceTest.class);

	@Test
	public void test() {
		// if not initialized appengine, occured error
		Memcache.put("key", "value");
	}

}
