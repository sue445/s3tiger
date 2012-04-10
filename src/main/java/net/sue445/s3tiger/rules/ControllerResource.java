package net.sue445.s3tiger.rules;

import org.junit.rules.ExternalResource;
import org.slim3.tester.ControllerTestCase;
import org.slim3.tester.ControllerTester;
import org.slim3.util.WrapRuntimeException;

/**
 * initialize appengine resource at test class field.
 * you don't need extends {@link ControllerTestCase}<br>
 *
 * <pre>
 * public class SomeControllerTest{
 *     &#064;Rule
 *     public ControllerResource resource = new ControllerResource(SomeControllerTest.class);
 *
 *     &#064;Test
 *     public void test(){
 *     }
 * }
 * </pre>
 * @author sue445
 * @since 0.0.3
 */
public class ControllerResource extends ExternalResource{
	public ControllerTester tester;

	public ControllerResource(Class<?> testClass) {
		tester = new ControllerTester(testClass);
	}

	@Override
	protected void before() throws Throwable {
		tester.setUp();
	}

	@Override
	protected void after() {
		try {
			tester.tearDown();
		} catch (Exception e) {
			throw new WrapRuntimeException(e);
		}
	}
}
