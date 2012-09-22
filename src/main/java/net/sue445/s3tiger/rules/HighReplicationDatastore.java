package net.sue445.s3tiger.rules;

import org.junit.rules.ExternalResource;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * enable HRD on UnitTest
 *
 * @author sue445
 * @see http://shin1o.blogspot.jp/2012/01/slim3cross-group-transactionunit-test.html
 */
public class HighReplicationDatastore extends ExternalResource {
	private LocalServiceTestHelper helper;

	@Override
	protected void before() throws Throwable {
		LocalDatastoreServiceTestConfig dsConfig = new LocalDatastoreServiceTestConfig()
				.setDefaultHighRepJobPolicyUnappliedJobPercentage(0.01f);
		helper = new LocalServiceTestHelper(dsConfig);
		helper.setUp();
	}

	@Override
	protected void after() {
		helper.tearDown();
	}
}
